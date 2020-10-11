package com.bookstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CatalogTest {

	
	public static void main(String args[]) { 
		Category cat = new Category("aaaaaaaaaaaaaaaaaaaaaaaaaaa") ; 
		
		EntityManagerFactory entityF = Persistence.createEntityManagerFactory("BookStoreWebsite") ; 
	    EntityManager entityM = entityF.createEntityManager() ; 
	    entityM.getTransaction().begin();
	    entityM.persist(cat);
	    entityM.getTransaction().commit();  
	    entityM.close();
	}
}
