package gic.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.Account;
import dto.AccountDTO;

@Controller
@RequestMapping("/")
public class AccountRegisterController {
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String init(Model model) {
		model.addAttribute(new AccountDTO());
		model.addAttribute("page", "Create Register");
		model.addAttribute("roles", new Account().getRoles());
		
		return "account/account";
	}
	
	@RequestMapping(value="/accountRegister", params="register", method=RequestMethod.GET)
	public String register(Model model, @Valid AccountDTO accountDTO, BindingResult result, RedirectAttributes redirectAttr) {
		if (result.hasErrors()) {
			model.addAttribute("page", "Create Account");
			model.addAttribute(
					"errorMessage", 
					"<p style='color:red'>Possible Reasons:"
					+ "<br/>Name and Role are all required!"
					+ "<br/>Name must be between 5 and 20 of length.</p>");
			model.addAttribute("roles", new Account().getRoles());
			
			return "account/account";
		}
		
		redirectAttr.addAttribute("message", "New account has been successfully created.");
		return "redirect:/accounts";
	}
}
