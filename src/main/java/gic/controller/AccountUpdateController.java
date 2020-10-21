package gic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gic.dao.Account;
import gic.dto.AccountDto;
import gic.service.AccountService;

@Controller
public class AccountUpdateController {
	
	@Autowired
	public AccountService service;

	@RequestMapping(value="/edit/{accountId}", method=RequestMethod.GET)
	public String edit(Model model, @PathVariable("accountId")Integer accountId) {	
		AccountDto account = service.edit(accountId);
		
		model.addAttribute("accountDTO", account);
		model.addAttribute("roles", new Account().getRoles());
		
		return "account/account-edit";
	}
	
	@RequestMapping(value="accountUpdate", method=RequestMethod.POST)
	public String updateData(Model model, @Valid AccountDto accountDTO, BindingResult bindingResult, RedirectAttributes redirectAttr) {
		if (bindingResult.hasErrors()) {
			FieldError error = bindingResult.getFieldError();
			if (("name").equals(error.getField()))
				model.addAttribute("nameError", "This value is required and must be between 5 and 20 of length.");

			model.addAttribute("roles", new Account().getRoles());
			return "redirect:/accountEdit";
		}
			
		service.update(accountDTO);
		
		redirectAttr.addAttribute("message", accountDTO.getName() + " has been successfully updated!");
		return "redirect:/accounts";
	}
}
