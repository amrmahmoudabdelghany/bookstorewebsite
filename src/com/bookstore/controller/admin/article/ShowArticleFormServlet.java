package com.bookstore.controller.admin.article;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ArticleService;
import com.bookstore.service.Redirector;

/**
 * Servlet implementation class ShowArticleFormServlet
 */
@WebServlet("/admin/new_article")
public class ShowArticleFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Redirector.forwardToPage(request, response, "article_form.jsp");
		
	}

}
