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

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
 <jsp:directive.include file ="header.jsp" />
<div align ="center">
  
  <h2>Write Review</h2>
   <form id = "review-form">
    <table width  ="60%"> 
      <tr>
        <td colspan = "2"> <h3> You Already Wrote a review</h3> </td>
        <td>&nbsp;</td>
        <td align ="left" width = "20%"> ${loggedCustomer.fullName }</td>
      </tr>
      <tr><td colspan = "3"><hr/></td></tr>
      <tr>
        
       <td>
        <span id = "book-title">${book.title }</span> <br/>
         <img class = "book-large" alt="book_image" src="data:image/jpg;base64,${book.base64Image }">
       </td>
        <td>
         
         <div id="rateYo"></div> 
         <input type = "hidden" name = "rating" id = "rating" value = "0" />
         <input type ="hidden" name = "bookId" value = "${book.bookId }" /> 
         <br/><br/>
         <input class = "form-input" type = "text" name = "headline" size = "60" readonly = "readonly" value = "${review.headline }"/>
         <br/>
         <br/>
         <textarea name = "comment" rows="10" cols="70" readonly ="readonly" >${review.comment }</textarea>
           
         
         
        </td>
      </tr>
     
      
    
    </table>
 </form>
</div>

 <script type="text/javascript">
 $(function () {
	
	 
	  $("#rateYo").rateYo({
	    starWidth: "40px" , 
	    fullStar : true  , 
	    readOnly : true  , 
	    rating : ${review.rating}
	  
	  });
	 
	});
 </script>
 <jsp:directive.include file = "footer.jsp" />
</body>

</html>