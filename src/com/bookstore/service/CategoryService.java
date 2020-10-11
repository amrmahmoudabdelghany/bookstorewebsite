package com.bookstore.service;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.ListUI;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryService {

	private CategoryDAO categoryDao ; 
	private HttpServletRequest request ; 
	private HttpServletResponse response ; 
	
	public CategoryService( HttpServletRequest request , HttpServletResponse response) { 
		this.categoryDao = new CategoryDAO() ; 
		this.request = request ; 
		this.response = response ; 
	}
	
	public void listCategory() throws ServletException, IOException { 
		List<Category> categoryList  = this.categoryDao.listAll() ; 
		request.setAttribute("category_list", categoryList);
		Redirector.forwardToPage(request, response, "category_list.jsp");
		
		
	}
	public void listCategory(String message) throws ServletException, IOException { 
		if(message != null)
		request.setAttribute("message", message);
		listCategory();
		
	}
	public void createCategory() throws ServletException, IOException { 
		String catName = request.getParameter("categoryname") ;
		 
		   Category cat = categoryDao.findCategoryByName(catName) ; 
		    if(cat != null ) { 
		    	String message = "Colud not create category ." + 
		                     "A category with name " + cat.getCategoryName() + " already exists ." ;
		       Redirector.showMessageBackend(request, response, message);
		    }else { 
		    	cat = new Category(catName);
		         categoryDao.create(cat) ; 
		    	listCategory("Category has been created successfully");
		    }
		
	}

	public void editeCategory() throws ServletException, IOException {
		
	  int catId = Integer.parseInt(request.getParameter("id")) ; 
	   Category cat = categoryDao.get(catId)  ;
	   if(cat != null) { 
	   request.setAttribute("category", cat);
	   Redirector.forwardToPage(request, response, "category_form.jsp");
	   }else { 
		   String message =  "Could not find Category with ID [" + catId + "], or it might have been deleted' " ;
		   Redirector.showMessageBackend(request, response, message);
	   }
	}

	public void updateCategory() throws ServletException, IOException {
		 int catId =Integer.parseInt(request.getParameter("categoryId")) ;
		 String catName = request.getParameter("categoryname") ;
		 String msg = "" ; 
		 Category cat = categoryDao.findCategoryByName(catName) ; 
		  if(cat != null && cat.getCategoryId() != catId) { 
			   msg = "Could not update  category." + 
		                       "Category with name " + catName + "is already exsits." ; 
			  Redirector.showMessageBackend(request, response, msg);
		  }else  { 
			  cat = categoryDao.get(catId) ; 
			     if(cat  != null) { 
			     cat.setCategoryName(catName);
				 categoryDao.update(cat) ; 
				 listCategory("Update has been successfuly"); 
			     }else {  
			   	  msg = "Could not update category ." + 
				            "Category with id " +
							  catId + 
							  " is not exsits." ; 
			   	  Redirector.showMessageBackend(request, response, msg);   
				 
		  }
			 
		  }
		
	}
	
	
	
	public void deleteCategory() throws ServletException, IOException { 
	  	 
		int catId = Integer.parseInt(request.getParameter("id")) ; 
		Category cat = categoryDao.get(catId) ; 
		if(cat != null) { 
			BookDAO bookDao = new BookDAO() ; 
		   long countBooks = bookDao.countByCategory(catId) ; 
		   if(countBooks > 0) { 
			   String message = "Could not delete the category with id " + catId +
					   " because it currently contains some books." ; 
			   Redirector.showMessageBackend(request, response, message);
			   
		   }else { 
			categoryDao.delete(catId);
		    listCategory("Category has been deleted successfully");
		   }
		}else { 
			String msg = "Could not delete category."  + "Category With id " + 
		   catId + " is not exists." ; 
			  Redirector.showMessageBackend(request, response, msg);
 		}
		
	}
	

}
