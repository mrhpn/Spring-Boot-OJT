package gic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.AccountDTO;
import gic.dao.Account;

@Controller
public class AccountUpdateController {

	@RequestMapping(value="/update/{accountId}", method=RequestMethod.GET)
	public String update(Model model, @PathVariable("accountId")Integer accoungId) {
		model.addAttribute("page", "Update Account");
		model.addAttribute("accountDTO", new AccountDTO());
		model.addAttribute("roles", new Account().getRoles());
		
		return "account/account";
	}
}
