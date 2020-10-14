package gic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.AccountDTO;

@Controller
@RequestMapping("/")
public class AccountController {
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String init(Model model) {
		model.addAttribute("accountDTO", new AccountDTO());
		return "account/account";
	}
	
	@RequestMapping(value="/accountRegister", params="register", method=RequestMethod.GET)
	public String register(Model model, @Valid AccountDTO accountDTO, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute(
					"errorMessage", 
					"<p style='color:red'>Possible Reasons:"
					+ "<br/>Name and Role are all required!"
					+ "<br/>Name must be between 5 and 20 of length.</p>");
			return "account/account";
		}
		
		return "redirect:/accounts";
	}
	
	@RequestMapping(value="/accounts", method=RequestMethod.GET)
	public String getAll(Model model) {
		List<AccountDTO> accounts = getList();
		model.addAttribute("accounts", accounts);
		
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
