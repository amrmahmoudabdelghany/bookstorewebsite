package com.bookstore.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;


public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected EntityManager entityManager ; 
    protected EntityManagerFactory entityManagerFactory ; 
    
	
    public BaseServlet() {
	    super() ; 
	}
	public void init(ServletConfig config) throws ServletException {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite") ; 
		this.entityManager = this.entityManagerFactory.createEntityManager() ; 
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
	 this.entityManagerFactory.close();
	 this.entityManager.close() ; 
	}

}
