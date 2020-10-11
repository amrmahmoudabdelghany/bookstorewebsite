package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.OrderWith;

import com.bookstore.entity.Article;
@TestMethodOrder(OrderAnnotation.class)
class ArticleDAOTest {
 private static ArticleDAO articleDao ; 
 private static Integer articleId  ; 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	     articleDao = new ArticleDAO() ; 
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		List<Article> artList = articleDao.listAll() ; 
		for(int i = 0 ; i < artList.size() ; i++) { 
		
			articleDao.delete(artList.get(i).getArticleId());
		}
		articleDao.close();
	}

	@Test
	@Order(1)
	void testCreateArticle() {
	    Article article = new Article() ; 
	    article.setArticleTitle("About us");
	    article.setArticleContent("We are compony responsible for derive courses and books");
	   Article res =  articleDao.create(article);
	   
	   assertNotNull(res);
	   assertTrue(res.getArticleId() > 0);
	   articleId = res.getArticleId() ; 
	}

	@Test
	@Order(3)
	void testUpdateArticle() {
		Article art = articleDao.get(articleId) ; 
		art.setArticleTitle("Title after updated done");
		Article res = articleDao.update(art) ;
		assertNotNull(res);
		assertEquals("Title after updated done", res.getArticleTitle());
		
	}


	@Test
	@Order(2)
	void testGet() {
		Article art = articleDao.get(articleId);
		assertNotNull(art) ; 
	}

	@Test
	@Order(4)
	void testDeleteObject() {
		articleDao.delete(articleId);
		Article art = articleDao.get(articleId) ; 
		assertNull(art);
	}

	@Test
	@Order(5)
	void testListAll() {
		for(int i = 0 ; i < 5 ; i++) { 
			Article art = new Article() ; 
			art.setArticleTitle("Title For Article " + i);
			art.setArticleContent("Content For Article " + i) ; 
			articleDao.create(art) ; 
			
		}
		List<Article> artList = articleDao.listAll() ; 
		assertEquals(5, artList.size());
		
	}

	 @Test
	 @Order(6)
	 void testGetByTitleExists() { 
		 String title = "Title For Article 0" ; 
		 Article existsArt = articleDao.getArticleByTitle(title);
		 
		 assertNotNull(existsArt);
	 }
	 @Test
	 @Order(7)
	 void testGetByTitleNotExists() { 
		 String title = "Not Exists Title" ; 
		 Article notExistsArt = articleDao.getArticleByTitle(title);
		 assertNull(notExistsArt);
	 }
	 
	  
	 
	@Test
	@Order(8)
	void testCount() {
	  long actCount = articleDao.count() ; 
	  long expCount =  5 ; 
	  assertEquals(expCount, actCount);
	}


}
