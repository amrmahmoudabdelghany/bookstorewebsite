package com.bookstore.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book>{

	

	@Override
	public Book create(Book entity) {
		entity.setLastUpdateTime(new Date());
		return   super.create(entity) ; 
	}

	@Override
	public Book update(Book entity) {
		entity.setLastUpdateTime(new Date()) ; 
		  return super.update(entity) ; 
	}

	@Override
	public Book get(Object id) {
		return super.find(Book.class , id) ;
	}

	@Override
	public void delete(Object id) {
	   super.delete(Book.class, id);
		
	}

	@Override
	public List<Book> listAll() {
	   return super.findWithNamedQuery("book.listAll" ) ; 
	}
   
	@Override
	public long count() {
		return super.countWithNamedQuery("book.count") ; 
	}
     public long countByCategory(int catId) { 
	   return super.countWithNamedQuery("book.countByCategory", "categoryId", catId) ;
     }
	public Book findByTitle(String title) { 
		List<Book> books = super.findWithNamedQuery("book.findByTitle", "title", title) ; 
		if(!books.isEmpty()) { 
			return books.get(0) ; 
		}
		return null ; 
	}
	public List<Book> listByCategory(int catId) { 
		return super.findWithNamedQuery("book.listByCategory", "categoryId", catId) ; 
	}
	public List<Book> listNewBooks() { 
		
		return super.findWithNamedQuery("book.listNew" , 0 , 4) ; 
	}
	public List<Book> search(String keyword) { 
		return super.findWithNamedQuery("book.search", "keyword", keyword) ; 
	}

	public List<Book> listBestSellingBooks() { 
		return super.findWithNamedQuery("orderDetail.bestSelling", 0, 4) ; 
	}
	
	public List<Book> listMostFavoritBooks() { 
	  List<Object[]> res = findWithNamedQueryObject("review.listMostFavoritBooks" , 0 , 4) ; 
	   List<Book> bookList = new ArrayList<Book>() ; 
	   
	   if(!res.isEmpty()) {
	   for(int i = 0 ; i < res.size() ; i++) { 
		   bookList.add((Book)res.get(i)[0]) ; 
	   }
	  }
	   return bookList ; 
	}
}
