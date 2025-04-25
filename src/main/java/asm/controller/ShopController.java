package asm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asm.dao.CategoryDAO;
import asm.dao.ProductDAO;
import asm.entity.Category;
import asm.entity.Product;
import asm.utils.CartItems;

@Controller
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	ProductDAO pd;
	@Autowired
	CategoryDAO cd;
	@Autowired
	HttpSession ss;
	
	@GetMapping(value = "/{page}")
	public String shop(@PathVariable("page") int page, Model model) {
		model.addAttribute("user", ss.getAttribute("user"));
		Pageable pageable = PageRequest.of(page, 6);
		Page<Product> ps = pd.findAll(pageable);
		model.addAttribute("ps", ps);
		model.addAttribute("actionNumber", page);
		model.addAttribute("cs", cd.findAll());
		CartItems cart = (CartItems) ss.getAttribute("cart");
		if (cart == null) {
			cart = new CartItems();
			ss.setAttribute("cart", cart);
		}
		model.addAttribute("total", cart.getTotal());
		return "shop";
	}
	
	@GetMapping(value = "/{page}", params = "category")
	public String shop2(@PathVariable("page") int page, @RequestParam("category") String ci, Model model) {
		model.addAttribute("user", ss.getAttribute("user"));
		Pageable pageable = PageRequest.of(page, 6);
		Page<Product> ps = pd.findByCategoryId(ci, pageable);
		model.addAttribute("ps", ps);
		model.addAttribute("actionNumber", page);
		model.addAttribute("cs", cd.findAll());
		CartItems cart = (CartItems) ss.getAttribute("cart");
		if (cart == null) {
			cart = new CartItems();
			ss.setAttribute("cart", cart);
		}
		model.addAttribute("total", cart.getTotal());
		model.addAttribute("ct", ci);
		return "shop";
	}
	
	@GetMapping(value = "/{page}", params = {"price", "category"})
	public String shop3(@PathVariable("page") int page, @RequestParam("category") String ci, @RequestParam("price") String pri, Model model) {
		Pageable pageable = PageRequest.of(page, 6);
		model.addAttribute("user", ss.getAttribute("user"));
		Page<Product> ps = pd.findByCategoryId2(ci, Double.valueOf(pri.split(",")[0]), Double.valueOf(pri.split(",")[1]), pageable);
		model.addAttribute("ps", ps);
		model.addAttribute("actionNumber", page);
		model.addAttribute("cs", cd.findAll());
		model.addAttribute("ct", ci);
		CartItems cart = (CartItems) ss.getAttribute("cart");
		if (cart == null) {
			cart = new CartItems();
			ss.setAttribute("cart", cart);
		}
		model.addAttribute("total", cart.getTotal());
		model.addAttribute("price", pri);
		return "shop";
	}
	
	@GetMapping(value = "/{page}", params = "price")
	public String shop3(@PathVariable("page") int page, @RequestParam("price") String pri, Model model) {
		Pageable pageable = PageRequest.of(page, 6);
		Page<Product> ps = pd.findByCategoryId3(Double.valueOf(pri.split(",")[0]), Double.valueOf(pri.split(",")[1]), pageable);
		model.addAttribute("ps", ps);
		model.addAttribute("actionNumber", page);
		model.addAttribute("cs", cd.findAll());
		model.addAttribute("price", pri);
		CartItems cart = (CartItems) ss.getAttribute("cart");
		if (cart == null) {
			cart = new CartItems();
			ss.setAttribute("cart", cart);
		}
		model.addAttribute("total", cart.getTotal());
		return "shop";
	}
	
}
