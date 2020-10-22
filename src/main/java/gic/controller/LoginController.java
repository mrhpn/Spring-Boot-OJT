package gic.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gic.security.LoginUserDetails;

@Controller
public class LoginController {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="/login",  method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Authentication auth, HttpServletRequest req) {
		session.invalidate();
		if(auth != null) {
			try {
				req.logout();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		return "home/login.html";
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String loginSuccess(@AuthenticationPrincipal LoginUserDetails user, RedirectAttributes redirectAttr) {
		if(user.isSysAdmin()) {
			redirectAttr.addAttribute("message", "Successfully logged in as admin.");
			return "redirect:/accounts";		
		} else {
			redirectAttr.addAttribute("message", "Successfully logged in as user.");
			return "redirect:/accounts";
		}				
	}
	
	@RequestMapping(value="/loginError", method = RequestMethod.GET)
	public String loginError(Model model) {
		model.addAttribute("message", "User name and password are invalid!");
		return "home/login.html";			
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String loginOut(@AuthenticationPrincipal LoginUserDetails user) {
		return "redirect:/login";							
	}

}
