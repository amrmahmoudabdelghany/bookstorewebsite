package com.bookstore.dao;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

class BookDAOTest  {
  private static BookDAO bookDao ; 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	
		bookDao = new BookDAO() ; 
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	   	 bookDao.close();
	}

	@Test
	void testBookDAO() {
		
	}

	@Test
	void testCreateBook() throws IOException {
		Book book = new Book() ; 
		book.setTitle("Java EE");
		book.setAuthor("Amroo");
		book.setDescription("Java servlet Api Tutorial ");
		book.setIsbn("00000");
		String imagePath = "E:\\PNG Icons\\book.png" ; 
		
		book.setImage(Files.readAllBytes(Paths.get(imagePath)));
		book.setPrice(300);
		book.setPuplishDate(new Date());
		book.setLastUpdateTime(new Date());
		Category cat = new Category() ; 
		cat.setCategoryName("Java EE");
		cat.setCategoryId(27);
		book.setCategory(cat);
		
	   Book res =  this.bookDao.create(book) ; 
	   assertTrue(res.getBookId() > 0);
	}

	@Test
	void testUpdateBook() throws IOException {
		  Book book = this.bookDao.get(1) ;  
		     book.setTitle("Java Advanced");
			
		   Book res =  this.bookDao.update(book) ; 
		   
		   assertEquals(book.getTitle(), "Java Advanced");
		   
	}

	@Test
	void testGet() {
		Book book = this.bookDao.get(2) ;
		Category cat = book.getCategory() ; 
		System.out.println("Category Name  : "+ cat.getCategoryName()) ; 
		System.out.println("Category Id : " + cat.getCategoryId()) ; 
		assertEquals(book.getBookId(), 2);
 	}
    @Test 
    void testGetNull() { 
    	Book book = this.bookDao.get(11) ; 
    	assertNull(book);
    }
	@Test
	void testDeleteBookFail() {
		
		
		
	    assertThrows(EntityNotFoundException.class , new Executable() {
			
			@Override
			public void execute() throws Throwable {
			   bookDao.delete(1);
			}
		}) ;
	}
	
	@Test
	void testDeleteBookSuccess() { 
		this.bookDao.delete(1);
		assertNull(bookDao.get(1));
	}

	@Test
	void testListAll() {
	  long booksCount = bookDao.count() ; 
	  java.util.List<Book> books = bookDao.listAll() ; 
	    for(Book b : books) { 
	    	System.out.println("Book Title : " + b.getTitle()) ; 
	    	Category cat = b.getCategory() ; 
	       System.out.println("Category Id : " + cat.getCategoryId()) ; 
	    }
	  assertTrue(books.size() == booksCount) ; 
	}
    @Test 
    void testFindByTitleNotEx() { 
    	assertNull(bookDao.findByTitle("ABC"));
    }
    @Test
    void testFindByTitleEx() { 
    	assertNotNull(bookDao.findByTitle("Java EE") ) ; 
    }
	@Test
	void testCount() {
		long conut  = bookDao.count() ; 
		List<Book> books = bookDao.listAll() ; 
		assertTrue(conut == books.size());
	}
	@Test
    void testListbyCategory() { 
    	List<Book> books = this.bookDao.listByCategory(27) ; 
    	for(Book b : books) { 
    		System.out.println("Book titlt : " + b.getTitle()) ; 
    		
    	}
    	assertTrue(books.size() > 0);
    }
	@Test
	void testListNewBooks() { 
		List<Book> newBooks = this.bookDao.listNewBooks() ; 
		for(Book b : newBooks) { 
			System.out.println("Book Date : " + b.getPuplishDate()) ; 
		}
		assertTrue(newBooks.size() == 4);
	}
	
	@Test
	void testSearchInTitle() { 
		List<Book> res = this.bookDao.search("SCWCD") ; 
		for(Book b : res) { 
			System.out.println(b.getTitle()) ; 
		}
		assertEquals(1, res.size());
	}
	
	@Test
	void testCountByCategory() { 
		long res = bookDao.countByCategory(32) ; 
		long exp = 7 ; 
		assertEquals(exp, res);
	}
	
	@Test
	void testBestSellingBooks() { 
		List<Book> list = bookDao.listBestSellingBooks() ; 
		 if(list != null) { 
			 for(Book book : list) { 
				 System.out.println("Book Id : " + book.getBookId()) ; 
				 System.out.println("Book Title : " + book.getTitle()) ; 
			 }
		 }
		assertTrue(list != null && !list.isEmpty());
	}
	
	@Test
	void testMostFavoriteBooks() { 
		List<Book> list = bookDao.listMostFavoritBooks() ; 
		if(list != null) { 
			for(Book b : list) { 
				System.out.println("Book Id : " + b.getBookId()) ; 
				System.out.println("Book Title : " + b.getTitle()) ; 
			}
		}
		assertTrue(list != null && !list.isEmpty());
		
	}
}
