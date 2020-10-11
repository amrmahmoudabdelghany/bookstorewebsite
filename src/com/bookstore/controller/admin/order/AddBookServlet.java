package com.bookstore.controller.admin.order;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;
import com.bookstore.service.OrderService;
import com.bookstore.service.Redirector;


@WebServlet("/admin/add_book_form")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 List<Book> bookList  = (new BookDAO()).listAll() ; 
		 request.setAttribute("bookList", bookList);
		 Redirector.forwardToPage(request, response, "add_book_form.jsp");
		
		 
	}

}
