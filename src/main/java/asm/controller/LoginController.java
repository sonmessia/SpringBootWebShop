package asm.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import asm.dao.AccountDAO;
import asm.entity.Account;
import asm.utils.Auth;

@Controller
public class LoginController {
	
	@Autowired
	AccountDAO ad;
	
	@Autowired
	HttpSession ss;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login2(Account a, RedirectAttributes ra) {
		Optional<Account> account = ad.checkLogin(a.getUsername(), a.getPassword());
		if(account.orElse(new Account()).getUsername() != null) {
			ss.setAttribute("user", account.get());
			if(account.get().isAdmin()) {
				return "redirect:/admin/user/view";
			} else {
				return "redirect:/";
			}
		} else {
			ra.addAttribute("message", "User name or password is wrong");
			return "redirect:/login";
		}
	}

}
