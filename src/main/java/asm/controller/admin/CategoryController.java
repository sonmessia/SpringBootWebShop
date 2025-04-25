package asm.controller.admin;

import java.util.Optional;

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
import asm.dao.CategoryDAO;
import asm.entity.Category;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
	
	@Autowired
	CategoryDAO cd;

	@GetMapping("/view")
	public String user(Model model) {
		model.addAttribute("categories", cd.findAll());
		return "admin/category";
	}
	
	@PostMapping("/add")
	public String add(Category a, RedirectAttributes ra) {
		cd.save(a);
		ra.addAttribute("message", "Add success");
		return "redirect:/admin/category/view";
	}
	
	
	@PostMapping("/edit")
	public String edit(Category a, RedirectAttributes ra) {
		cd.save(a);
		ra.addAttribute("message", "Edit suscess");
		return "redirect:/admin/category/view";
	}
	
	@GetMapping("/delete/{id}")
	public String remove(@PathVariable("id") String id, RedirectAttributes ra) {
		Optional<Category> c = cd.findById(id);
		cd.delete(c.get());
		ra.addAttribute("message", "Delete Success");
		return "redirect:/admin/category/view";
	}
	
	
}
