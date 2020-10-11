package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.BookOrder;

public class OrderDAO extends JpaDAO<BookOrder> implements GenericDAO<BookOrder> {

	
	
	
	@Override
	public BookOrder create(BookOrder entity) {
		entity.setOrderDate(new Date());
		
		entity.setOrderStatus("Processing");
		return super.create(entity) ; 
	}
  
	@Override
	public BookOrder update(BookOrder entity) {
		
		return super.update(entity) ; 
	}
 
	@Override
	public BookOrder get(Object id) {
		return super.find(BookOrder.class,id) ; 
	}
	public BookOrder get(Integer orderId , Integer customerId) { 
		Map<String, Object> pars = new HashMap<String, Object>() ; 
		pars.put("orderId", orderId)   ; 
		pars.put("customerId" , customerId) ; 
		List<BookOrder> order =  super.findWithNamedQuery("order.findByIdAndCustomer", pars) ; 
		if(!order.isEmpty()) { 
			return order.get(0) ; 
		}
		return null ; 
	}

	@Override
	public void delete(Object id) {
		 super.delete(BookOrder.class, id);
		
	}

	@Override
	public List<BookOrder> listAll() {
	  return super.findWithNamedQuery("order.listAll") ; 
	}
    public List<BookOrder> listByCustomer(Integer customerId) { 
    	return super.findWithNamedQuery("order.listByCustomer" , "customerId" , customerId) ;
    }
	@Override
	public long count() {
	  
		return super.countWithNamedQuery("order.countAll") ; 
	}
	
	public long countDetailByBook(int bookId) { 
		return super.countWithNamedQuery("orderDetail.countByBook", "bookId", bookId) ; 
	}
	public long countOrderByCustomer(int customerId ) { 
		return super.countWithNamedQuery("order.countByCustomer" , "customerId" , customerId) ; 
	}

	public List<BookOrder> listRecentSales() { 
		return super.findWithNamedQuery("order.listAll", 0, 3) ; 
	}
}
