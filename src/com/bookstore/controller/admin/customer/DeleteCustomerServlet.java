package com.bookstore.controller.admin.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CustomerService;

@WebServlet("/admin/delete_customer")
public class DeleteCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteCustomerServlet() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 CustomerService service = new CustomerService(request , response) ; 
		 service.deleteCustomer() ; 
	}

}
