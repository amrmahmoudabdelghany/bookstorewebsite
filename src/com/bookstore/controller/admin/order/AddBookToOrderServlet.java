package com.bookstore.controller.admin.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;
import com.bookstore.service.Redirector;

/**
 * Servlet implementation class AddBookToOrderServlet
 */
@WebServlet("/admin/add_book_to_order")
public class AddBookToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
 	    int bookId = Integer.parseInt(request.getParameter("bookId")) ;
 	    int quantity = Integer.parseInt(request.getParameter("quantity")) ; 
 	    if(quantity <= 0) return ; 
 	    BookDAO bookDao = new BookDAO() ; 
 	    Book book = bookDao.get(bookId) ; if(book == null) return ; 
 	    BookOrder  order =  (BookOrder)request.getSession().getAttribute("order");
 	   OrderDetail orderDetail = null ; 
 	    OrderDAO orderDAO = new OrderDAO() ; 
 		if(order != null ) { 
 		   orderDetail = order.getOrderDetailByBookId(book.getBookId()) ;
 		  
 		  if(orderDetail == null) { // add scenario
 			  
	 		  orderDetail = new OrderDetail() ; 
	 		  orderDetail.setBook(book);
	 		  orderDetail.setBookOrder(order);
	 		  orderDetail.setQuantity(quantity);
	 		
 		  } else  { // update scenario
 			  
 			     orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
 			     
 		  }
 		  
 		  float subTotal = book.getPrice() * orderDetail.getQuantity() ; 
 		  float orderTotal = (order.getOrderTotal() + (quantity * book.getPrice() )  ) ; 
 		  orderDetail.setSubTotal(subTotal);
		  order.setOrderTotal(orderTotal);
 		  order.getOrderDetails().add(orderDetail);
 		 // orderDAO.update(order) ; 
 		  request.getSession().setAttribute("on_add_book_to_order", true);
 		  request.setAttribute("book", book);
 		  Redirector.forwardToPage(request ,response , "add_book_result.jsp");
 		   
 		}
 		
	}

}
