package com.cdac.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session= request.getSession();

		if(session.getAttribute("username")!=null && session.getAttribute("isLoggedIn")!=null ){
			//user has already logged in . so therefore can access any resource
			System.out.println("Logged In");
			if (request.getRequestURI().endsWith("/login")){
				response.sendRedirect(request.getContextPath()+"/profile");
				return false;
			}
			if (request.getRequestURI().endsWith("/registration")){
				response.sendRedirect(request.getContextPath()+"/profile");
				return false;
			}
			return true;
		}

		//if code reaches here means that user is not logged in
		if (request.getRequestURI().endsWith("/")){

			return true;
		}
		if (request.getRequestURI().endsWith("/home")){

			return true;
		}

		//allow login http request.
		if (request.getRequestURI().endsWith("/login")){
			//user is not logged in but is trying to login. so allow only login requests
			return true;
		}
		if (request.getRequestURI().endsWith("/dologin")){

			return true;
		}
		if (request.getRequestURI().endsWith("/registration")){

			return true;
		}
		if (request.getRequestURI().endsWith("/register")){

			return true;
		}
		else{
			//user is not logged in and is trying to access a resource. so redirect him to login page
			response.sendRedirect(request.getContextPath()+"/login");
			System.out.println("Not logged in");
			return false;
		}
	}

}
