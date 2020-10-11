package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class AdminLoginFilter
 */
@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 
		HttpServletRequest httpRequest = (HttpServletRequest)request ;  
		 boolean isLoginIn = httpRequest.getSession(false) != null && 
				 httpRequest.getSession().getAttribute("useremail") != null ;
		 RequestDispatcher dispatcher  = null ; 
		 String loginURI = httpRequest.getContextPath() + "/admin/login"; 
		 
	     boolean isLoginRequest  = httpRequest.getRequestURI().equals(loginURI)  ;  
	     boolean isLoginPage = httpRequest.getRequestURI().endsWith("/login.jsp") ; 
	     
	     if(isLoginIn && ( isLoginPage || isLoginRequest)) { 
	    	 dispatcher = request.getRequestDispatcher("/admin/") ; 
			   dispatcher.forward(request, response);
			    
	     
	     }else if(isLoginIn || isLoginRequest) { 
			   
	    	
			   chain.doFilter(request, response);
			
		 }else { 
			 
			  dispatcher = request.getRequestDispatcher("login.jsp") ;
			  dispatcher.forward(request, response);
			   
		 }
		
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
