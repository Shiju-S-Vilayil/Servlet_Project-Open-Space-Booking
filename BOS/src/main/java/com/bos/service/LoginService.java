package com.bos.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bos.db.DBConnection;

public class LoginService 
{
	private Connection con = DBConnection.connection();
	List<String> list =new ArrayList();
	String role;
	String roleid;
	public List<String> login(String email,String password)
	{
		
		try {
			Statement st=con.createStatement();
			String sql="select * from log where email='"+email+"' and password ='"+password+"'";
			ResultSet set=st.executeQuery(sql);
			while(set.next())
			{
				role=set.getString("role");
				roleid=set.getString("roleid");
				list.add(role);
				list.add(roleid);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

}
