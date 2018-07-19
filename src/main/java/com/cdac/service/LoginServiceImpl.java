package com.cdac.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdac.dao.LoginDAO;

@Service("ls")
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginDAO ldo;
	@Override
	public boolean loginUser(HttpServletRequest req,HttpServletResponse res) {
		// TODO Auto-generated method stub
		return (ldo.loginUser(req,res));
	}

}
