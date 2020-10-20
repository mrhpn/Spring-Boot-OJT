package gic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gic.service.AccountService;

@Controller
public class AccountDeleteController {
	
	@Autowired
	public AccountService service;

	@RequestMapping(value="/delete/{accountId}", method=RequestMethod.GET)
	public String delete(Model model, @PathVariable("accountId")Integer accountId, RedirectAttributes redirectAttr) {
		service.delete(accountId);
		
		redirectAttr.addAttribute("message", "Selected account has been successfully deleted!");
		return "redirect:/accounts";
	}
}
