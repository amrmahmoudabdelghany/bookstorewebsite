package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Category;

class CategoryDAOTest  {
  static CategoryDAO categoryDao ; 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		categoryDao = new CategoryDAO() ; 
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	    categoryDao.close();
	}

	@Test
	void testCreateCategory() {
		Category cat = new Category() ; 
		cat.setCategoryName("Health");
		Category created = categoryDao.create(cat) ; 
		assertTrue(created != null && created.getCategoryId() > 0);
	}

	@Test
	void testUpdateCategory() {
		Category cat = new Category() ; 
		cat.setCategoryName("Core Java");
		cat.setCategoryId(16);
		Category updated = categoryDao.update(cat) ; 
		assertTrue(updated.getCategoryId() == cat.getCategoryId() && updated.getCategoryName().equals(cat.getCategoryName()));
		
	}

	@Test
	void testGet() {
		Category cat = categoryDao.get(16) ; 
		assertTrue(cat.getCategoryName().equals("Core Java" ));
	}
	@Test
	void testGetNull() { 
		Category cat = categoryDao.get(100) ;
	      if(cat == null) { 
	    	  System.out.println("Category with id 100  is not exists" );
	      }
		assertNull(cat);
	}
	
	

	@Test
	void testDeleteObject() {
		categoryDao.delete(19);
		assertTrue(categoryDao.get(19) == null);
	}

	@Test
	void testListAll() {
	 java.util.List<Category> list = 	categoryDao.listAll() ; 
	  list.forEach(c ->System.out.println(c.getCategoryName()));
	  assertTrue(list.size() > 0);
	}

	@Test
	void testCount() {
		long c = categoryDao.count() ; 
		System.out.println("Category Count : " + c);
		
		assertTrue( c > 0);
	}

}
