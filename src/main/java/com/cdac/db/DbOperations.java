package com.cdac.db;

import java.util.LinkedHashMap;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class DbOperations {
	
	public static String select_m_degree =  "SELECT id, name FROM qualification ORDER BY name";

	public LinkedHashMap<Integer, String> getQualification(JdbcTemplate jdbcTemplate)
	{
		LinkedHashMap<Integer, String> qual = new LinkedHashMap<Integer, String>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(select_m_degree);
		while(rs.next()){
			qual.put(rs.getInt("id"), rs.getString("name").trim());
		}
		return qual;
	}
}
