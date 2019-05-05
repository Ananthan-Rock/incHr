/**
 * 
 */
package com.inc.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inc.user.model.User;

/**
 * @author Ananthan
 *
 */
@Controller
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String onEntry(Model model, HttpServletRequest request) {
		User user = new User();
		return "Login";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String defaultPage(Model model) {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  String welcomeMessage = "Welcome "+authentication.getName()+"!!";
	  model.addAttribute("title", "Spring Security Login Form - Database Authentication");
	  model.addAttribute("message", "This is default page!");
	  return "hello";
	}
	
	@RequestMapping("/error")
    public String error(Model model) {
        model.addAttribute("error", "true");
        return "Login";
    }
	
	@RequestMapping("/403")
	@ResponseBody
    public String accessDenied(Model model) {
        model.addAttribute("error", "true");
        return "Access Denied page";
    }
	
	@RequestMapping("/logout")
    public String logout(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.setAuthenticated(false);
        model.addAttribute("logout", "true");
        return "Login";
    }
	
	@RequestMapping(value = "/readFile", method = RequestMethod.GET)
	@ResponseBody
	public String readFile(Model model) {
	  return "Read File completed";

	}

	/*@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

	  ModelAndView model = new ModelAndView();
	  model.addObject("title", "Spring Security Login Form - Database Authentication");
	  model.addObject("message", "This page is for ROLE_ADMIN only!");
	  model.setViewName("admin");
	  return model;

	}*/

/*	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

	  ModelAndView model = new ModelAndView();
	  if (error != null) {
		model.addObject("error", "Invalid username and password!");
	  }

	  if (logout != null) {
		model.addObject("msg", "You've been logged out successfully.");
	  }
	  model.setViewName("login");

	  return model;

	}*/
	
	//for 403 access denied page
	/*@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

	  ModelAndView model = new ModelAndView();
		
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }
		
	  model.setViewName("403");
	  return model;

	}*/

}
