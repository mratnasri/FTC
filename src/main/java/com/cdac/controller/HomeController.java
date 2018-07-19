package com.cdac.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cdac.model.Profile;
import com.cdac.model.User;
import com.cdac.service.LoginService;
import com.cdac.service.ProfileService;
import com.cdac.service.RegistrationService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	@Qualifier("rs")
	RegistrationService rs;
	@Autowired
	@Qualifier("ls")
	LoginService ls;
	@Autowired
	@Qualifier("ps")
	ProfileService ps;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/" ,method = RequestMethod.GET)
	public String welcome(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "welcome";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model)
	{
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "welcome";
	}
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public String registration()
	{
		return "registration";
	}
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login()
	{
		return "login";
	}
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req,HttpServletResponse res)
	{
		HttpSession session= req.getSession();
		session.removeAttribute("username");
		session.removeAttribute("isLoggedIn");
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, max-age=0, post-check=0, pre-check=0");
		 res.setHeader("Pragma","no-cache");
		 res.setHeader("Expires", "0");
		session.invalidate();
		return "login";
	}
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public String profile()
	{
		return "profile";
	}
	@RequestMapping(value="/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfile(HttpServletRequest req,HttpServletResponse res)
	{
		ModelAndView mv=new ModelAndView();
		
		 mv.setViewName("editProfile");
		
		return mv;
	}
	@RequestMapping(value="/dologin", method = RequestMethod.POST)
	public ModelAndView dologin(@RequestParam("username")String username,@RequestParam("password")String pass,HttpServletRequest req,HttpServletResponse res)
	{
		ModelAndView mv=new ModelAndView();

		req.setAttribute("username", username);
		req.setAttribute("pass",pass);
		if(ls.loginUser(req,res))
		{
			ps.getQualification(req,res);
			mv.setViewName("profile");
		}

		else
		{
			mv.setViewName("login");
			mv.addObject("errorMsg","username or password is incorrect");
		}

		return mv;
	}
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute User user,@RequestParam("confirmPass")String pass)
	{
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("user",user);
		if(pass.equals(user.getPassword()))
		{
			if(rs.registerUser(user))
			{
				mv.setViewName("login");
				mv.addObject("successMsg","Hi "+user.getName()+", your registration is successful.");
			}

			else
				mv.setViewName("registration");

		}
		else
		{
			mv.setViewName("registration");
			mv.addObject("errorMsg","Password does not match");
		}
		return mv;
	}
	@RequestMapping(value="/saveProfile", method = RequestMethod.POST)
	public ModelAndView saveProfile(@ModelAttribute("profile") Profile profile,HttpServletRequest req,HttpServletResponse res)
	{HttpSession session= req.getSession();
		ModelAndView mv=new ModelAndView();
		
			if(ps.saveProfile(profile,req,res))
			{
				mv.setViewName("profile");
				mv.addObject("successMsg","Hi "+session.getAttribute("firstName")+", your profile is successfully saved.");
			}

			else
			{
				mv.setViewName("editProfile");
				mv.addObject("errorMsg","Profile could not be saved");
			}
		

		
		return mv;
	}
	
	
	
}
