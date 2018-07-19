package com.cdac.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cdac.model.Profile;
import com.cdac.model.User;



@Repository
public class LoginDAOImpl implements LoginDAO {
	
	@Autowired
	JdbcTemplate jdbctemplate;
	@Override
	public boolean loginUser(HttpServletRequest req,HttpServletResponse res) {
		try {
			String query="select * from users where email_id = ? and password=?";
			User user= jdbctemplate.queryForObject(query, new Object[]{req.getAttribute("username"),req.getAttribute("pass")}, new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setName(rs.getString("name"));
					user.setEmail_id(rs.getString("email_id"));
					user.setPassword(rs.getString("password"));
					user.setType_of_user(rs.getString("type_of_user"));
					user.setIsvalid(rs.getBoolean("isvalid"));
					return user;
				}
			});

			if(user.isIsvalid())
			{
				try	{
					HttpSession session= req.getSession();
					System.out.println("Session created");
					session.setAttribute("isLoggedIn", "true");
					session.setAttribute("username", req.getAttribute("username"));
					String name[]= user.getName().trim().split(" ");
					session.setAttribute("firstName", name[0]);
					session.setAttribute("Type_of_user",user.getType_of_user());
					
					String prof="select * from profile where username = ?";
					Profile profile= jdbctemplate.queryForObject(prof, new Object[]{req.getAttribute("username")}, new RowMapper<Profile>() {
						public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
							Profile profile = new Profile();
							profile.setUsername(rs.getString("username"));
							profile.setGender(rs.getString("gender"));
							profile.setAddress(rs.getString("address"));
							profile.setHobbies(rs.getString("hobbies"));
							profile.setSkills(rs.getString("skills"));
							profile.setSpecialization(rs.getString("specialization"));
							profile.setQualId(rs.getInt("qualification"));
							profile.setOrganization(rs.getString("organization"));
							profile.setDesignation(rs.getString("designation"));
							
							return profile;
						}
					});
					
					session.setAttribute("profile", profile);
					
				}

				catch(Exception e)
				{
					System.out.println("Exception in session creation");
					e.printStackTrace();
				}


				return true;
			}

			else
				return false;

		} 
		catch (DataAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in data access from database");
			e.printStackTrace();
			return false;
		}

	}

}
