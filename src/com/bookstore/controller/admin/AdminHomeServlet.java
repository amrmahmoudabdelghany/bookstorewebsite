package com.bookstore.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Review;
import com.bookstore.service.Redirector;

/**
 * Servlet implementation class AdminHomeServlet
 */
@WebServlet("/admin/")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req  , resp ) ;
	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderDao = new OrderDAO() ; 
        ReviewDAO reviewDao = new ReviewDAO() ; 
        UserDAO userDao = new UserDAO() ; 
        BookDAO bookDao = new BookDAO() ;
        CustomerDAO customerDao = new CustomerDAO() ; 
        
        
		List<BookOrder> recentSales = orderDao.listRecentSales() ; 
		List<Review> recentReviews = reviewDao.listMostRecentReviews() ; 
		long totalUsers = userDao.count() ; 
		long totalBooks = bookDao.count() ; 
		long totalCustomers = customerDao.count() ; 
		long totalReviews = reviewDao.count() ; 
		long totalOrders = orderDao.count() ; 
		
		request.setAttribute("totalUsers", totalUsers);
		request.setAttribute("totalBooks", totalBooks);
		request.setAttribute("totalCustomers", totalCustomers);
		request.setAttribute("totalReviews", totalReviews);
		request.setAttribute("totalOrders", totalOrders);
		
		request.setAttribute("recentReviews", recentReviews);
		request.setAttribute("listRecentSales",recentSales );
		
		Redirector.forwardToPage(request, response, "index.jsp");

	}

	

}
