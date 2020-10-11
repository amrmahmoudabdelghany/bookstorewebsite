package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookService {

	private EntityManager entityManager;
	private HttpServletRequest request;
	private HttpServletResponse response;

	private BookDAO bookDao;
    private CategoryDAO categoryDao ; 
	public BookService( HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		this.entityManager = entityManager;
		this.request = httpServletRequest;
		this.response = httpServletResponse;
		this.bookDao = new BookDAO();
		this.categoryDao = new CategoryDAO() ;

	}

	public void listBook() throws ServletException, IOException {
       List<Book> bookList = this.bookDao.listAll() ; 
       request.setAttribute("bookList", bookList);
       
        Redirector.forwardToPage(request, response, "book_list.jsp");
	}
	
	public void listBook(String message) throws ServletException, IOException { 
		request.setAttribute("message", message);
		listBook() ;
	}
	public void showNewBookForm() throws ServletException, IOException { 
	   List<Category> categoryList =   this.categoryDao.listAll() ; 
	   request.setAttribute("categoryList", categoryList);
		Redirector.forwardToPage(request, response, "book_form.jsp");
	}

	public void createBook() throws IOException, ServletException {
		Book newBook = new Book() ; 
		 readBook(newBook) ; 
		
		if(!checkTitleUNIQ(newBook)) { 
			String message = "Could not create a Book with the title " + newBook.getTitle() + " already exists." ; 
			   Redirector.showMessageBackend(request, response, message);
		}else {
	     bookDao.create(newBook) ; 
         listBook("Book has been created successfully");
		}
		
		
	}
	public boolean checkTitleUNIQ(Book book) { 
	   Book bookByTitle = bookDao.findByTitle(book.getTitle()) ; 
	   if(bookByTitle != null && bookByTitle.getBookId() != book.getBookId()) { 
		   return false ; 
	   }
	   return true ; 
	}
	
    
	public void editBook() throws ServletException, IOException {
	   String bookId = request.getParameter("id") ;
	   Book book = bookDao.get(Integer.parseInt(bookId)) ; 
	   if(book != null) { 
	   List<Category> categoryList = categoryDao.listAll() ; 
	   
	   request.setAttribute("book", book);
	   request.setAttribute("categorylist", categoryList);
	   Redirector.forwardToPage(request, response, "book_form.jsp");
	   }else { 
		   Redirector.showMessageBackend(request, response, "Could not find book with ID [" + bookId + "]");
		  
	   }
 		
	}

	public void updateBook() throws IOException, ServletException {
		int bookId = Integer.parseInt(request.getParameter("bookId")) ;
	   Book updatedBook = bookDao.get(bookId) ; 
	   
	   readBook(updatedBook) ; 
	   
	   if(!checkTitleUNIQ(updatedBook)) { 
		   String message = "Could not update a Book with the title " + updatedBook.getTitle() + " already exists." ;
		   Redirector.showMessageBackend(request, response, message);
	   }else { 
		   bookDao.update(updatedBook) ; 
		   listBook("Book has been updated successfully");
	   }
		
	}
	
	private void readBook(Book book) throws IOException, ServletException { 
		   String catId = request.getParameter("category") ; 
	 	   Category cat = categoryDao.get(Integer.parseInt(catId)) ; 
		   String title = request.getParameter("title") ; 
		   String author = request.getParameter("author");
		   String isbn = request.getParameter("isbn") ;
		   String description = request.getParameter("description"); 
		   
		   float price = Float.parseFloat(request.getParameter("price")) ; 
		   DateFormat dateFormate  = new SimpleDateFormat("MM/dd/yyyy") ; 
		   Date date = null ; 
			
		   try {
			 date = dateFormate.parse(request.getParameter("puplishdate"));
			 
			} catch (ParseException e) {
				
				e.printStackTrace();
			} 
		   
		  
		   Part part =  request.getPart("bookimage") ;  
		   
		   
		   if(part != null && part.getSize() > 0) { 
			   byte imageBytes [] = new byte[(int)part.getSize()] ; 
			  InputStream inputStream = part.getInputStream() ; 
			  inputStream.read(imageBytes) ; 
			  book.setImage(imageBytes);
		   }
		
		   book.setTitle(title);
		   book.setAuthor(author);
		   book.setCategory(cat);
		   book.setPrice(price);
		   book.setIsbn(isbn);
		   book.setPuplishDate(date);
		   book.setDescription(description);

	
}

	public void deleteBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id")) ; 
		
		Book target = bookDao.get(bookId) ;
		String message = "" ; 
		if(target != null) { 
			long reviewsNum = (new ReviewDAO()).countByBook(bookId) ; 
			if(reviewsNum > 0) { 
			 message = "Could not delete the book with ID [ "+bookId+" ] because it has reviews" ; 
			
			}else { 
				long orderNum = (new OrderDAO()).countDetailByBook(bookId) ; 
				if(orderNum > 0) { 
			
		     message = "Could not delete book with ID [ " + bookId + " ] because there are orders associated with it" ; 
					
					
				}else {
				bookDao.delete(bookId);
				listBook("Book has been deleted successfully");
				return ; 
			}
			}
		}else {
			
			 message =  "Could not find book with ID [" + bookId + "], or it might have been deleted' " ; 
			
		}
		Redirector.showMessageBackend(request, response, message);
		
	}

	public void listBooksByCategory() throws ServletException, IOException {
		int catId = Integer.parseInt(request.getParameter("id")) ; 
		Category category = categoryDao.get(catId) ; 
	     
		if(category != null) { 
		List<Book> catBooks = bookDao.listByCategory(catId) ;
		
		request.setAttribute("listbooks", catBooks);
		request.setAttribute("category", category);
		  Redirector.forwardToPage(request, response, "frontend/books_list_by_cat.jsp");
		}else { 
			Redirector.showMessageFrontend(request, response, "Sorry, the category ID [" + catId + "] is not available");
			
		}
		
	}
	
	public void viewBookDetail() throws ServletException, IOException { 
		int bookId = Integer.parseInt(request.getParameter("id")) ; 
		
		Book book = bookDao.get(bookId) ; 
		
		if(book != null) { 
			request.setAttribute("book", book);
		
		 Redirector.forwardToPage(request, response, "frontend/view_book_details.jsp");
			
		}else { 
			Redirector.showMessageFrontend(request, response, "Sorry, the book with ID [" + bookId +"] is not available");
		
		}
		
		
	}

	public void search() throws ServletException, IOException {
	  List<Book> res = null  ; 
	  String keyword = request.getParameter("keyword") ;
	  if(keyword.isEmpty()) { 
		  res = bookDao.listAll() ; 
	  }else { 
		  res = bookDao.search(keyword) ; 
		
	  }
	
	  request.setAttribute("search_result", res);
	  request.setAttribute("keyword", keyword);
	 
	  Redirector.forwardToPage(request, response, "frontend/search_result.jsp");
	  
	  
	}
	
	
	
}