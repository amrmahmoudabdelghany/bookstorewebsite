package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.dao.ArticleDAO;
import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Article;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.service.CategoryService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("")
public class HomeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	BookDAO bookDao = new BookDAO() ; 
	ArticleDAO articleDao = new ArticleDAO() ; 
	 List<Book> listNewBooks = bookDao.listNewBooks() ; 
	 List<Book> listBestSellingBooks = bookDao.listBestSellingBooks() ; 
	 List<Book> listMostFavoritBooks = bookDao.listMostFavoritBooks() ; 
	 List<Article> articleList = articleDao.listAll() ; 
	 request.setAttribute("articleList", articleList);
	 request.setAttribute("listMostFavoritBooks", listMostFavoritBooks);
	 request.setAttribute("listBestSellingBooks", listBestSellingBooks);
	 request.setAttribute("listNewBooks", listNewBooks);
	 String page = "frontend/index.jsp" ;
	 RequestDispatcher dispatcher = request.getRequestDispatcher(page) ; 
	 dispatcher.forward(request, response);
	}

	
}
