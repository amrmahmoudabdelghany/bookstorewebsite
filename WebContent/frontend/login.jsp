<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Login</title>
<link href = "css/style.css" rel = "stylesheet" > 
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
<div align ="center">
  
  <h2>Customer Login</h2>
  <c:if test="${message != null }">
   <h4 class = "list-message">${message }</h4>
  </c:if>
 <form class = "login-form" id="login-form" action ="login" method="post">

	 <table>
	   	<tr>
	   	  <td>Email:</td>
	   	  <td><input class = "form-input" type = "text" name = "email" id ="email" size = "20" /> </td>
	   	  
	   	</tr>
	   	<tr>
	   	  <td>Password : </td>
	   	  <td><input class = "form-input" type = "password" name ="password"  id="password" size ="20"/></td>
	   	</tr> 
	   	<tr>
	   	  <td colspan="2" align ="center"><button class ="form-btn" type = "submit">Login</button></td>
	   	</tr>
	 </table>
	 
 </form>

</div>

<script >
  $(function() {
	  console.log("On Ready...") ; 
	$("#login-form").validate({
		rules : {
           email : { 
        	   required : true , 
        	   email : true 
           },
	       password : "required"
           
		} , 
		messages :{
			email : {
				requied : "Please enter email."  , 
				email : "Please enter valid email"
			},
			password :"Please enter password."
		
		}
	}) ;   
  }); 
</script>
</body>

</html>