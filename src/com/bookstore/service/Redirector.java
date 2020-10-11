package com.bookstore.service;

import java.io.IOException;
import java.net.HttpRetryException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirector {

	
	
	public static void forwardToPage(HttpServletRequest request  , HttpServletResponse response , String pageName) throws ServletException, IOException { 
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageName) ; 
		 dispatcher.forward(request, response);
 	}
	public static void showMessageFrontend(HttpServletRequest request , HttpServletResponse response , String message) throws ServletException, IOException { 
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/message.jsp") ; 
		dispatcher.forward(request, response);
	}
	public static void showMessageBackend(HttpServletRequest request , HttpServletResponse response , String message) throws ServletException, IOException { 
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp") ; 
		dispatcher.forward(request, response);
	}
}
