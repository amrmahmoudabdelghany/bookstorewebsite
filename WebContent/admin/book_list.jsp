<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Management</title>
<link href = "../css/style.css" rel = "stylesheet" >

<script type="text/javascript" src ="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src ="../js/jquery.validate.min.js"></script>
</head>
<body>
<jsp:directive.include file="header.jsp" />

 
   <div align="center">
     <h2 class = "page_header">Book Management</h2>
     <h3><a class = "create-a"href="new_book" >Create New Book</a></h3>
      <c:if test="${message!=  null}">
       <h4 class = "list-message"> ${message} </h4>
      </c:if>
   </div>
   
   <div align ="center" > 
     <table class = "table table-striped table-bordered" border="1" cellpadding ="5px" > 
      <tr> 
       <th>Index</th>
       <th>ID</th>
       <th>Image</th>
       <th>Title</th>
       <th>Author </th>
       <th>Category </th>
       <th>Price </th>
       <th>Last Updated </th>
       <th>Actions</th>
      </tr>
      
      <c:forEach var="book" items="${bookList }" varStatus="varStatus">
       
      <tr>
        <td>${varStatus.index }</td>
        <td>${book.bookId } </td>
        <td>
          <img alt="Book Image" src="data:image/jpg;base64,${book.base64Image }" width = "85" height = "110">
         </td>
        <td>${book.title } </td>
        <td>${book.author } </td>
        <td>${book.category.categoryName } </td>
        <td>$${book.price } </td>
        <td><fmt:formatDate pattern = "MM/dd/yyyy" value="${book.lastUpdateTime }"/>    </td>
        
        <td>
          <a href="edit_book?id=${book.bookId}">Edit</a> &nbsp; &nbsp; 
          <a href="javascript:void(0)" class ="deletelink" id="${book.bookId}"  >Delete</a>
        </td>
       </tr>
      </c:forEach>
     </table>
   </div>
 
  
   <jsp:directive.include file="footer.jsp" /> 
   <script >
     $(function() { 
    	 $(".deletelink").each(function(){
    		 $(this).on("click" , function() { 
        		 var bookId = $(this).attr("id") ; 
        		 if(confirm("Are you sure you want to delete book with id " + bookId + "?")) { 
        			 window.location = "delete_book?id=" + bookId ; 
        		 }
        		 
        	 }) ; 
    	 });
    	 
     }) ; 
   </script>
</body>
</html>