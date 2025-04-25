package asm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asm.dao.ProductDAO;
import asm.entity.Product;
import asm.utils.CartItems;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
	ProductDAO pd;
	
	@Autowired
	HttpSession ss;

    @GetMapping("/view")
    public String index(Model model) {
        model.addAttribute("cartItems", ss.getAttribute("cart"));
        model.addAttribute("user", ss.getAttribute("user"));
        return "checkout";
    }
    // @GetMapping("/")
    // public String checkout(Model model) {
    //     model.addAttribute("user", ss.getAttribute("user"));
    //     CartItems cart = (CartItems) ss.getAttribute("cart");
	// 	return "redirect:/checkout/view";
    // }

}
