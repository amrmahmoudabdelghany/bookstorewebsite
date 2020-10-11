<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New User</title>
<link href = "../css/style.css" rel = "stylesheet"> 
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

</head>
<body>
 <jsp:directive.include file="header.jsp" />
  <div align = "center" > 
   <c:if test="${user == null }">
 <h2 class = "page_header"> Create New User</h2>
 </c:if>
 <c:if test = "${user != null }" >
   <h2 > Edit User</h2>
 </c:if>
  </div>
   <div align="center">
     <c:if test="${user == null }">
      <form  id = "user-form" action="create_user"  method="post" >
    </c:if>
    <c:if test = "${user != null }" >
    <form id ="user-form" " action="update_user" method="post" >
    </c:if>
        
        <input type = "hidden" name = "userId" value = "${user.userId }"  />
        <table> 
          
          <tr>
           <td>Email </td>
           <td><input class = "form-input" type = "text"  size = "20" id = "email" name = "email" value = "${user.email }"/></td>
          </tr>
          <tr>
          <td> Full Name </td>
          <td><input class = "form-input" type = "text" size = "20" id = "fullname" name = "fullname" value  ="${user.fullName }" /></td>
          </tr>
          <tr>
           <td> Password </td>
           <td>
           <input class ="form-input" type ="password" size = "20" id = "password" name ="password" value = "${user.password }" />
           </td>
          </tr>
           <tr>
           <td>&nbsp;&nbsp;</td>
           </tr>
          <tr>
          <td colspan= "2">
          <button class = "form-btn" type ="submit">Save</button>
         <button  class = "form-btn" onClick="javascript:history.go(-1)" > Cancel </button>
        </td>
          </tr>
        </table>
      </form>
    </div>
    
 <jsp:directive.include file="footer.jsp" /> 
</body>

<script >

$(function() { 
	console.log("On Ready...") ; 
	 $("#user-form").validate({
		 rules: { 
			 email :{
				 required : true  , 
				 email : true 
			 }, 
			 fullname : "required"  , 
			 password : "required"
			 
		 } , 
		 messages: { 
			 email : {
			  required : "Please enter email."  , 
			  email : "Please enter a valid email address."
			 } ,
			 fullname :"Please enter full name." , 
			 password : "Please enter password."
		 }
	 }) ; 
}) ; 


</script>
</html>