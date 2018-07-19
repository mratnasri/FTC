package com.cdac.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.dao.ProfileDAO;
import com.cdac.model.Profile;

@Service("ps")
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	ProfileDAO pdo;
	@Override
	public boolean saveProfile(Profile profile,HttpServletRequest req,HttpServletResponse res) {
		// TODO Auto-generated method stub
		return (pdo.saveProfile(profile,req,res));
	}
	@Override
	public boolean getQualification(HttpServletRequest req,HttpServletResponse res) {
		// TODO Auto-generated method stub
		return (pdo.getQualification(req,res));
	}
}
