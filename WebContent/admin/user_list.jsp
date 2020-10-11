<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href = "../css/style.css" rel = "stylesheet" >
<script type="text/javascript" src ="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src ="../js/jquery.validate.min.js"></script>
<title>Insert title here</title>
</head>
<body>
 <jsp:directive.include file="header.jsp" />
 
   <div align="center">
     <h2 class = "page_header">Users Management</h2>
     <h3><a class = "create-a" href="user_form.jsp" >Create New User</a></h3>
      <c:if test="${message!=  null}">
       <h4 class = "list-message"> ${message} </h4>
      </c:if>
   </div>
   
   <div align ="center" > 
     <table class = "table" border="1" cellpadding ="5px" > 
      <tr> 
       <th>Index</th>
       <th>ID</th>
       <th>Email</th>
       <th>Full Name</th>
       <th>Actions </th>
      </tr>
      <c:forEach var="user" items="${users }" varStatus="varStatus">
      <tr>
        <td>${varStatus.index }</td>
        <td>${user.userId } </td>
        <td>${user.email } </td>
        <td>${user.fullName } </td>
        <td>
          <a href="edit_user?id=${user.userId}">Edit</a> &nbsp; &nbsp; 
          <a href="javascript:void(0)" class ="deletelink" id="${user.userId}">Delete</a>
        </td>
       </tr>
      </c:forEach>
     </table>
   </div>
 <jsp:directive.include file="footer.jsp" /> 
</body>
<script>
 
 $(function() {
	$(".deletelink").each(function() {
		$(this).on("click" ,function() {
			userId = $(this).attr("id") ;
			if(confirm("Are you sure you want to delete the user ID " + userId)) { 
				 
				 window.location = "delete_user?id=" + userId ; 
				
			 }
		}); 
	});  
 });
</script>
</html>