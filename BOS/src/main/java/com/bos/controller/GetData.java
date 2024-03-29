package com.bos.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bos.db.DBConnection;
import com.bos.service.SeatBookingService;

/**
 * Servlet implementation class GetData
 */
@WebServlet("/GetData")
public class GetData extends HttpServlet {
	String selectedSeats="no data";
	private static final long serialVersionUID = 1L;
	 SeatBookingService serv=new SeatBookingService();
     int noofvisit1=0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetData() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Connection con=DBConnection.con;
	int maxseatcount=0;
    int seatcurrentcount=0;
    List<Integer> seatreservedList=new ArrayList<Integer>();
    List<Integer> seatreservedListClient=new ArrayList<Integer>();
		selectedSeats = request.getParameter("selectedSeats");
		 String customerId=request.getParameter("customerId");
		 int id=Integer.parseInt(customerId);
			try 
			{
				Statement st=con.createStatement();
				String sql="select premium.premium_seatcount as maxseatcount ,premium.weekday as weekday, premium.weekend as weekend from premium inner join premium_users on premium.id=premium_users.id where premium_users.premium_customer_id="+id+"";
				ResultSet rs=st.executeQuery(sql);
				while(rs.next())
				{
					maxseatcount=rs.getInt("maxseatcount");
//					 System.out.println("MaxSeatCount of Current User--------------------------------------------------------------------------------------------"+maxseatcount);

			
				}
			} catch (Exception e) 
			{
				System.out.println(e);
			}
		 String selectSeat[]=selectedSeats.split(",");
		 List seats=new ArrayList();
		 for (String string : selectSeat) 
		 {
			 seats.add(string);
			 seatreservedListClient.add(Integer.parseInt(string));
		 }
		 //data from db seatreserved
		 try 
			{
				Statement st=con.createStatement();
				String sql="select seatcodecol from seatreserved where customerid='"+customerId+"'";
				ResultSet rs=st.executeQuery(sql);
				while(rs.next())
				{
					seatreservedList.add((Integer.parseInt(rs.getString("seatcodecol"))+1000000));

				}
			} catch (Exception e) 
			{
				System.out.println(e);
			}
		 
		 boolean flag = false;

	        // Iterating through elements of list1
	        for (Integer element : seatreservedListClient) {
	            // If list2 contains the current element of list1
	            if (seatreservedList.contains(element)) {
	                flag = true;
	                break; 
	            }
	        }
		 
		 if(flag!=true)
		 {
			 if(seats.size()>=2)
			 {
						 try 
						 {
							 String s="select count(seatcodecol) as count from seatreserved where customerid='"+customerId+"'";
							 Statement st=con.createStatement();
							 ResultSet rs=st.executeQuery(s);
							 while(rs.next())
							 {
								 seatcurrentcount=rs.getInt("count");
			//					 System.out.println("SeatCount of Current User--------------------------------------------------------------------------------------------"+seatcurrentcount);
							 }
							
						} 
						 catch (Exception e) 
						 {
							System.out.println(e);
						}
						 
						 int c=seats.size()+seatcurrentcount;
						 
						 if(c<=maxseatcount)
						 {
							 
							 if(Customerdashboard.noofvisit!=2)
							 {
								
								 int i=serv.addReservedSeats(seats,Integer.parseInt(customerId),Customerdashboard.noofvisit);
								 if(i==1)
								 {
									 String value1="202";
									 String redirectUrl = "Customerdashboard?arg1=" + value1;
									    response.sendRedirect(redirectUrl); 
								 }
							 }
							 else
							 {
								 
								 String value1="100";
								 String redirectUrl = "Customerdashboard?arg1=" + value1;
								    response.sendRedirect(redirectUrl); 
							 }
							 
				 }
				 else
				 {
					 
					 String value1="200";
					 String redirectUrl = "Customerdashboard?arg1=" + value1;
					    response.sendRedirect(redirectUrl); 
				 }
			
			
			 }
			 else
			 {
				 String value1="600";
				 String redirectUrl = "Customerdashboard?arg1=" + value1;
				    response.sendRedirect(redirectUrl); 
				 
			 }
		      
		}
		 else
		 {
			 String value1="700";
			 String redirectUrl = "Customerdashboard?arg1=" + value1;
			    response.sendRedirect(redirectUrl); 
		 }
		 }
		 
		 

}
