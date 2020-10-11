package com.bookstore.controller.frontend.article;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.ArticleDAO;
import com.bookstore.entity.Article;
import com.bookstore.service.Redirector;

/**
 * Servlet implementation class ShowArticleDetailServlet
 */
@WebServlet("/article_details")
public class ShowArticleDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer articleId = Integer.parseInt(request.getParameter("id")) ; 
		ArticleDAO articleDao = new ArticleDAO() ; 
		
		Article art = articleDao.get(articleId) ; 
		if(art != null) { 
		  request.setAttribute("article", art);
		  Redirector.forwardToPage(request, response, "frontend/article_detail.jsp");
		}else { 
			Redirector.showMessageFrontend(request, response, "Colud not found article with id [ " + articleId+ " ] .");
		}
 	}

}
