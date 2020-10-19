package gic.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dto.AccountDTO;
import gic.dao.Account;
import gic.service.AccountService;

@Controller
@RequestMapping("/")
public class AccountRegisterController {
	@Autowired
	public AccountService service;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String init(Model model) {
		model.addAttribute(new AccountDTO());
		model.addAttribute("page", "Register Account");
		model.addAttribute("roles", new Account().getRoles());
		
		return "account/account";
	}
	
	@RequestMapping(value="/accountRegister", params="register", method=RequestMethod.GET)
	public String register(Model model, @Valid AccountDTO accountDTO, BindingResult bindingResult, RedirectAttributes redirectAttr) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("page", "Create Account");
			
			// Server-Side Error Handling
			List<FieldError> errors = bindingResult.getFieldErrors();
			
			for (FieldError e: errors) {
				if (("name").equals(e.getField()))
					model.addAttribute("nameError", "This value is required and must be between 5 and 20 of length.");
				else if (("password").equals(e.getField()))
					model.addAttribute("passwordError", "This value is required and must be between 8 and 20 of length.");
			}

			model.addAttribute("roles", new Account().getRoles());
			
			return "account/account";
		}
		
		// Hashing password
		String hashedPassword = generateHash(accountDTO.getPassword());
		accountDTO.setPassword(hashedPassword);
		service.register(accountDTO);
		
		redirectAttr.addAttribute("message", "New account has been successfully created.");
		return "redirect:/accounts";
	}
	
	private static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			
			for (int i = 0; i < hashedBytes.length; i++) {
				byte b = hashedBytes[i];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}

		return hash.toString();
	}
}
