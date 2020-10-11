<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Write Review</title>
<link href = "css/style.css" rel = "stylesheet" > 
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
 <jsp:directive.include file ="header.jsp" />
<div align ="center">
  
  <h2>Write Review</h2>
   
    <table width  ="60%"> 
      <tr>
        <td>Your Reviews </td>
        <td>&nbsp;</td>
        <td> ${loggedCustomer.fullName }</td>
      </tr>
      <tr><td colspan = "3"><hr/></td></tr>
      <tr>
        
       <td>
        <span id = "book-title">${book.title }</span> <br/>
         <img class = "book-large" alt="book_image" src="data:image/jpg;base64,${book.base64Image }">
       </td>
        <td>
          <h3>Your review has been submited successfully</h3> 
        
        </td>
      </tr>
   
      
    
    </table>

</div>

<jsp:directive.include file = "footer.jsp" />
</body>

</html>