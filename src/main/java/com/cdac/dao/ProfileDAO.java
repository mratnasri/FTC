package com.cdac.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdac.model.Profile;

public interface ProfileDAO {
	public boolean saveProfile(Profile profile,HttpServletRequest req,HttpServletResponse res);

	public boolean getQualification(HttpServletRequest req,HttpServletResponse res);

	
}
