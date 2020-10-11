package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

import org.hibernate.hql.internal.ast.tree.ExpectedTypeAwareNode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.bookstore.entity.Users;

class UserDAOTest {
 
 private static  UserDAO userDao ; 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	  
	  userDao = new UserDAO() ; 
	
	 
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	   userDao.close();
	
	}
    @Test
    void testCheckLoginSuccess() { 
    	String email = "amrkoky18@gmail.com" ; 
    	String password = "161825" ; 
    	boolean res = this.userDao.checklogin(email, password) ; 
    	assertTrue(res);
    }
    @Test
    void testCheckLoginFaild() { 
    	String email = "asdasldk" ; 
    	String pass = "asd;oasd" ; 
    	boolean res = this.userDao.checklogin(email, pass) ; 
    	assertFalse(res) ; 
    }
	@Test
	void testCreateUsers() {
	
	   Users user = new Users() ; 
	   user.setEmail("amrkoky22@gmail.com");
	   user.setFullName("Ahmed Mahmoud");
	   user.setPassword("mypassword");
	   user = userDao.create(user) ; 
	   
	  assertTrue(user.getUserId() > 0);
	}
   @Test
   void testCreateUsersNullF() { 
	   Users user = new Users() ; 
	   user = userDao.create(user) ; 
	   
   }
   @Test 
   public void testGetUsersFound() { 
	   Integer userId = 5; 
	   Users user = userDao.get(userId) ;
	   if(user != null) { 
	   System.out.println("User email :  " + user.getEmail());
	   System.out.println("User FullName : " + user.getFullName()) ; 
	   System.out.println("User Password : " + user.getPassword()) ; 
	   }
	   assertNotNull(user);
   }
   
   @Test
   public void testGetUsersNotFound() { 
	   Integer userId  = 1 ;
	   Users user = userDao.get(userId) ; 
	   
	   if(user != null) { 
		   System.out.println("User Email : " + user.getEmail()) ; 
	   }
	   
 	   assertNull(user) ;
	   
   }
	@Test
	void testUpdateUsers() {
         Users user = new Users() ; 
         //user.setUserId(30);
         user.setEmail("amrkoky18@gmail.com");
         user.setFullName("amr mahmoud ");
         user.setPassword("161825");
        user =  userDao.update(user) ; 
         
         String exEmail = "amrkoky26@gmail.com" ; 
         String acEmail = user.getEmail() ; 
         assertEquals(exEmail, acEmail);
	}
  @Test 
  void testDeleteUsersFound() { 
	  Integer userId = 5 ; 
	  Users user = userDao.get(userId) ; 
	  assertNotNull(user);
	  userDao.delete(userId);
	   user = userDao.get(userId) ; 
	  assertNull(user) ; 
  }
 
  @Test 
  void testListAll() { 
	  java.util.List<Users> users = userDao.listAll() ; 
	  for(int i = 0 ; i  < users.size() ; i++) { 
		  System.out.println(users.get(i).getEmail()) ; 
	  }
	  assertTrue(users.size() > 0) ; 
   }
  @Test
  void testCountAll() {
	  long count = userDao.count() ; 
	  assertTrue(count > 0);
  }
  @Test
  void testFindByEmail() { 
	  Users user = userDao.findByEmail("amrkoky30@gmail.com") ;
	  assertNotNull(user);
  }
}
