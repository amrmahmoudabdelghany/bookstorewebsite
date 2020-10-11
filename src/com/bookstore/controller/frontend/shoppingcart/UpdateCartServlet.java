package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String tempId [] = request.getParameterValues("bookId") ;
	    int ids [] = new int[tempId.length] ;  
	      for(int i = 0 ; i < ids.length ; i++) { 
	    	  ids[i] = Integer.parseInt(tempId[i]) ; 
	      }
		
		int quantities[] = new int[ids.length] ; 
		
		for(int i = 1; i <= ids.length; i++) { 
			quantities[i - 1] = Integer.parseInt( request.getParameter("quantity" + i)) ; 
		}
		
	
		
 		ShoppingCart cart =(ShoppingCart) request.getSession().getAttribute("cart") ;
		if(cart != null) { 
			
			cart.updateCart(ids, quantities);
		}
		
		
		response.sendRedirect(request.getContextPath().concat("/view_cart"));
 	} 

}
