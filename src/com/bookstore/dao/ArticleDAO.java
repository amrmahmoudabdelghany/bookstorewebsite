package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.Article;

public class ArticleDAO extends JpaDAO<Article> implements GenericDAO<Article> {

	@Override
	public Article create(Article entity) {
		return super.create(entity); 
	}

	@Override
	public Article update(Article entity) {
		return super.update(entity);
	}

	@Override
	public Article get(Object id) {
		return super.find(Article.class, id) ; 
	}
    
	public Article getArticleByTitle(String title) { 
		List<Article> artList = super.findWithNamedQuery("article.findByTitle" , "title" , title) ; 
		  if(artList != null && !artList.isEmpty()) { 
			  return artList.get(0) ; 
		  }
		
		return null ; 
	}
	@Override
	public void delete(Object id) {
		super.delete(Article.class, id);
		
	}

	@Override
	public List<Article> listAll() {
		return super.findWithNamedQuery("article.listAll") ; 
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("article.countAll") ; 
	}

}
