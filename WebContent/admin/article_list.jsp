<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Article Management</title>
<link href = "../css/style.css" rel = "stylesheet" >
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" > 
<script type="text/javascript" src ="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src ="../js/jquery.validate.min.js"></script>
</head>
<body>
<jsp:directive.include file="header.jsp" />

 
   <div align="center">
     <h2 class = "page_header">Article Management</h2>
     <h3><a class = "create-a"href="new_article" >Create New Article</a></h3>
      <c:if test="${message!=  null}">
       <h4 class = "list-message"> ${message} </h4>
      </c:if>
   </div>
   
   <div align ="center" > 
     <table class = "table table-striped table-bordered" border="1" cellpadding ="5px" style = "width : 50%" > 
      <thead>
         <tr> 
       <th>Index</th>
       <th>ID</th>
       <th>Title</th>
       <th>Actions</th>
      </tr>
      
      </thead>
       <tbody>
          <c:forEach var="article" items="${articleList }" varStatus="varStatus">
       
      <tr>
        <td>${varStatus.index }</td>
        <td>${article.articleId } </td>
        <td>${article.articleTitle } </td>
        <td>
          <a href="edit_article?id=${article.articleId}">Edit</a> |
          <a href="javascript:void(0)" class ="deletelink" id="${article.articleId}"  >Delete</a> |
          <a href = "detail_article?id=${article.articleId}">Details</a>
          
        </td>
       </tr>
      </c:forEach>
          
       </tbody>      
     </table>
   </div>
 
  
   <jsp:directive.include file="footer.jsp" /> 
   <script >
     $(function() { 
    	 $(".deletelink").each(function(){
    		 $(this).on("click" , function() { 
        		 var articleId = $(this).attr("id") ; 
        		 if(confirm("Are you sure you want to delete article with id " + articleId + "?")) { 
        			 window.location = "delete_article?id=" + articleId ; 
        		 }
        		 
        	 }) ; 
    	 });
    	 
     }) ; 
   </script>
</body>
</html>