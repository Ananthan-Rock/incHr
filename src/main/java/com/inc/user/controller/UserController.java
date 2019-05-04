/**
 * 
 */
package com.inc.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inc.user.helper.UserHelper;
import com.inc.user.model.User;
import com.inc.user.service.UserService;

/**
 * @author Ananthan
 *
 */
@Controller
public class UserController {
	
	private UserService userService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService ps){
		this.userService = ps;
	}
	
	@RequestMapping(value = "/GetUsers", method = RequestMethod.GET)
	public String getUsers(Model model) {
		model.addAttribute("user", new User());
		List<User> userss = this.userService.getUsers();
		System.out.println("From hibernate : "+userss.size());
		UserHelper helper = new UserHelper();
		List<User> users =  helper.getUsers();
		System.out.println("From helper : "+users.size());
		return "Login";
	}

}
