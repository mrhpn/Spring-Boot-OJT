package gic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AccountController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String init() {
		return "account/account";
	}
	
	@RequestMapping(value="/accounts", method=RequestMethod.GET)
	public String getAccounts(Model model) {
		String[] accounts = {"Htet Phyo Naing", "Nay Toe", "Aung Ye Lin", "Myint Myat"};
		
		model.addAttribute("accounts", accounts);
		return "account/accounts-list";
	}
}
