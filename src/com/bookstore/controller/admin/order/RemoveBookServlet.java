package com.bookstore.controller.admin.order;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;
import com.bookstore.service.Redirector;


@WebServlet("/admin/remove_book_from_order")
public class RemoveBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId")) ; 
	
		HttpSession session = request.getSession() ; 
		BookOrder order =(BookOrder) session.getAttribute("order") ; 
        Set<OrderDetail> orderDetails = order.getOrderDetails() ; 
        Iterator<OrderDetail> ite = orderDetails.iterator() ; 
        while(ite.hasNext()) { 
        	OrderDetail detail = ite.next() ; 
        	Book book = detail.getBook() ; 
        	System.out.println("Ite for requested book : " + bookId) ; 
        	System.out.println("Title for requested book : " + book.getTitle()) ; 
        	System.out.println("Book Id : " + book.getBookId()) ; 
        	if(book.getBookId().equals(bookId)) { 
        		System.out.println("Ite for requested book : " + bookId) ; 
            	System.out.println("Title for requested book : " + book.getTitle()) ; 
        		float total = order.getOrderTotal() - detail.getSubTotal() ; 
        		order.setOrderTotal(total);
        		ite.remove();
        		break ; 
        	}
        }
        Redirector.forwardToPage(request, response, "order_form.jsp");
	}

	

}
