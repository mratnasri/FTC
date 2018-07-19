package com.cdac.init;

import java.util.LinkedHashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cdac.db.DbOperations;

public class Initializer implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private ServletContext context;
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	DbOperations dbopr = new DbOperations(); 
	public Initializer() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		LinkedHashMap<Integer, String> qual = new LinkedHashMap<Integer, String>();
		qual = dbopr.getQualification(jdbctemplate);
		
		this.context.setAttribute("qual", qual);
	}

}
