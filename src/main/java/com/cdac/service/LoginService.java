package com.cdac.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

	

	boolean loginUser(HttpServletRequest req, HttpServletResponse res);

}
