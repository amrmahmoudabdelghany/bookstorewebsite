package com.bookstore.dao;


import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bookstore.entity.Book;
import com.bookstore.entity.Users;

import antlr.collections.List;

public class JpaDAO<E> {
	private static EntityManagerFactory entityManagerFactory ; 
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite") ; 
	   
	}

 
 
 
 
 public E create(E entity) { 
	 EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
	  entityManager.getTransaction().begin();  
	  entityManager.persist(entity);
	  entityManager.flush();  
	  entityManager.refresh(entity);
	  entityManager.getTransaction().commit(); 
	 entityManager.close() ; 
	 return entity ;
 }
 
 public E update(E entity) { 
	 EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
    entityManager.getTransaction().begin(); 
    entity = entityManager.merge(entity) ; 
    entityManager.getTransaction().commit();
	 entityManager.close();
	 return entity ; 
 }
	
 public E find(Class<E> type , Object userId) { 
	 EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
	  E entity = entityManager.find(type, userId) ; 
     if(entity != null )   entityManager.refresh(entity);
      entityManager.close(); 
      return entity ;   
 }
 
 public void delete(Class<E> type , Object id) { 
	 EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
	 Object ref = entityManager.getReference(type, id) ;
	 entityManager.getTransaction().begin();
	 entityManager.remove(ref);
	 entityManager.getTransaction().commit();
	 entityManager.close();
 }
 
 public java.util.List<E> findWithNamedQuery(String query ) { 
	 
	 EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
	 Query q = entityManager.createNamedQuery(query);
	 java.util.List<E> res = q.getResultList(); 
	
	 entityManager.close(); 
     return res ; 
 	 
 }
 
 public java.util.List<E> findWithNamedQuery(String query , String paraName , Object paraValue) { 
	 EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
	 Query q = entityManager.createNamedQuery(query) ;
	 q.setParameter(paraName, paraValue) ; 
	 java.util.List<E> res = 	q.getResultList() ;  
	 entityManager.close() ; 
	 return res ; 
 }
 public java.util.List<E> findWithNamedQuery(String query , Map<String, Object> pars) { 
	 EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
	 Query q = entityManager.createNamedQuery(query) ; 
	Set<Map.Entry<String, Object>> paras  = pars.entrySet() ; 
	for(Entry<String, Object> par : paras ) { 
		q.setParameter(par.getKey(), par.getValue()) ; 
	}
	java.util.List<E> res = q.getResultList() ; 
	entityManager.close();
	return res ; 
	
  }
 public long countWithNamedQuery(String namedQ) { 
	 EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
	 Query query = entityManager.createNamedQuery(namedQ) ;
	 long res = (long) query.getSingleResult() ; 
	 entityManager.close();
	 return res  ; 
 }
 public long countWithNamedQuery(String namedQ , String paraName , Object paraValue ) { 
	 EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
	 Query query = entityManager.createNamedQuery(namedQ) ;
	 query.setParameter(paraName , paraValue) ; 
	 long res = (long) query.getSingleResult() ; 
	 entityManager.close();
	 return res  ; 
 }
public java.util.List<E> findWithNamedQuery(String query, int start, int end) {
	EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
	Query q = entityManager.createNamedQuery(query) ;
	q.setFirstResult(start) ; 
	q.setMaxResults(end) ; 
	
	java.util.List<E> res = q.getResultList() ; 
	entityManager.close();
	return res ; 
}


public java.util.List<Object[]> findWithNamedQueryObject(String query, int start, int end) {
	EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
	Query q = entityManager.createNamedQuery(query) ;
	q.setFirstResult(start) ; 
	q.setMaxResults(end) ; 
	
	java.util.List<Object[]> res = q.getResultList() ; 
	entityManager.close();
	return res ; 
}

public void close() { 
	if(entityManagerFactory != null) { 
		entityManagerFactory.close();
	}
}
}
