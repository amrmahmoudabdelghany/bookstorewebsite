package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.service.Redirector;

/**
 * Servlet implementation class ViewCartServlet
 */
@WebServlet("/view_cart")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Object cartObj = request.getSession().getAttribute("cart") ;
		if(cartObj == null) { 
			ShoppingCart cart = new ShoppingCart() ; 
			request.getSession().setAttribute("cart", cart);
		
		}
		
		
		
 		Redirector.forwardToPage(request, response, "frontend/shopping_cart.jsp");
	
	
	}

}
