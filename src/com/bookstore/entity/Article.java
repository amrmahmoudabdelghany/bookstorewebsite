package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@NamedQueries({
	@NamedQuery(name = "article.listAll" , query = "SELECT a FROM Article a") , 
	@NamedQuery(name = "article.countAll" , query = "SELECT COUNT(a) FROM Article a") , 
	@NamedQuery(name = "article.findByTitle" , query = "SELECT a FROM Article a WHERE a.articleTitle = :title")
})
@Table(name = "article" , catalog = "bookstoredb" , uniqueConstraints = @UniqueConstraint(columnNames = "article_title"))
public class Article implements java.io.Serializable{

	private Integer articleId ; 
	private String articleTitle ; 
	private String articleContent ;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "article_id" , unique = true , nullable = false)
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
	@Column(name = "article_title" , unique = true , nullable = false , length = 30)
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	@Column(name = "article_content"  , nullable = false , length = 500 )
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	} 
	
	
	
	
}
