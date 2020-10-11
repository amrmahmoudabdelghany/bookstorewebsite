package com.bookstore.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.bookstore.entity.Book;

public class ShoppingCart {
	
	
	private Map<Book , Integer> cart = new HashMap<Book, Integer>() ; 
	
	
	public void addItem(Book book) { 
		if(cart.containsKey(book)) { 
			Integer quantity = cart.get(book) + 1 ; 
			cart.put(book, quantity) ; 
			
 		}else { 
 			cart.put(book, 1) ; 
 		}
		
	}
	
	public void removeItem(Book book) { 
		this.cart.remove(book) ; 
	}
	public Map<Book , Integer> getItems() { 
		
		return this.cart ; 
	}

	public int getTotalQuantity() { 
	  Set<Book> books = cart.keySet() ; 
	  
	 Iterator<Book> iterator =   books.iterator() ; 
	 int total = 0 ; 
	 while(iterator.hasNext()) { 
		 total += cart.get(iterator.next()) ; 
	 }
		
	 return total ; 
	}
	
	public double getTotalQuantityAmount() {
		
		double total = 0.0 ; 
		
		Iterator<Book> ite = cart.keySet().iterator() ; 
		
		while(ite.hasNext()) { 
			Book book = ite.next() ;
			Integer quantity = cart.get(book) ; 
			double sum = quantity * book.getPrice() ; 
			total += sum ; 
		}
		
		
		
		return total ; 
	}
	
	public void updateCart(int[] bookIds , int[] quantities) { 
		
		for(int i = 0 ; i < bookIds.length ; i++) { 
			Book key = new Book(bookIds[i]) ; 
			Integer value = quantities[i] ; 
			cart.put(key, value) ; 
		}
		
	}
	public int getTotalItems( ) { 
		return this.cart.size() ; 
	}
	public void clear() { 
		this.cart.clear();  
	}
}
