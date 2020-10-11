package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.OrderWith;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

class ReviewDAOTest {
  private static ReviewDAO reviewDao ; 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDAO() ; 
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}

	@Test
	void testGet() {
		int reviewId = 1 ; 
		Review r = reviewDao.get(reviewId) ; 
	    assertNotNull(r);
		
	}

	@Test
	void testDeleteObject() {
		reviewDao.delete(1);
		assertEquals(1, reviewDao.count());
	}

	@Test
	void testListAll() {
		List<Review> review = reviewDao.listAll() ; 
		for(Review r : review) { 
			System.out.println("Review ID : " + r.getReviewId()) ; 
			System.out.println("Review Heding : " + r.getHeadline()) ; 
			System.out.println("Review Rating : " + r.getRating()) ; 
			
		}
		assertEquals(reviewDao.count(), review.size());
	}

	@Test
	void testCount() {
		long count = reviewDao.count()  ; 
		assertTrue(count > 0);
	}

	@Test
	void testCreateReview() {
	  Review r = new Review() ; 
	  Book book = new Book() ; 
	
	  Customer customer = new Customer();
	  customer.setCustomerId(183);
	  book.setBookId(13);
	 
	  r.setHeadline("Very Informative Book");
	  r.setRating(5);
	  r.setComment("It was a very useful book");
	  r.setBook(book);
	  r.setCustomer(customer);
	 r =  reviewDao.create(r) ;
	 assertNotEquals(null, r);
	}
 
	@Test
	void testUpdateReview() { 
		Review r = reviewDao.get(1) ; 
		r.setRating(2);
		Review updatedR = reviewDao.update(r); 
		assertEquals(r.getRating(),updatedR.getRating());
	}
	@Test
	void testCountByBook() { 
		int bookId = 24 ; 
		
		long num = reviewDao.countByBook(bookId) ; 
		
		assertEquals(1, num);
	}
	@Test
	void testCountByCustomer() { 
		int customerId = 197 ; 
		
		long num = reviewDao.countByCustomer(customerId) ; 
		assertEquals(1, num);
	}
	
	@Test
	void testFindByCustomerAndBook() { 
		int customerId  = 197 ; 
		int bookId = 24 ; 
		Review review = reviewDao.findByCustomerAndBook(customerId, bookId) ; 
	   assertNotNull(review);
		
	}
	
	@Test
	void testListMostRecentReviews() { 
		List<Review> reviews = reviewDao.listMostRecentReviews() ; 
		
		assertEquals(3, reviews.size());
	}
}
