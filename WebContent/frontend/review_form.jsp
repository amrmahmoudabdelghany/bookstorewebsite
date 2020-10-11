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
   <form id = "review-form" action = "submit_review" method ="post">
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
         
         <div id="rateYo"></div> 
         <input type = "hidden" name = "rating" id = "rating" value = "0" />
         <input type ="hidden" name = "bookId" value = "${book.bookId }" /> 
         <br/><br/>
         <input class = "form-input" type = "text" name = "headline" size = "60" placeholder ="Headline or summary for your review (required)"/>
         <br/>
         <br/>
         <textarea name = "comment" rows="10" cols="70" placeholder = "write your review details..."></textarea>
         
        </td>
      </tr>
      <tr>
       <td colspan = "3" align = "center">
        <button type = "submit">Submit</button>
        &nbsp;&nbsp;&nbsp;
        <button id = "btn-cancel">Cancel</button>
       </td>
      </tr>
      
    
    </table>
 </form>
</div>

 <script type="text/javascript">
 $(function () {
	 
	 $("#review-form").validate({
			rules : {
	           headline : "required" , 
	           comment : "required"
			} , 
			messages :{
			    headline : "Please enter headline" , 
			    comment : "Please enter comment" 
			}
		}) ; 
	 
	 
	  $("#rateYo").rateYo({
	    starWidth: "40px" , 
	    fullStar : true  , 
	    onSet : function(rating , rateYoInstance) { 
	    	$("#rating").val(rating) ; 
	    }
	  });
	 
	  $("#btn-cancel").on("click" , function() {
		history.go(-1) ; 
	})
	});
 </script>
 <jsp:directive.include file = "footer.jsp" />
</body>

</html>