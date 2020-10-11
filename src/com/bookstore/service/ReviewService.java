package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewService {

	private ReviewDAO reviewDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ReviewService( HttpServletRequest request, HttpServletResponse response) {
		super();
		this.reviewDao = new ReviewDAO();
		this.request = request;
		this.response = response;
	}

	public void listAllReview(String message) throws ServletException, IOException { 
		request.setAttribute("message", message);
		listAllReview();  
	}
	public void listAllReview() throws ServletException, IOException { 
		List<Review>  reviews = reviewDao.listAll() ; 
		request.setAttribute("reviewList", reviews);
		Redirector.forwardToPage(request, response, "list_review.jsp");
	}

	public void editReview() throws ServletException, IOException {
	  int reviewId = Integer.parseInt(request.getParameter("id")) ; 
	  Review review = reviewDao.get(reviewId) ; 
	  if(review != null) { 
		  request.setAttribute("review", review);
		  Redirector.forwardToPage(request, response, "review_form.jsp");
	  }else { 
		  String message = "Could not found a review with id " + reviewId + ", which might have been deleted.";
          Redirector.showMessageBackend(request, response, message);
	  }
	}
	

	public void updateReview() throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("reviewId")) ; 
	    Review r = reviewDao.get(id) ; 
	    if(r != null) { 
		String headline = request.getParameter("headline") ;
		String comment = request.getParameter("comment") ; 
		r.setHeadline(headline);
		r.setComment(comment);
		reviewDao.update(r) ; 
		listAllReview("Review with id [" +r.getReviewId() + "] has been updated successfully" );
	    }else { 
	    	Redirector.showMessageBackend(request, response, "Update fail . Colud not find review with id [ " +id +" ]");
	    }
	}
	
	public void deleteReview() throws ServletException, IOException { 
		int reviewId = Integer.parseInt(request.getParameter("id")) ;
	    Review r = reviewDao.get(reviewId) ; 
	  
	    if(r != null) { 
	    	
	    	reviewDao.delete(reviewId);
	    	listAllReview("Delete has been sucessfully.");
	    }else { 
	    	Redirector.showMessageBackend(request, response, "Delete fail . Colud not find review with id [ " +reviewId +" ]");
	    }
	}

	public void showReviewForm() throws ServletException, IOException {
		int bookId =Integer.parseInt( request.getParameter("id") ) ; 
		BookDAO bookDao = new BookDAO() ; 
		Book book = bookDao.get(bookId);
		Customer customer =(Customer) request.getSession().getAttribute("loggedCustomer") ; 
		 Review r = reviewDao.findByCustomerAndBook(customer.getCustomerId(), bookId) ; 
		 if(r != null ) { 
			 request.setAttribute("review", r);
			 Redirector.forwardToPage(request, response, "frontend/review_info.jsp");
		 }else { 
	    request.getSession().setAttribute("book", book);	
		Redirector.forwardToPage(request, response, "frontend/review_form.jsp");
		 }
	}

	public void submitReview() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId")) ; 
		 HttpSession session = request.getSession() ; 
		 String headline = request.getParameter("headline") ;
		 String comment = request.getParameter("comment") ;
		 int rating = Integer.parseInt(request.getParameter("rating")) ; 
		 
		 Book book = new Book() ; 
		 book.setBookId(bookId);
		 Customer customer =(Customer) session.getAttribute("loggedCustomer") ;
		 Review r = new Review() ; 
		 r.setBook(book);
		 r.setCustomer(customer);
		 r.setComment(comment);
		 r.setHeadline(headline);
		 r.setRating(rating);
		 reviewDao.create(r) ; 
		 
		 Redirector.forwardToPage(request, response, "frontend/review_message.jsp");

		 session.removeAttribute("book");
	}
	
}
