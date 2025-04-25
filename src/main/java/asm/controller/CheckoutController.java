package asm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import asm.dto.ProvinceDTO;
import asm.service.AddressService;
import asm.utils.CartItems;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    
    @Autowired
    private AddressService addressService;
    
    @GetMapping("/view")
    public String checkoutView(Model model, HttpSession session) {
        CartItems cart = (CartItems) session.getAttribute("cart");
        List<ProvinceDTO> provinces = addressService.getProvinces();
        
        model.addAttribute("cartItems", cart);
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("provinces", provinces);
        return "checkout";
    }
}
