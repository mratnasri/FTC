package com.cdac.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginDAO {

	boolean loginUser(HttpServletRequest req,HttpServletResponse res);

}
