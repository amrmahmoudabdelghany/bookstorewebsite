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
 * Servlet implementation class AddBookToCartServlet
 */
@WebServlet("/add_to_cart")
public class AddBookToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  int bookId = Integer.parseInt(request.getParameter("bookId")) ; 
	  BookDAO bookDao = new BookDAO() ; 
	   Book book = bookDao.get(bookId) ; 
	   if(book != null) { 
		   Object cartObj = request.getSession().getAttribute("cart") ; 
		   ShoppingCart cart = null ; 
		   if(cartObj !=null && cartObj instanceof ShoppingCart) {
		    cart =(ShoppingCart) cartObj ; 
		   
		   }else { 
			   cart = new ShoppingCart() ; 
			   request.getSession().setAttribute("cart", cart);
		   }
		   cart.addItem(book);
		   Redirector.forwardToPage(request, response, "frontend/shopping_cart.jsp");
	   }else { 
		   Redirector.showMessageFrontend(request, response, "Book With id [ " + bookId + " ] " + "is not exists." );
	   }
	}

}
