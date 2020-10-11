package com.bookstore.controller.admin.article;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ArticleService;

/**
 * Servlet implementation class UpdateArticleServlet
 */
@WebServlet("/admin/update_article")
public class UpdateArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleService service = new ArticleService(request , response) ;
		service.updateArticle() ; 
	}

}
