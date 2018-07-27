package com.cdac.controller;

/**
	@author Ratna
**/

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cdac.model.Course;
import com.cdac.model.GeneralObject;
import com.cdac.model.Profile;
import com.cdac.model.User;
import com.cdac.service.AttendeeService;
import com.cdac.service.LoginService;
import com.cdac.service.OrganizerService;
import com.cdac.service.ProfileService;
import com.cdac.service.RegistrationService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static String UPLOAD_LOCATION="/home/sanketc/Documents/workspace-sts-3.8.4.RELEASE/FTC/src/main/webapp/resources/images";

	@Autowired
	@Qualifier("rs")
	RegistrationService rs;
	@Autowired
	@Qualifier("ls")
	LoginService ls;
	@Autowired
	@Qualifier("ps")
	ProfileService ps;
	@Autowired
	@Qualifier("os")
	OrganizerService os;
	@Autowired
	@Qualifier("as")
	AttendeeService as;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/" ,method = RequestMethod.GET)
	public String start() {

		return "home";
	}

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home()
	{
		return "home";
	}
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public String registration()
	{
		return "registration";
	}
	@RequestMapping(value="/courses", method = RequestMethod.GET)
	public String courses(HttpServletRequest req,HttpServletResponse res)
	{
		as.getCourses(req,res);
		return "courses";
	}
	@RequestMapping(value="/course", method = RequestMethod.GET)
	public ModelAndView course(@RequestParam("selectedCourse") int selectedCourse, HttpServletRequest req,HttpServletResponse res)
	{
		String description[] = as.splitDescription(selectedCourse,req,res);
		ModelAndView mv = new ModelAndView();
		mv.addObject("description",description);
		mv.setViewName("course");

		return mv;
	}
	@RequestMapping(value="/enroll", method = RequestMethod.GET)
	public ModelAndView enroll(HttpServletRequest req,HttpServletResponse res)
	{
		LinkedHashMap<Integer,Integer> courseCategories = as.getCourseCategories(req,res);
		os.getCategories(req, res);
		ModelAndView mv = new ModelAndView();
		mv.addObject("courseCategories",courseCategories);
		mv.setViewName("enroll");

		return mv;
	}
	@RequestMapping(value="/saveEnrollment", method = RequestMethod.POST)
	public ModelAndView saveEnrollment(@RequestParam("category")int selectedCategory,@RequestParam("laptop") boolean laptop, HttpServletRequest req,HttpServletResponse res)
	{
		ModelAndView mv = new ModelAndView();
		if(as.saveEnrollment(selectedCategory,laptop,req,res))
			mv.setViewName("payment");
		else
		{
			mv.setViewName("enroll");
			mv.addObject("errorMsg","Enrollment was not successful");
		}
		return mv;
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

	@RequestMapping(value="/createSubject", method = RequestMethod.GET)
	public String createSubject()
	{
		return "createSubject";
	}

	@RequestMapping(value="/saveSubject", method = RequestMethod.POST)
	public ModelAndView saveSubject(@ModelAttribute GeneralObject subject)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("createSubject");
		if(os.saveSubject(subject))
		{				
			mv.addObject("successMsg","subject is successfully created.");
		}

		else
		{				
			mv.addObject("errorMsg","Subject could not be created");
		}



		return mv;
	}

	@RequestMapping(value="/createQualification", method = RequestMethod.GET)
	public String createQualification()
	{
		return "createQualification";
	}

	@RequestMapping(value="/saveQualification", method = RequestMethod.POST)
	public ModelAndView saveQualification(@ModelAttribute GeneralObject qualification)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("createQualification");
		if(os.saveQualification(qualification))
		{				
			mv.addObject("successMsg","qualification is successfully created.");
		}

		else
		{				
			mv.addObject("errorMsg","qualification could not be created");
		}

		return mv;
	}

	@RequestMapping(value="/createCategory", method = RequestMethod.GET)
	public String createCategory()
	{
		return "createCategory";
	}

	@RequestMapping(value="/saveCategory", method = RequestMethod.POST)
	public ModelAndView saveCategory(@ModelAttribute GeneralObject category)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("createCategory");
		if(os.saveCategory(category))
		{				
			mv.addObject("successMsg","Category is successfully created.");
		}

		else
		{				
			mv.addObject("errorMsg","Category could not be created");
		}

		return mv;
	}

	@RequestMapping(value="/createCourse", method = RequestMethod.GET)
	public String createCourse(HttpServletRequest req,HttpServletResponse res)
	{
		os.getSubjects(req,res);
		os.getCategories(req,res);
		return "createCourse";
	}

	@RequestMapping(value="/saveCourse", method = RequestMethod.POST)
	public ModelAndView saveCourse(@ModelAttribute Course course,@RequestParam("image")MultipartFile file,  
			HttpSession session,@RequestParam Map<String,String> categoryFees, HttpServletRequest req, HttpServletResponse res)throws Exception
	{
		/*res.setContentType("application/x-png");
		res.setHeader("Content-disposition","inline;filenam");*/
		FileCopyUtils.copy(file.getBytes(), new File(UPLOAD_LOCATION +"/"+ file.getOriginalFilename()));

		course.setBanner_path(file.getOriginalFilename());
		System.out.println("path==>>" + UPLOAD_LOCATION + "/" + file.getOriginalFilename());
		ModelAndView mv=new ModelAndView();
		mv.setViewName("createCourse");


		if(os.saveCourse(course,categoryFees,req,res))
		{				
			mv.addObject("successMsg","Course is successfully created.");
		}

		else
		{				
			mv.addObject("errorMsg","Course could not be created");
		}

		return mv;
	}


	@RequestMapping(value="/downloadImage/{filename}", method = RequestMethod.GET)
	public void download(HttpServletRequest req,HttpServletResponse res)
	{
		os.getSubjects(req,res);
		os.getCategories(req,res);

	}

	@RequestMapping(value = "/analysisReport" ,method = RequestMethod.GET)
	public String analysisReport() {

		return "analysisReport";
	}

	@RequestMapping(value = "/archivedCourses" ,method = RequestMethod.GET)
	public String archivedCourses() {

		return "archivedCourses";
	}

	@RequestMapping(value = "/attendeeData" ,method = RequestMethod.GET)
	public String attendeeData() {

		return "attendeeData";
	}

	@RequestMapping(value = "/changePassword" ,method = RequestMethod.GET)
	public String changePassword() {

		return "changePassword";
	}

	@RequestMapping(value = "/contact" ,method = RequestMethod.GET)
	public String contact() {

		return "contact";
	}
	@RequestMapping(value = "/myCourses" ,method = RequestMethod.GET)
	public String myCourses() {

		return "myCourses";
	}
	@RequestMapping(value = "/payment" ,method = RequestMethod.GET)
	public String payment() {

		return "payment";
	}
	@RequestMapping(value = "/recoverPassword" ,method = RequestMethod.GET)
	public String recoverPassword() {

		return "recoverPassword";
	}
	@RequestMapping(value = "/upcomingCourses" ,method = RequestMethod.GET)
	public String upcomingCourses() {

		return "upcomingCourses";
	}
	@RequestMapping(value = "/updateAttendance" ,method = RequestMethod.GET)
	public String updateAttendance() {

		return "updateAttendance";
	}
	@RequestMapping(value = "/updateCourse" ,method = RequestMethod.GET)
	public String updateCourse() {

		return "updateCourse";
	}
}
