package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.bookstore.entity.Customer;
@TestMethodOrder(OrderAnnotation.class)
class CustomerDAOTest {
 private static  CustomerDAO customerDao ; 
 private static long count = 0  ; 
 private static List<Customer> customers  ; 
 
 private static  Customer singleCustomer ; 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		customerDao = new CustomerDAO() ; 
		 customers = new ArrayList() ; 
		 count = customerDao.count() ;
		 
	     
	}

	
	@Test
	void createCustomer() { 
	Customer customer  = new Customer() ; 
	
   	 customer.setEmail("AmrMahmoud2@gmail.com");
   	 customer.setFirstname("fullName");
   	 customer.setLastname("lastName ");
   	 customer.setAddressLine1("Address Line 1" );
   	 customer.setAddressLine2("Address Line 2 " );
   	 customer.setCity("city" );
   	 customer.setState("State");
   	 customer.setPhoneNumber("phonenumber" );
   	 customer.setZipCode("Zip Code " );
   	 customer.setPassword("Password " );
   	 customer.setRegisterDate(new Date());
   	 customer.setCountry("country " );
   	 Customer res  = customerDao.create(customer) ;
   	 assertNotNull(res) ; 
	}

	@Test
	@Order(1)
	void testCreateCustomer() {
		System.out.println("On Test Create Customer  run" ) ; 
		 Customer customer  = new Customer() ; 
    	 customer.setEmail("email@email");
    	 customer.setFirstname("fullName");
    	 customer.setLastname("lastName ");
    	 customer.setAddressLine1("Address Line 1" );
    	 customer.setAddressLine2("Address Line 2 " );
    	 customer.setCity("city" );
    	 customer.setState("State");
    	 customer.setPhoneNumber("phonenumber" );
    	 customer.setZipCode("Zip Code " );
    	 customer.setPassword("Password " );
    	 customer.setRegisterDate(new Date());
    	 customer.setCountry("country " );
    	 singleCustomer = customerDao.create(customer) ;
    	 if(singleCustomer == null) { 
    		 System.out.println("After assin single customer is null") ; 
    	 }
    	 count ++ ; 
    	 assertTrue(singleCustomer.getCustomerId() > 0);
 	 
	}

	@Test
	@Order(2)
	void testGet() {
 
		if(singleCustomer == null) { 
			System.out.println("Single Customer == null") ; 
		}
		int id = singleCustomer.getCustomerId() ; 

		Customer c = customerDao.get(id) ; 
	
		assertEquals(c.getEmail(), singleCustomer.getEmail());
	}
	
	@Test
	@Order(3)
	void testUpdateCustomer() { 
		singleCustomer.setFirstname("Amr");
		singleCustomer.setLastname("Mahmoud Abd Elghany");
		singleCustomer.setPhoneNumber("01283612465");
		singleCustomer.setEmail("amrkoky19@gmail.com");
		singleCustomer.setPassword("123456");
		singleCustomer.setCountry("Egypt");
		singleCustomer.setState("Alex");
		singleCustomer.setCity("Alexandria");
		singleCustomer.setAddressLine1("Elbitash");
		singleCustomer.setAddressLine2("Elagamy");
		singleCustomer.setZipCode("23000");
	   Customer res = customerDao.update(singleCustomer);
	   assertEquals("Amr", res.getFirstname());
	   assertEquals("Mahmoud Abd Elghany", res.getLastname());
	   assertEquals("Elbitash"  , res.getAddressLine1());
	   assertEquals("Elagamy", res.getAddressLine2());
	   assertEquals("Alex", res.getState());
	   
		
	}
	
	@Test
	@Order(4)
	void testDeleteObject() {
	  customerDao.delete(singleCustomer.getCustomerId());
	  count -- ; 
	  testGetFailer();
	}
	
	@Test
	@Order(5)
    void testGetFailer() { 
    	Customer c = customerDao.get(singleCustomer.getCustomerId()) ; 
    	 assertEquals(c, null);
    }

	@Test
	@Order(6)
    void testCreateList() { 
		
     for(int i = 0 ; i  < 10 ; i++) { 
    	 Customer customer  = new Customer() ; 
    	 customer.setEmail("email" + i +"@email");
    	 customer.setFirstname("fullName" + i);
    	 customer.setLastname("lastName" + i);
    	 customer.setAddressLine1("Address Line 1" + i);
    	 customer.setAddressLine2("Address Line 2 " + i);
    	 customer.setCity("city" + i);
    	 customer.setPhoneNumber("phonenumber" + i);
    	 customer.setZipCode("Zip Code " + i);
    	 customer.setPassword(HashGenerator.md5("Password " + i));
    	 System.out.println("Password : " + customer.getPassword()) ; 
    	 customer.setRegisterDate(new Date());
    	 customer.setCountry("country " + i);
    	 customer.setState("State " + i);
    	 Customer createdCu = customerDao.create(customer) ; 
    	 assertEquals(createdCu.getEmail(), customer.getEmail());
    	 customers.add(i, customer);
    	 count ++ ; 
     }
		
	 	
		
    }
	
	@Test
    @Order(7)
	void testCount() {
		long actC = customerDao.count() ; 
		assertEquals(count, actC);
	}
 

	@Test
	@Order(8)
	void testListAll() {
		List<Customer> cList = customerDao.listAll() ; 
		boolean exstis = false ;  
		for(Customer createdCustomer : customers) { 
			
			for(Customer existsCustomer : cList) { 
				if(createdCustomer.getEmail().equalsIgnoreCase(existsCustomer.getEmail())) {
					exstis = true ; 
					break ; 
				}else { 
					exstis = false ; 
				}
			}
			if(exstis == false ) break ; 
		}
		assertTrue(exstis);
		 
	}

   @Test
   @Order(9)
   void testFindByEmail() { 
	   Customer c = customerDao.findByEmail("email9@email") ; 
	   if(c != null) { 
		   assertTrue(c.getEmail().equalsIgnoreCase("email9@email"));
	   }else { 
		   assertTrue(false) ;
	   }
   }
   
   @Test 
   @Order(10)
   void testCheckLogin() { 
	   String email = "email9@email" ; 
	   String password = "Password 9" ; 
	  
	  Customer c =  customerDao.checkLogin(email, password) ; 
	  if(c != null) { 
		  System.out.println("Welcome " + c.getFirstname()) ; 
	  }
	  assertNotEquals(null, c);
   }
   
   @Test
   @Order(11) 
   void testClearList() { 
	   long currentCount = count ; 
	   for(Customer c : customers ) { 
		   customerDao.delete(c.getCustomerId());
		   currentCount -- ; 
	   }
	   long actCount = customerDao.count() ; 
	   assertEquals(currentCount, actCount);
   }
   

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}
}
