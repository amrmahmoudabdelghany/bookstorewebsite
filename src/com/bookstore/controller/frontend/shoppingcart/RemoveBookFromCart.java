package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;

/**
 * Servlet implementation class RemoveBookFromCart
 */
@WebServlet("/remove_from_cart")
public class RemoveBookFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId")) ; 
		ShoppingCart cart =(ShoppingCart) request.getSession().getAttribute("cart") ; 
		cart.removeItem(new Book(bookId));
		
		response.sendRedirect(request.getContextPath().concat("/view_cart"));
	}

}
