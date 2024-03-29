package com.bos.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bos.db.DBConnection;

/**
 * Servlet implementation class GetFormData
 */
@WebServlet("/GetFormData")
public class GetFormData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFormData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String address = request.getParameter("address");
	        String nric = request.getParameter("nric");
	        String usageTrade = request.getParameter("usage-trade");
	        String usageTradeOther = request.getParameter("usage-trade-other");
	        String telNumber = request.getParameter("tel-number");
	        String telNumberInput = request.getParameter("tel-number-input");
	        String roleid=request.getParameter("roleid");
	
	     try {
	         con=DBConnection.con;
	         String sql="insert into customerpersonal values('"+address+"','"+usageTrade+"','"+usageTradeOther+"','"+telNumberInput+"','"+telNumber+"',"+roleid+",'"+nric+"')";
	    	 Statement st=con.createStatement();
	    	 int i=st.executeUpdate(sql);
	    	 if(i==1)
	    	 {
	    		   String url = "payment.jsp?data="+roleid;// + dataToSend;
	   	        
	    	       
	   	        response.sendRedirect(url);
	    	 }
			
		} catch (Exception e)
	     {
			System.out.println(e);
		}
	       
	      
	}

}
