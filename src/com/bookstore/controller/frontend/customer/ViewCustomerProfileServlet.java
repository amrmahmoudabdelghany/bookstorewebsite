package com.bookstore.controller.frontend.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CustomerService;

/**
 * Servlet implementation class ViewCustomerProfileServlet
 */
@WebServlet("/view_profile")
public class ViewCustomerProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     CustomerService service = new CustomerService(request , response) ;
     service.viewCustomerProfile() ; 
	}


}
