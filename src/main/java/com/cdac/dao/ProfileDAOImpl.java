package com.cdac.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cdac.model.Profile;
import com.cdac.model.Qualification;
@Repository
public class ProfileDAOImpl implements ProfileDAO {
	@Autowired
	JdbcTemplate jdbctemplate;
	

	@Override
	public boolean saveProfile(Profile profile,HttpServletRequest req,HttpServletResponse res) {
		try {
			String query;
			int rowCount = jdbctemplate.queryForObject("select count(*) from profile where username= ?",new Object[]{profile.getUsername()},Integer.class);
			if(rowCount==0)
			{
				query="Insert into profile values (?,?,?,?,?,?,?,?,?)";
			}
			else
			{
			    query="UPDATE profile set username = ?, gender=?,address=?,hobbies=?, skills=?, specialization=?, qualification=?, organization=?, designation=?";
			}
			
			jdbctemplate.update(query, new Object[]{profile.getUsername(),profile.getGender(),profile.getAddress(),profile.getHobbies(),profile.getSkills(),profile.getSpecialization(),(profile.getQualId()<=0)?null:profile.getQualId(),profile.getOrganization(),profile.getDesignation()});
			HttpSession session= req.getSession();
			session.setAttribute("profile", profile);
			return true;
		
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean getQualification(HttpServletRequest req,HttpServletResponse res) {
		try{
		List<Qualification> quals = this.jdbctemplate.query(
		        "select * from qualification",
		        new RowMapper<Qualification>() {
		            public Qualification mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	Qualification quals = new Qualification();
		            	quals.setId(rs.getInt("id"));
		            	quals.setName(rs.getString("name"));
		            	quals.setDescription("description");
		                return quals;
		            }
		        });
		
	
		
		HttpSession session= req.getSession();
		session.setAttribute("quals", quals);
		
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
