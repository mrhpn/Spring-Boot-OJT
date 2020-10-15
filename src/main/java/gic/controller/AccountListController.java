package gic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dto.AccountDTO;

@Controller
public class AccountListController {
	
	@RequestMapping(value="/accounts", method=RequestMethod.GET)
	public String getAccounts(Model model, @RequestParam("message")String message) {
		List<AccountDTO> accounts = getList();
		model.addAttribute("accounts", accounts);
		model.addAttribute("message", message);
		
		return "account/accounts-list";
	}
	
	private List<AccountDTO> getList() {
		List<AccountDTO> result = new ArrayList<AccountDTO>();
		
		AccountDTO dto1 = new AccountDTO();
		dto1.setId(1);
		dto1.setName("John");
		dto1.setRole("ADMIN");
		
		AccountDTO dto2 = new AccountDTO();
		dto2.setId(2);
		dto2.setName("Mary");
		dto2.setRole("USER");
		
		result.add(dto1);
		result.add(dto2);
		
		return result;
	}
}
