package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClearCartServlet
 */
@WebServlet("/clear_cart")
public class ClearCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object cartObj = request.getSession().getAttribute("cart") ; 
		 if(cartObj != null) { 
			 ShoppingCart cart = (ShoppingCart) cartObj ; 
			 cart.clear();
			
		 }
		 response.sendRedirect(request.getContextPath().concat("/view_cart"));
		
	}

}
