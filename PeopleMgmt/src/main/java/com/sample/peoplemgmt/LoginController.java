package com.sample.peoplemgmt;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sample.peoplemgmt.model.User;
import com.sample.peoplemgmt.service.LoginService;

@Controller
public class LoginController {

	private LoginService loginService;

	@Autowired(required=true)
	@Qualifier(value="loginService")
	public void setLoginService(LoginService ls){
		this.loginService = ls;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login", "user", new User()); 
	}

	@RequestMapping(value = "/loginAction", method = RequestMethod.POST)
	public ModelAndView loginAction(Model model, @ModelAttribute("user")User user, HttpSession session) {
		User loggedInUser = this.loginService.login(user.getEmail(), user.getPassword());

		if(null != loggedInUser){
			model.addAttribute("user", loggedInUser);
			session.setAttribute("userProfile", loggedInUser);
			return new ModelAndView("redirect:/persons");
		}else{
			return new ModelAndView("login", "user", new User()); 
		}
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logoutAction(HttpSession session){
		session.invalidate();

		return new ModelAndView("login", "user", new User()); 
	}
}
