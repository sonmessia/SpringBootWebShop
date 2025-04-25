package asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import asm.dao.AccountDAO;
import asm.entity.Account;

@Controller
public class RegistratorController {
	
	@Autowired
	AccountDAO ad;
	
	@GetMapping("/registrator")
	public String registrator() {
		return "registrator";
	}
	
	@PostMapping("/registrator")
	public String registrator2(Account a, RedirectAttributes ra) {
		a.setPhoto("user.png");
		ad.save(a);
		ra.addAttribute("message", "Thanh Cong");
		return "redirect:/registrator";
	}
}
