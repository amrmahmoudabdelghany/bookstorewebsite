package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Review;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {

	@Override
	public Review get(Object id) {
		return super.find(Review.class, id) ; 
	}

	@Override
	public void delete(Object id) {
		 super.delete(Review.class, id);
		
	}

	@Override
	public List<Review> listAll() {
		return super.findWithNamedQuery("review.listAll") ; 
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("review.countAll") ; 
	}
	@Override
	public Review create(Review entity) {
		 entity.setReviewTime(new Date());
		return super.create(entity);
	}
    public long countByBook(int bookId) { 
    	return super.countWithNamedQuery("review.countByBook", "bookId", bookId) ; 
    }

	public long countByCustomer(int customerId) {
		return super.countWithNamedQuery("review.countByCustomer","customerId", customerId) ; 
	}
	
	public Review findByCustomerAndBook(int customerId , int bookId) { 
		Map<String, Object> pars = new HashMap<String, Object>() ; 
		pars.put("customerId", customerId) ;
		pars.put("bookId" , bookId) ; 
		List<Review> res = super.findWithNamedQuery("review.findByCustomerAndBook", pars) ; 
		System.out.println("Result Size : " + res.size()) ; 
		if(res != null && !res.isEmpty()) { 
			return res.get(0) ; 
		}
		return null ; 
	}
	
	public List<Review> listMostRecentReviews() { 
		
		return super.findWithNamedQuery("review.listAll" , 0 , 3) ; 
	}
}