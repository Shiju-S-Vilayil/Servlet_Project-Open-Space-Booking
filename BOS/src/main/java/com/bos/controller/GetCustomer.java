package com.bos.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bos.model.Customer;
import com.bos.service.CustomerService;

/**
 * Servlet implementation class GetCustomer
 */
@WebServlet("/GetCustomer")
public class GetCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService service=new CustomerService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomer() {
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
		PrintWriter out=response.getWriter();
		Customer customer=new Customer();
		customer.setCustomerName( request.getParameter("customerName"));
		customer.setCustomerEmail(request.getParameter("customerEmail"));
		customer.setCustomerPassword(request.getParameter("customerPassword"));
		int result=service.saveCustomerData(customer);
		int roleid=service.getRoleid();
		if(result==1)
		{
			
			
		    String url = "SecondForm?data="+roleid;// + dataToSend;
	        
	       
	        response.sendRedirect(url);
			
		
		
		}
		else
		{
			out.println("<script>alert('Data saved Failed ');</script>");
		}
		
	}

}
