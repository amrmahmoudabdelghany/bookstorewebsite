package com.bookstore.controller.frontend.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Book;

class ShoppingCartTest {
   private static ShoppingCart shoppingCart ;
  
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
        shoppingCart = new ShoppingCart() ; 
        Book book = new Book(1) ; 
	
		book.setPrice(10);
		shoppingCart.addItem(book);
		shoppingCart.addItem(book);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	void testAddItem() {
		
		Map<Book , Integer> cart = shoppingCart.getItems() ; 
		 Book book = new Book() ;
		 book.setBookId(1);
		Integer quantity  = cart.get(book) ; 
		
		assertEquals(2, quantity);
		
	}

	@Test
	void testRemoveItem() { 
		Book book = new Book() ; 
		book.setBookId(1);
		this.shoppingCart.removeItem(book);
	   int size =  this.shoppingCart.getItems().size() ; 	
	   assertEquals(0, size);
	}
	
	
	@Test
	void testRemoveItem2() { 
		Book book = new Book() ; 
		book.setBookId(2);
		shoppingCart.addItem(book);
		
		this.shoppingCart.removeItem(book);
	   assertNull(this.shoppingCart.getItems().get(book));
	}
	
	@Test
	void testTotalQuantity() { 
		Book book = new Book(3) ; 
		
		shoppingCart.addItem(book);
		shoppingCart.addItem(book);
		shoppingCart.addItem(book);
		
		assertEquals(5, shoppingCart.getTotalQuantity());
		
	}
	
	@Test
	void testTotalAmount() { 
		ShoppingCart shoppingCart = new ShoppingCart() ; 
		
		assertEquals(0.0f, shoppingCart.getTotalQuantityAmount());
		
		
	}
	
	@Test
	void testTotalAmount2() { 
		
		assertEquals(20f, shoppingCart.getTotalQuantityAmount());
	}
	
	@Test
	void testClear() { 
		shoppingCart.clear(); 
		assertEquals(0, shoppingCart.getTotalQuantity());
	}
	
	
}
