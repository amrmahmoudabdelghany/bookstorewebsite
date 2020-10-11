package com.bookstore.dao;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {
   
	

	public Users create(Users user) { 
		String pass = user.getPassword() ; 
		String encPass = HashGenerator.md5(pass) ; 
		user.setPassword(encPass); 
		 
		return super.create(user) ; 
	}
	
	@Override
	public Users update(Users entity) {
		return super.update(entity) ; 
	}

	@Override
	public Users get(Object userId) {
		return super.find(Users.class, userId) ;
	}

	@Override
	public void delete(Object userId) {
	     super.delete(Users.class, userId);
	}
    public Users findByEmail(String email) { 
      List<Users> users = super.findWithNamedQuery("user.findByEmail"  , "email", email) ;
       if(users != null && users.size() == 1) { 
    	   return users.get(0) ; 
       }
      
      return null ; 
    }
    public boolean checklogin(String userName , String password) { 
    	 Map<String , Object> paras  = new HashMap<String, Object>() ; 
    	   
    	   password = HashGenerator.md5(password) ; 
     	   paras.put("email", userName) ;
    	   paras.put("password", password) ; 
    	   
    	   List<Users> res =   super.findWithNamedQuery("user.checkLogin", paras) ; 
    	      if(res.size() == 1) { 
    	    	  return true ; 
    	      }
    	return false ; 
    }
	@Override
	public List<Users> listAll() {
		return super.findWithNamedQuery("user.findAll") ; 
	}
    
	@Override
	public long count() {
		return  super.countWithNamedQuery("user.countAll");
	}

	
}
