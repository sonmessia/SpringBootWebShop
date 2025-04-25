package asm.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.client.RestTemplate;

import asm.dao.AccountDAO;
import asm.dao.OrderDAO; // Thêm dòng này
import asm.dao.ProductDAO;
import asm.entity.Account;
import asm.entity.Order;
import asm.entity.Product;
import asm.utils.CartItems;
import asm.dto.ProvinceDTO;
import asm.dto.DistrictDTO;
import asm.dto.WardDTO;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	ProductDAO pd;
	
	@Autowired
	OrderDAO orderDAO; // Thêm dòng này

	@Autowired
	AccountDAO accountRepository;
	
	@Autowired
	HttpSession ss;
	
	
	@GetMapping("/view")
	public String index(Model model) {
		model.addAttribute("cartItems", ss.getAttribute("cart"));
		model.addAttribute("user", ss.getAttribute("user"));
		return "cart";
	}
	
	@GetMapping("/add/{id}")
	public String add(@PathVariable("id") int id, @RequestParam("url") String url, @RequestParam("quantity") int quantity) {
	    // Khởi tạo cart nếu chưa có
	    if (ss.getAttribute("cart") == null) {
	        ss.setAttribute("cart", new CartItems());
	    }
	    Product p = pd.findById(id).get();
	    ((CartItems)ss.getAttribute("cart")).addItems(p, quantity);
	    return "redirect:/"+url;
	}
	
	@GetMapping("/modify/{id}")
	public String modify(@PathVariable("id") int id, @RequestParam("url") String url, @RequestParam("quantity") int quantity) {
		Product p = pd.findById(id).get();
		((CartItems)ss.getAttribute("cart")).modifyQuantity(p, quantity);
		return "redirect:/"+url;
	}
	
	@GetMapping("/remove/{id}")
	public String remove(@PathVariable("id") String id, @RequestParam("url") String url) {
		((CartItems)ss.getAttribute("cart")).removeItem(id);
		return "redirect:/"+url;
	}
	
	@GetMapping("/removeall")
	public String remove() {
		((CartItems)ss.getAttribute("cart")).removeAll();
		return "redirect:/cart/view";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("user", ss.getAttribute("user"));
		CartItems cart = (CartItems) ss.getAttribute("cart");
		return "redirect:/checkout/view";
	}

	@GetMapping("/checkout/view")
	public String checkoutView(Model model) {
		CartItems cart = (CartItems) ss.getAttribute("cart");
		model.addAttribute("cartItems", cart);
		model.addAttribute("user", ss.getAttribute("user"));
		return "checkout";
	}
	
	@PostMapping("/placeorder")
	public String placeOrder(
	    HttpSession session, 
	    RedirectAttributes ra,
	    @RequestParam("fullName") String fullName,
	    @RequestParam("phone") String phone,
	    @RequestParam("email") String email,
	    @RequestParam("province") String provinceCode,
	    @RequestParam("district") String districtCode,
	    @RequestParam("ward") String wardCode,
	    @RequestParam("addressDetail") String addressDetail,
	    @RequestParam(value = "payment", defaultValue = "cod") String payment) {
	    
	    CartItems cart = (CartItems) session.getAttribute("cart");
	    Account user = (Account) session.getAttribute("user");
	    
	    if (cart == null || cart.getItems().isEmpty()) {
	        ra.addFlashAttribute("message", "Giỏ hàng trống, không thể đặt hàng!");
	        return "redirect:/cart/view";
	    }
	    
	    if (user == null) {
	        ra.addFlashAttribute("message", "Vui lòng đăng nhập để đặt hàng!");
	        return "redirect:/login";
	    }
	    
	    try {
	        // Lấy thông tin địa chỉ đầy đủ từ API
	        RestTemplate restTemplate = new RestTemplate();
	        String provinceUrl = "https://provinces.open-api.vn/api/p/" + provinceCode + "?depth=1";
	        String districtUrl = "https://provinces.open-api.vn/api/d/" + districtCode + "?depth=1";
	        String wardUrl = "https://provinces.open-api.vn/api/w/" + wardCode + "?depth=1";
	        
	        ProvinceDTO province = restTemplate.getForObject(provinceUrl, ProvinceDTO.class);
	        DistrictDTO district = restTemplate.getForObject(districtUrl, DistrictDTO.class);
	        WardDTO ward = restTemplate.getForObject(wardUrl, WardDTO.class);
	        
	        // Tạo địa chỉ đầy đủ
	        StringBuilder fullAddress = new StringBuilder();
	        fullAddress.append(fullName).append(" | ");
	        fullAddress.append(phone).append(" | ");
	        fullAddress.append(email).append(" | ");
	        fullAddress.append(addressDetail).append(", ");
	        
	        if (ward != null) {
	            fullAddress.append(ward.getName()).append(", ");
	        }
	        
	        if (district != null) {
	            fullAddress.append(district.getName()).append(", ");
	        }
	        
	        if (province != null) {
	            fullAddress.append(province.getName());
	        }
	        
	        // Tạo và lưu đơn hàng
	        Order order = new Order();
	        order.setCreateDate(new Date());
	        order.setAddress(fullAddress.toString());
	        order.setAccount(user);
	        
	        // Nếu entity Order có trường status và paymentMethod, bạn có thể thêm
	        // order.setStatus("Pending");
	        // order.setPaymentMethod(payment);
	        
	        Order savedOrder = orderDAO.save(order);
	        
	        // Lưu chi tiết đơn hàng từ giỏ hàng
	        // Bạn cần đảm bảo có OrderDetailDAO được autowired
	        /*
	        for (Object item : cart.getItems()) {
	            // Chuyển đổi item thành Map
	            @SuppressWarnings("unchecked")
	            Map<String, Object> cartItem = (Map<String, Object>) item;
	            
	            OrderDetail detail = new OrderDetail();
	            detail.setOrder(savedOrder);
	            detail.setProduct((Product) cartItem.get("product"));
	            detail.setPrice(((Product) cartItem.get("product")).getPrice());
	            detail.setQuantity((Integer) cartItem.get("qty"));
	            orderDetailDAO.save(detail);
	        }
	        */
	        
	        // Xóa giỏ hàng sau khi đặt hàng thành công
	        session.removeAttribute("cart");
	        
	        ra.addFlashAttribute("message", "Đặt hàng thành công! Mã đơn hàng của bạn là: " + savedOrder.getId());
	        return "redirect:/";
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        ra.addFlashAttribute("message", "Đặt hàng thất bại: " + e.getMessage());
	        return "redirect:/cart/checkout/view";
	    }
	}
}
