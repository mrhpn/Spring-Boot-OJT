package gic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dto.AccountDTO;
import gic.service.AccountService;

@Controller
public class AccountListController {
	
	@Autowired
	private AccountService service;
	
	@RequestMapping(value="/accounts", method=RequestMethod.GET)
	public String getAccounts(Model model, @RequestParam("message")String message) {
		List<AccountDTO> accounts = service.getAccountList();
		model.addAttribute("accounts", accounts);
		model.addAttribute("message", message);
		
		return "account/accounts-list";
	}
}
