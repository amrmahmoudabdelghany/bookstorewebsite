package com.bookstore.controller.admin.category;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.CategoryService;

/**
 * Servlet implementation class DeleteCategoryServlet
 */
@WebServlet("/admin/delete_category")
public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  CategoryService service = new CategoryService( request , response) ; 
	  service.deleteCategory(); 
	}

}
