package com.bookstore.controller.admin.users;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.UsersService;




@WebServlet("/admin/user_list")
public class UsersListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UsersService user ; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       UsersService userService = new UsersService( request , response) ; 
       
	   userService.listUsers() ; 
  
	}

	

}
