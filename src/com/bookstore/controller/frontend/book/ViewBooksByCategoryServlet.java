package com.bookstore.controller.frontend.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.BookService;

/**
 * Servlet implementation class ViewBooksByCategoryServlet
 */
@WebServlet("/view_category")
public class ViewBooksByCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ViewBooksByCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().write("User request category list :" + request.getParameter("id"));
	  BookService service = new BookService( request, response) ; 
	  service.listBooksByCategory() ; 
	   
	
	}

}
