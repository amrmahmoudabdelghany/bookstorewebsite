package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.UserException;

import com.bookstore.dao.HashGenerator;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users; 

public class UsersService {

 private UserDAO userDAO ; 
 private HttpServletRequest request ; 
 private HttpServletResponse response ; 
 
 
 public UsersService( HttpServletRequest req , HttpServletResponse resp) { 
	 this.request = req ; 
	 this.response = resp ; 
	 this.userDAO = new UserDAO() ; 
 }
	
	

	
	public void  listUsers() throws ServletException, IOException { 
		 listUsers(null) ; 
	}
	public void listUsers(String message) throws ServletException, IOException { 
		List<Users> users = userDAO.listAll() ; 
		
	     
	      request.setAttribute("message", message);
	      request.setAttribute("users", users);
	      Redirector.forwardToPage(request, response, "user_list.jsp");
	}
	
	public void createUser() throws ServletException, IOException { 
		
		Users user = new Users() ; 
		String email = request.getParameter("email") ; 
		String fullName = request.getParameter("fullname") ; 
		String password = request.getParameter("password") ; 
		
		user.setEmail(email);
		user.setFullName( fullName );
		user.setPassword(password);
		Users exsists = userDAO.findByEmail(email) ; 
		if(exsists != null) {
			Redirector.showMessageBackend(request, response, "Could not create user.A user with email " + email + " already exists.");
		}else { 
			userDAO.create(user) ; 
			listUsers("User Has been Created Succssfully");
		}
	}
	
	public void editUser() throws ServletException, IOException { 
		int userId =Integer.parseInt(request.getParameter("id")) ; 
	   
		Users user = userDAO.get(userId) ;
	
		
		if(user == null) { 
		 Redirector.showMessageBackend(request, response, "Could not found user with id " + userId);
		}else { 
		 user.setPassword(null);
		 request.setAttribute("user", user);
		 Redirector.forwardToPage(request, response, "user_form.jsp");
		}
		
	}
	
	public void updateUser() throws ServletException, IOException { 
		 int userId = Integer.parseInt(request.getParameter("userId")) ; 
		 String email = request.getParameter("email") ; 
		 String fullName = request.getParameter("fullname") ;
		 String password = request.getParameter("password") ; 
		 
		 Users newUser = new Users() ; 
		 newUser.setUserId(userId);
		 newUser.setEmail(email);
		 newUser.setPassword(password);
		 newUser.setFullName(fullName);
		 
		 Users existUser = userDAO.findByEmail(email ) ; 
		 String message =  "" ; boolean sucss = false ; 
		 if((existUser != null && existUser.getUserId() != userId ) )  { 
			 message = "Could Not Update User" ;

		 }else if( this.userDAO.get(userId) == null) { 
			 message = "Could not find user with ID " + userId ; 
		 }else { 
			 message = "User has been updated successfully" ; 
			sucss = true ; 
			
		 }
 		 
		 if(sucss) { 
			  String pass = newUser.getPassword() ; 
			  if(pass != null && !pass.isEmpty() ) { 
			  newUser.setPassword(HashGenerator.md5(pass)); // encrept password
		      }
			 this.userDAO.update(newUser) ; 
			listUsers(message);
		 }else { 
			  Redirector.showMessageBackend(request, response, message);
			  
		 }
	}
	




	public void deleteUser() throws ServletException, IOException {
		String id = request.getParameter("id") ; 
		
		int userId = Integer.parseInt(id) ; 
		 Users user = userDAO.get(userId) ; 
		 String message = "" ;
		 if(user != null) { 
			 if(user.getUserId() != 1) { 
			 userDAO.delete(userId);
			  message = "User has been deleted successfully" ; 
			 }else {
				 message = "The default admin user account cannot be deleted" ; 
			 }
		 }else { 
			 message = "Could not find user with ID " + userId +  ", or it might have been deleted by another admin" ;  
		 }
		 listUsers(message) ; 
		
	}




	public void userLogin() throws ServletException, IOException {
		  String email = request.getParameter("email") ; 
		  String pass = request.getParameter("password") ; 
		  
		  boolean loginRes = this.userDAO.checklogin(email, pass) ; 
		  
		  if(loginRes) { 
			  
		    request.getSession().setAttribute("useremail", email);
		     Redirector.forwardToPage(request, response, "/admin/");
		    
		  }else { 
			   request.setAttribute("message", "Login Faild!");
			   Redirector.forwardToPage(request, response, "/admin/login.jsp");
		  }
	}
}
