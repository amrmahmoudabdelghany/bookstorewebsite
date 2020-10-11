package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {
  

	
	@Override
	public Category create(Category entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public Category update(Category entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Category get(Object id) {
		return super.find(Category.class, id);
	}

	@Override
	public void delete(Object id) {
		 super.delete(Category.class, id);
	}

	@Override
	public List<Category> listAll() {
		return super.findWithNamedQuery("category.findAll") ;
	}
   
	@Override
	public long count() {
		return (long) super.countWithNamedQuery("category.countAll") ; 
	}
	
	public Category findCategoryByName(String name ) { 
		 List<Category> cats  = super.findWithNamedQuery("category.findByName" , "name",name) ; 
		 
		 if(cats != null && cats.size() > 0) { 
			 return cats.get(0) ; 
		 }
		
		return null ; 
		
	}

}
