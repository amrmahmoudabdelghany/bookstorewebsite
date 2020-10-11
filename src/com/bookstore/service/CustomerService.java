package com.bookstore.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.HashGenerator;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Customer;

public class CustomerService {
	private CustomerDAO customerDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CustomerService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		customerDao = new CustomerDAO();
	}

	public void listAll(String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		listAll();
	}
    public void deleteCustomer() throws ServletException, IOException { 
    	int customerId = Integer.parseInt(request.getParameter("id")) ;
    	Customer customer = customerDao.get(customerId) ; 
    	if(customer != null) {
    		long reviewsNum = (new ReviewDAO()).countByCustomer(customerId) ; 
    		if(reviewsNum > 0) {
    			String message = "Could not delete customer with ID [ " + customerId + " ] because he/she posted reviews for books" ;
    			Redirector.showMessageBackend(request, response, message);
    		}else {
    	     long orderNum = (new OrderDAO()).countOrderByCustomer(customerId) ; 
    	     if(orderNum  > 0) { 
    	    	  String message = "Could not delete customer with ID [ " + customerId  + " ] because he/she placed orders" ; 
    	    	  Redirector.showMessageBackend(request, response, message);
    	     }else { 
    		customerDao.delete(customerId);
    		listAll("Customer has been deleted successfully") ;
    	     }
    		}
    	}else { 
    	   String message = "Could not find customer with ID [ " +customerId+" ], or it has been deleted by another admin" ; 
    	   Redirector.showMessageBackend(request, response, message);
    	}
    }
	public void listAll() throws ServletException, IOException {

		List<Customer> customers = customerDao.listAll();
		request.setAttribute("customerList", customers);
		Redirector.forwardToPage(request, response, "customer_list.jsp");

	}

	public void createCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");

		if (customerDao.findByEmail(email) != null) {
			Redirector.showMessageBackend(request, response,
					"Could not create customer with email  " + email + ". This email is already exists.");
		} else {
			Customer customer = readCustomer();
			customer.setEmail(email);
			customerDao.create(customer);
			listAll("Customer has been created successfully");
		}
	}
    private Customer readCustomer(Customer customer) { 
    	
    			String firstname = request.getParameter("firstname");
    			String lastname = request.getParameter("lastname") ; 
 
    			String addressLine1 = request.getParameter("address1");
    			String addressLine2 = request.getParameter("address2") ; 
    			String city = request.getParameter("city");
    			String state = request.getParameter("state") ; 
    			String phoneNumber = request.getParameter("phoneNumber");
    			String zipCode = request.getParameter("zipCode");
    			String password = request.getParameter("password");
    			String country = request.getParameter("country");
    			
    			customer.setFirstname(firstname);
    			customer.setLastname(lastname);
    			
    			customer.setAddressLine1(addressLine1);
    			customer.setAddressLine2(addressLine2);
    			customer.setCity(city);
    			customer.setState(state);
    			customer.setPhoneNumber(phoneNumber);
    			customer.setZipCode(zipCode);
    			if(password != null && !password.isEmpty()) { 
    				customer.setPassword(HashGenerator.md5(password));
    			}
    			
    			customer.setCountry(country);

    			return customer;


    }
	private Customer readCustomer() {
		return readCustomer(new Customer() ) ; 
    }

	public void editCustomer() throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDao.get(customerId);
		String dest = "customer_form.jsp";
		if (customer != null) {
			customer.setPassword(null);
			request.setAttribute("countries", generateCountryList());
			request.setAttribute("customer", customer);

		} else {
			String message = "Could not edit customer with id [" + customerId + "] becuse it is not exsits ";
			request.setAttribute("message", message);
			dest = "message.jsp";

		}
		Redirector.forwardToPage(request, response, dest);

	}
    public void registerCustomer() throws ServletException, IOException { 
    	String email = request.getParameter("email");
        String message =  "Could not create customer with email  " + email + ". This email is already exists." ; 
		if (customerDao.findByEmail(email) == null) {
		
			Customer customer = readCustomer();
			customer.setEmail(email);
			customerDao.create(customer);
			message = "You have registered successfully ! Thank you." +
			"<br/> <a href = 'login'>Click here </a> to login" ;
			
		}
		Redirector.showMessageFrontend(request, response, message);
      
    }
	public void updateCustomer() throws ServletException, IOException {
	
      String email = request.getParameter("email") ; 
      int customerId = Integer.parseInt(request.getParameter("customerId")) ; 
      Customer customer = readCustomer() ; 
      customer.setCustomerId(customerId);
       
      Customer existCustomer = customerDao.findByEmail(email) ;
     
      if(existCustomer != null && !existCustomer.getCustomerId().equals(customer.getCustomerId())  ) { 
    	  String message  = "Could not update customer by email [" + email + "] . this email is already registred. " ; 
    	  Redirector.showMessageBackend(request, response, message);
      }else { 
    	  Customer actC = customerDao.get(customer.getCustomerId()) ; 
    	  if(actC != null) { 
    		  
    		  customer.setEmail(email);
    		  customer.setRegisterDate(actC.getRegisterDate());
        	  customerDao.update(customer) ; 
        	  listAll("Customer with " + email + " has been updated successfully.");  
    	  }else { 
    		  String message = "Could not found customer with id [" + customer.getCustomerId() + "] or it may be deleted." ; 
    		  Redirector.showMessageBackend(request, response, message);
    	  }
   
      }
	}

	public void showLoginPage() throws ServletException, IOException {
		Redirector.forwardToPage(request, response, "frontend/login.jsp");
		
	}
	public void doLogin() throws ServletException, IOException { 
		String email = request.getParameter("email")  ;
		String password = request.getParameter("password") ; 
		Customer customer = customerDao.checkLogin(email, password) ; 
		HttpSession session = request.getSession() ; 
		
		if(customer != null) { 
			session.setAttribute("loggedCustomer", customer);
			
			Object redirectUrl = session.getAttribute("redirectURL") ; 
			if(redirectUrl != null) { 
			
				session.removeAttribute("redirectURL");
				response.sendRedirect((String)redirectUrl);
			}else { 
		
			viewCustomerProfile(); 
			}
		}else { 
		    request.setAttribute("message", "Login Faild Please Check Your E-Mail And Password");
		    showLoginPage();
		}
	}
	
	
	public void viewCustomerProfile() throws ServletException, IOException { 
		Redirector.forwardToPage(request, response, "frontend/customer_profile.jsp");
	}
    
	public void showEditCustomerProfile() throws ServletException, IOException { 
		Redirector.forwardToPage(request, response, "frontend/edit_profile.jsp");
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer") ; 
		 if(customer != null) { 
			 customer = readCustomer(customer) ; 
			 customerDao.update(customer) ; 
 			 viewCustomerProfile();
		 }else { 
			 System.out.println("Customer logged out"); 
		 }
		
		
	}
    
	public Map<String , String> generateCountryList() { 
		
		Map<String , String> countries = new TreeMap<String, String>(); 
		String codes[] = Locale.getISOCountries() ; 
		 for(String code : codes) { 
			 Locale local = new Locale("" , code) ; 
			 String countryCode  = local.getCountry();
			 String countryName = local.getDisplayCountry() ; 
			 countries.put(countryName, countryCode) ; 
			 
		 }
		return  countries ; 
	}
	public void newCustomer() throws ServletException, IOException {
	       
		 request.setAttribute("countries", generateCountryList());
		 Redirector.forwardToPage(request, response, "customer_form.jsp");
		
	}
	
}
