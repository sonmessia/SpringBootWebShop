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

import asm.dao.AccountDAO;
import asm.dao.OrderDAO; // Thêm dòng này
import asm.dao.ProductDAO;
import asm.entity.Account;
import asm.entity.Order;
import asm.entity.Product;
import asm.utils.CartItems;

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
	public String placeOrder(HttpSession session, RedirectAttributes ra) {
	    CartItems cart = (CartItems) session.getAttribute("cart");
	    Account user = (Account) session.getAttribute("user"); // Cast to Account type
	    
	    if (cart != null && user != null) {
	        // Create and save order
	        Order order = new Order();
	        order.setCreateDate(new Date());
			// Get address from the cart or set a default address
			order.setAddress("Default Address"); // Replace with actual address if available
	        
	        // Use the account object directly from session
	        order.setAccount(user);
	        
	        orderDAO.save(order);
	    }
	    
	    session.removeAttribute("cart"); // Clear cart after order
	    ra.addFlashAttribute("message", "Order placed successfully!");
	    return "redirect:/";
	}
}
