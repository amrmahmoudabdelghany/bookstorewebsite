package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.service.Redirector;

/**
 * Servlet Filter implementation class CustomerLoginFilter
 */
@WebFilter("/*")
public class CustomerLoginFilter implements Filter {
  private static final String [] LOGIN_REQUIRED = {
		  "/view_profile" , 
		  "/edit_profile" , 
		  "/update_profile" , 
		  "/write_review" , 
		  "/checkout" , 
		  "/place_order" , 
		  "/view_orders" , 
		  "/show_order_detail"
  } ; 
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  httpRequest = (HttpServletRequest) request ; 
		HttpServletResponse httpResponse = (HttpServletResponse) response ; 
	   HttpSession session = httpRequest.getSession(false) ; 
	   boolean isLoggined = (session != null && session.getAttribute("loggedCustomer") != null) ; 
	   String URL = httpRequest.getRequestURL().toString() ; 
	   
	   if(!isLoggined && isLoginRequired(URL)) { 
		   String query = httpRequest.getQueryString() ; 
		   String redirectURL = "";
		   if(query != null) { 
		    redirectURL = URL.concat("?").concat(query) ; 
		   }else { 
			   redirectURL = URL ; 
		   }
		   session.setAttribute("redirectURL", redirectURL);
		  Redirector.forwardToPage(httpRequest, httpResponse, "frontend/login.jsp");
	  }else { 
		  chain.doFilter(httpRequest, response);
	  }
	   
	 
	}
	private boolean isLoginRequired(String requestURI) {
		for(String uri : LOGIN_REQUIRED) { 
			if(requestURI.contains(uri)) { 
				
				return true ;
			}
		}
		
		return false ; 
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
