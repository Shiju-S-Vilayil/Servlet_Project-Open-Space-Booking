package com.bos.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SecondForm
 */
@WebServlet("/SecondForm")
public class SecondForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecondForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

      
String roleid=request.getParameter("data").toString();


        // Get the PrintWriter object to write the response
        PrintWriter out = response.getWriter();

        // Printing each line of the HTML code
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Open Space Booking</title>");
        out.println("<link rel='stylesheet' href='formstyle.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action='GetFormData' method='post'  id='myForm' >");
        out.println("<h2>Personal Information</h2>");
        out.println("<div class='form-group address'>");
        out.println("<label for='address'>Address</label>");
        out.println("<input type='text' id='address' name='address' placeholder='Enter your address (max 200 characters)' maxlength='200'>");
        out.println("</div>");
        out.println("<div class='form-group nric'>");
        out.println("<label for='nric'>NRIC </label>");
        out.println("<input type='text' id='nric' name='nric' placeholder='Enter your NRIC (max 20 characters)' maxlength='20'>");
        out.println("</div>");
        out.println("<div class='form-group usage-trade'>");
        out.println("<label for='usage-trade'>Usage Trade</label>");
        out.println("<select id='usage-trade' name='usage-trade'>");
        out.println("<option value='' selected disabled>Select usage trade</option>");
        out.println("<option value='Credit Card Promotion'>Credit Card Promotion</option>");
        out.println("<option value='F&B'>F&B</option>");
        out.println("<option value='Hairdressing'>Hairdressing</option>");
        out.println("<option value='Clothing'>Clothing</option>");
        out.println("<option value='Others'>Others</option>");
        out.println("</select>");
        out.println("<input type='text' id='usage-trade-other' name='usage-trade-other' placeholder='Enter other usage trade (max 50 characters)' maxlength='50' style='display: none;'>");
        out.println("</div>");
        out.println("<div class='form-group tel-number'>");
        out.println("<label for='tel-number'>Tel Number</label>");
        out.println("<select id='tel-number' name='tel-number'>");
        out.println("<option value='' selected disabled>Select Telephone Number Type</option>");
        out.println("<option value='Handphone'>Handphone </option>");
        out.println("<option value='Office'>Office</option>");
        out.println("<option value='Home'>Home </option>");
        out.println("</select>");
        out.println("<input type='tel' id='tel-number-input' name='tel-number-input' placeholder='Enter tel number' pattern='[8-9][0-9]{7,}' style='display: none;'>");
        out.println("</div>");
        out.println("<input type='hidden' name='roleid' value="+roleid+">");
        out.println("<div class='form-group submit-btn'>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</div>");
        out.println("</form>");
        out.println("<div id='thank-you-content' style='display: none; color: #ffffff;'>");
        out.println("<h1>Thank you for filling out the form correctly 💙</h1>");
        out.println("</div>");
        out.println("<script src='formscript.js'></script>");
        out.println("</body>");
        out.println("</html>");
  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
