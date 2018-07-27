package com.cdac.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.cdac.model.Course;
import com.cdac.model.GeneralObject;
@Repository
public class AttendeeDAOImpl implements AttendeeDAO {
	@Autowired
	JdbcTemplate jdbctemplate;
	@Override
	public void getCourses(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		try{
			List<Course> courses = this.jdbctemplate.query(
					"select * from course",
					new RowMapper<Course>() {
						public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
							Course courses = new Course();
							courses.setCourse_id(rs.getInt("course_id"));
							courses.setCourse_name(rs.getString("course_name"));
							courses.setSubjectId(rs.getInt("subject"));
							courses.setTagline(rs.getString("tagline"));
							courses.setDescription(rs.getString("description"));
							courses.setStart_date(rs.getString("start_date"));
							courses.setEnd_date(rs.getString("end_date"));
							courses.setReg_start_date(rs.getString("reg_start_date"));
							courses.setReg_end_date(rs.getString("reg_end_date"));
							courses.setBanner_path(rs.getString("banner_path"));
							System.out.println("Sys path"+rs.getString("banner_path"));
							courses.setCreator(rs.getString("creator"));
							return courses;
						}
					});

			
			HttpSession session= req.getSession();
			session.setAttribute("courses", courses);

		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
	}
	@Override
	public LinkedHashMap<Integer, Integer> getCourseCategories(HttpServletRequest req, HttpServletResponse res) {
		
		HttpSession session= req.getSession();
		String query = "select category_id, fees from course_category_fees where course_id = ?";
		Course course = (Course) session.getAttribute("course");
		LinkedHashMap<Integer, Integer> courseCategories = new LinkedHashMap<Integer, Integer>();
		SqlRowSet rs = jdbctemplate.queryForRowSet(query,new Object[]{course.getCourse_id()});
		while(rs.next()){
			courseCategories.put(rs.getInt("category_id"), rs.getInt("fees"));
		}
		return courseCategories;
		
	}
	@Override
	public boolean saveEnrollment(int selectedCategory, boolean laptop, HttpServletRequest req, HttpServletResponse res) {
		try {
			String query;
			int rowCount,enrollment_id;
			GeneralObject rn = new GeneralObject();
			do{
				enrollment_id=rn.randomNumber(1000, 9999);
				rowCount = jdbctemplate.queryForObject("select count(*) from enrollment where enrollment_id= ?",new Object[]{enrollment_id},Integer.class);
			}while(rowCount!=0);

			HttpSession session= req.getSession();
			Course course = (Course) session.getAttribute("course");
			query="INSERT INTO enrollment VALUES (?,?,?,?,?,?)";
			jdbctemplate.update(query, new Object[]{enrollment_id,session.getAttribute("username").toString(),course.getCourse_id(),selectedCategory,laptop,"failed"});
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}
