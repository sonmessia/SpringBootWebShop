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
import asm.entity.Account;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	@Autowired
	AccountDAO ad;

	@GetMapping("/view")
	public String user(Model model) {
		model.addAttribute("users", ad.findAll());
		return "admin/user";
	}
	
	@PostMapping("/add")
	public String add(Account a, RedirectAttributes ra) {
		a.setPhoto("user.png");
		ad.save(a);
		ra.addAttribute("message", "Add success");
		return "redirect:/admin/user/view";
	}
	
	@PostMapping("/edit")
	public String edit(Account a, RedirectAttributes ra) {
		a.setPhoto("user.png");
		ad.save(a);
		ra.addAttribute("message", "Edit suscess");
		return "redirect:/admin/user/view";
	}
	
	@GetMapping("/delete/{username}")
	public String remove(@PathVariable("username") String id, RedirectAttributes ra) {
		Optional<Account> a = ad.findByUserName(id);
		ad.delete(a.get());
		ra.addAttribute("message", "Delete Success");
		return "redirect:/admin/user/view";
	}
	
	
}
