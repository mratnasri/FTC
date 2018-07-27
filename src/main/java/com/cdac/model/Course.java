package com.cdac.model;

import java.time.LocalDate;

public class Course {
	
	int course_id;     
	String course_name;    
	int subjectId;      
	String tagline  ;     
	String description ;   
	LocalDate start_date ;    
	LocalDate end_date ;    
	LocalDate reg_start_date; 
	LocalDate reg_end_date;   
	String banner_path ; 
	String creator;
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = LocalDate.parse(start_date);
		
	}
	public LocalDate getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = LocalDate.parse(end_date);
	}
	public LocalDate getReg_start_date() {
		return reg_start_date;
	}
	public void setReg_start_date(String reg_start_date) {
		this.reg_start_date = LocalDate.parse(reg_start_date);
	}
	public LocalDate getReg_end_date() {
		return reg_end_date;
	}
	public void setReg_end_date(String reg_end_date) {
		this.reg_end_date = LocalDate.parse(reg_end_date);
	}
	public String getBanner_path() {
		return banner_path;
	}
	public void setBanner_path(String banner_path) {
		this.banner_path = banner_path;
	}
	  
}
