package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import com.bookstore.entity.Customer;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {

	@Override
	public Customer get(Object id) {
		return super.find(Customer.class , id) ; 
	}

	@Override
	public void delete(Object id) {
		 super.delete(Customer.class, id);
		
	}
	
    @Override
    public Customer create(Customer entity) {
    	 entity.setRegisterDate(new Date());
    	
    	return super.create(entity);
    }
	@Override
	public List<Customer> listAll() {
		return super.findWithNamedQuery("customer.listAll") ; 
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("customer.countAll") ; 
	}
	
	public Customer findByEmail(String email) { 
		List<Customer> customers  = super.findWithNamedQuery("customer.findByEmail" , "email" , email) ;
		if(customers != null && customers.size() > 0) { 
			return customers.get(0) ; 
		}
		return null ; 
	}
	public Customer checkLogin(String email , String password) {
		Map<String, Object> paras = new HashMap<String, Object>()  ; 
				paras.put("email", email) ; 
		        paras.put("password", HashGenerator.md5(password)) ; 
		        
		List<Customer> customer = super.findWithNamedQuery("customer.checkLogin", paras) ; 
		 if(customer != null && !customer.isEmpty()) { 
			 return customer.get(0) ; 
		 }
		return null ; 
	}
}
