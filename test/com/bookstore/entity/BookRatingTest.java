package com.bookstore.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.dao.BookDAO;

class BookRatingTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testAverageRating() {
		BookDAO bookDao = new BookDAO() ; 
		
		Book book = bookDao.get(13); 
		
		
		assertEquals(4.5f, book.getAveraeRating());
	}

	
	@Test 
	void testRatingStarsArr() { 
		for(int x = 0 ; x < 20 ; x++) { 
		float rating = 0.1f ; 
		Book b = new Book() ; 
		Random rand = new Random() ; 
		Set<Review> reviews = new HashSet<Review>() ; 
		for(int i = 0 ; i < 13 ; i++) { 
			Review r = new Review() ; 
			r.setRating(rand.nextInt(6));
			reviews.add(r); 
		}
		b.setReviews(reviews);
		float avr = b.getAveraeRating() ; 
		
        String [] res = b.getRatingStars() ; 
      
        System.out.println("Average : " + avr) ; 
        System.out.print("Stars : " ) ; 
        
        for(String star : res) { 
        	System.out.print(star + " ");
        }
        System.out.println("" ) ; 
        assertTrue(true) ;
		}
	}
	@Test
	void testAverageRatingString() { 
		float rating = 4.9f ; 
		Book b = new Book() ; 
        String res = b.getRatinString2(rating) ; 
        System.out.println("res : " + res) ; 
        assertEquals("on,on,on,on,half", res);
        
		
	}
}
