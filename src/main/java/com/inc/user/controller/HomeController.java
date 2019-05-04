/**
 * 
 */
package com.inc.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inc.user.model.User;

/**
 * @author Ananthan
 *
 */
@Controller
public class HomeController extends BaseController {
	
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String onEntry(Model model, HttpServletRequest request) {
		User user = new User();
		return "Login";
	}*/

}
