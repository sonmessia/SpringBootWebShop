package asm.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import asm.dao.ProductDAO;
import asm.entity.Product;
import asm.utils.CartItems;

@Controller
public class DetailController {
	
	@Autowired
	ProductDAO pd;
	@Autowired
	HttpSession ss;
	
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") int id, Model model) {
		Product p = pd.findById(id).orElse(new Product());
		model.addAttribute("user", ss.getAttribute("user"));
		model.addAttribute("p", p);
		model.addAttribute("cartNumber", ((CartItems) ss.getAttribute("cart")).getTotal());
		return "detail";
	}
}
