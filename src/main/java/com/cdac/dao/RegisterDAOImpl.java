package com.cdac.dao;

import java.sql.DriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cdac.model.User;

@SuppressWarnings("unused")
@Repository
public class RegisterDAOImpl implements RegisterDAO{

	@Autowired
	JdbcTemplate jdbctemplate;
	@Override
	public boolean registerUser(User user) {
		// TODO Auto-generated method stub
		/*String url= "jdbc:postgresql://localhost/ftcdb" ;
		String username = "ftc";
		String password = "ftc_pass";
*/
		
		/*	try{
			
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection(url, username, password);
			PreparedStatement pst = db.prepareStatement("Insert into users values (?,?,?,?,?,?)");
			pst.setString(1,user.getName());
			pst.setString(2,user.getEmail());
			pst.setString(3,user.getPassword());
			pst.setString(4,user.getUserType());
			pst.setLong(5,user.getMobile());
			if(user.getUserType().equalsIgnoreCase("Attendee"))
				pst.setBoolean(6,true);
			else
				pst.setBoolean(6,false);

			pst.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}*/
		
		try {
			String query="INSERT INTO users (email_id, name, password, type_of_user, mobile_no,isvalid)" + " VALUES (?, ?, ?, ?, ?, ?)";
			jdbctemplate.update(query, new Object[]{user.getEmail_id(),user.getName(),user.getPassword(),user.getType_of_user(),user.getMobile_no(),user.getType_of_user().equalsIgnoreCase("attendee")?true:false});
			return true;
		
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
}
