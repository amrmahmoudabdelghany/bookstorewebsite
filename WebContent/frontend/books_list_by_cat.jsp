<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books in ${category.categoryName }</title>
<link href = "css/style.css" rel = "stylesheet"> 
</head>
<body>
 <jsp:directive.include file="header.jsp"/>
   <div align = "center" >   
    <h2>${category.categoryName }</h2>
</div>


 <div class = "book-group"  > 
   <c:forEach var = "book" items="${listbooks }">
   <div class = "book">
     <div>
     <a href = "view_book?id=${book.bookId }">
     <img alt="Image Preview" id="thumbnail" class = "book-small" src = "data:image/jpg;base64,${book.base64Image }">
      </a>
     </div>
     <div>
       <a class = "inhA" href = "view_book?id=${book.bookId }">
     <b> ${book.title }</b>
     </a>
     </div>
       <div>
       <jsp:directive.include file="book_rating.jsp"/>
     </div>
     <div>by <i>${book.author }</i></div>
     <div><b>$${book.price }</b></div>
     </div>
   </c:forEach>
 
 </div>
<jsp:directive.include file="footer.jsp"/>
</body>
</html>