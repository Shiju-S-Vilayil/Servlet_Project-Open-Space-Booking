package com.bos.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.bos.db.DBConnection;
import com.bos.model.Customer;

public class CustomerService {
	private Connection con = DBConnection.connection();
	  int roleid=0;
	public int saveCustomerData(Customer customer) 
	{
	
		String role="ROLE_CUSTOMER";
		int result = 0;
		try {

			String sql = "insert into customerinfo (customername,customeremail,customerpassword,role) values('"
					+ customer.getCustomerName() + "','" + customer.getCustomerEmail() + "',SHA2('\"\r\n"
							+ "					+ customer.getCustomerPassword() + \"',256),'"+role+"');";
			Statement stmt = con.createStatement();

			result = stmt.executeUpdate(sql);
			if(result==1)
			{
				try 
				{
					Statement stmt1 = con.createStatement();
					ResultSet set=stmt1.executeQuery("select max(customerid) as custid  from customerinfo");
					while(set.next())
					{
						roleid=set.getInt("custid");
					}
					Statement stmt2 = con.createStatement();
			String sqlLog="insert into log(email,password,role,roleid) values('"+customer.getCustomerEmail()+"','"+customer.getCustomerPassword()+"','"+role+"','"+roleid+"')";
			int result1 = stmt.executeUpdate(sqlLog);
			System.out.println("Log data created "+result1);
				} catch (Exception e) 
				{
					System.out.println(e);
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}
	
	public int getRoleid()
	{
		return this.roleid;
	}
	

}
