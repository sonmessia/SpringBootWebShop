package asm.controller.admin;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import asm.dao.CategoryDAO;
import asm.dao.ProductDAO;
import asm.entity.Product;

import asm.utils.SaveFile;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
	
	int id; 
	@Autowired
	CategoryDAO cd;
	@Autowired
	ProductDAO pd;
	@Autowired
	SaveFile sf;
	
	@GetMapping("/view")
	public String view(Model model) {
		model.addAttribute("user", pd.findById(this.id).orElse(new Product()));
		model.addAttribute("products", pd.findAll(Sort.by(Direction.DESC, "id")));
		model.addAttribute("categories", cd.findAll());
		return "admin/product";
	}
	
	@PostMapping("/add")
	public String add(Product p, RedirectAttributes ra, @RequestParam("file") MultipartFile file, @RequestParam("cate") String id) {

		try {
			sf.save(file, "/resources/img");
			p.setImage(file.getOriginalFilename());
			p.setCategory(cd.findById(id).get());
			pd.save(p);
			ra.addAttribute("message", "Add Success");
			System.out.println("Add Success");
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/admin/product/view";
	}
	
	@PostMapping("/update")
	public String edit(Product p, RedirectAttributes ra, @RequestParam("file") MultipartFile file, @RequestParam("cate") String id) {
		try {
			sf.save(file, "/resources/img");
			p.setImage(file.getOriginalFilename());
			p.setCategory(cd.findById(id).get());
			pd.save(p);
			ra.addAttribute("message", "update Success");
			System.out.println("update Success");
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/admin/product/view";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, RedirectAttributes ra) {
		Optional<Product> p = pd.findById(id);
		pd.delete(p.get());
		ra.addAttribute("message", "delete Success");
		return "redirect:/admin/product/view";
	}
	
	@GetMapping("/edit/{id}")
	public String edit2(@PathVariable("id") int id, RedirectAttributes ra) {
		this.id = id;
		return "redirect:/admin/product/view";
	}
	
	@PostMapping("/reset")
	public String reset() {
		this.id = 0;
		return "redirect:/admin/product/view";
	}

}
