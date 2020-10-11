<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Review</title>
<link href = "../css/style.css" rel = "stylesheet" > 

<script type="text/javascript" src ="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src ="../js/jquery.validate.min.js"></script>

</head>
<body>
 <jsp:directive.include file="header.jsp" />
  <div align = "center" > 
  
   <h2 > Edit Review</h2>
 
  </div>
   <div align="center">
     
    <form  id = "review-form" action="update_review" method="post" >

        
        <input type = "hidden" name = "reviewId" value = "${review.reviewId }"  />
        <table> 
          
         
          <tr>
          <td> Book :</td>
          <td>${review.book.title } </td>
          </tr>
          <tr>
           <td>Rating :</td>
           <td>${review.rating}</td>
          </tr>
          <tr>
           <td>Customer :</td>
           <td>${review.customer.fullName}</td>
          </tr>
          
           <tr>
           <td>Headline :</td>
           <td><input type = "text" name = "headline" value = "${review.headline}"/></td>
          </tr>
           <tr>
           <td>Comment :</td>
           <td>
           <textarea rows = "4" cols = "70" name = "comment" >
           ${review.comment}
           </textarea>
           
           </td>
          </tr>
          
          
           <tr>
           <td>&nbsp;&nbsp;</td>
           </tr>
          <tr align = "center">
          <td colspan= "2">
          
          <button class = "form-btn" type ="submit">Save </button>
          <button class = "form-btn" id="cancel-btn" type = "button" >Cancel</button>
         
        </td>
          </tr>
        </table>
      </form>
    </div>
    
 <jsp:directive.include file="footer.jsp" /> 
</body>

<script >

  $(function(){
	 
	  $("#review-form").validate({
		  rules : {
			  headline : "required" , 
			  comment : "required"
		  },
		  messages :{
			  headline :"Please enter headline." , 
			  comment : "Please enter comment"
		  }
	  
	  }) ;
	  $("#cancel-btn").on("click" , function(){
		  history.go(-1) ; 
	  });
  });
   
</script>
</html>