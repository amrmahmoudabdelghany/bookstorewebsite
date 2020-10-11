package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.ArticleDAO;
import com.bookstore.entity.Article;

public class ArticleService {

	private HttpServletRequest request ; 
	private HttpServletResponse response ; 
	private ArticleDAO articleDao ; 
	
	
	public ArticleService(HttpServletRequest request , HttpServletResponse response) {
		this.request = request ; 
		this.response = response ; 
		this.articleDao = new ArticleDAO() ; 
	}
	
	
	public void listAll() throws ServletException, IOException { 
		List<Article> artList = articleDao.listAll() ; 
		request.setAttribute("articleList", artList);
		Redirector.forwardToPage(request, response, "article_list.jsp");  
		
	}
	public void listAll(String message) throws ServletException, IOException { 
		request.setAttribute("message" , message) ; 
		listAll() ; 
	}


	public void createArticle() throws IOException, ServletException {
		String title = request.getParameter("title") ; 
		String content = request.getParameter("content") ; 
	    Article artByTitle = articleDao.getArticleByTitle(title) ; 
	    String message = "" ; 
	    if(artByTitle != null) { 
	    	message = "Colud not create article with this title [ " + title + " ] because it alread exists." ; 
	    	Redirector.showMessageBackend(request, response, message);
	    }else { 
	      Article art = new Article() ; 
	      art.setArticleTitle(title);
	      art.setArticleContent(content);
	      articleDao.create(art);
	      listAll("Article has been created successfully." ) ; 
	    }
	    		
		
	}


	public void deleteArticle() throws ServletException, IOException {
	  Integer articleId = Integer.parseInt(request.getParameter("id")) ; 
	  Article art = articleDao.get(articleId) ; 
	  String message = "" ; 
	  if(art != null) { 
		  articleDao.delete(articleId);
		  message = "Article with id [ " + articleId + " ] has been deleted successfully." ; 
	  }else { 
		  message = "Could not delete article with id [ "  + articleId + " ]  , it may be deleted by another admin ." ;  
		
	  }
 		
	  listAll(message) ; 
	}


	public void editArticle() throws ServletException, IOException {
	   Integer articleId = Integer.parseInt(request.getParameter("id")) ; 
	   Article article = articleDao.get(articleId);
	   if(article != null) { 
		   request.setAttribute("article", article);
		   Redirector.forwardToPage(request, response, "article_form.jsp");
		   
	   }else { 
		String message = "Colud not edit article with id [ " + articleId + " ] , it may be deleted by another admin."  ;   
		   
		listAll(message) ;
	   }
	   
		
	}


	public void updateArticle() throws ServletException, IOException {
		Integer articleId = Integer.parseInt(request.getParameter("articleId")) ; 
		String title = request.getParameter("title") ; 
		String content = request.getParameter("content") ; 
		String message = "" ; 
		Article articleByTitle = articleDao.getArticleByTitle(title) ; 
		
		if(articleByTitle != null &&
				articleByTitle.getArticleId() != articleId) { 
			 message = "Could not update article , title is already exsits ." ; 
			Redirector.showMessageBackend(request, response, message);
			
		}else { 
			Article art = articleDao.get(articleId); 
			if(art != null) { 
				art.setArticleTitle(title);
				art.setArticleContent(content);
				articleDao.update(art); 
				message = "Article with id [ " + articleId + " ]  has been updated successfully." ; 
				listAll(message) ;  
			}else { 
				message = "Could not find article with id [ " + articleId + " ] , it may be deleted by another admin." ; 
				listAll(message) ; 
				
			}
		}
		
	}


	public void showDetailArticle() throws ServletException, IOException {
	  Integer articleId = Integer.parseInt(request.getParameter("id")) ;
	  Article art = articleDao.get(articleId) ; 
	  
	  if(art != null) { 
		  request.setAttribute("article", art);
		  Redirector.forwardToPage(request, response, "article_detail.jsp");
	  }else { 
		  String message = "Could not find article with id [ " + articleId + " ] , it may be deleted by another admin." ; 
		  Redirector.showMessageBackend(request, response, message);
	  }
 		
		
	}


	
	
	
}
