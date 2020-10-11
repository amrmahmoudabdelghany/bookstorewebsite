<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link href = "../css/style.css" rel = "stylesheet" >

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" > 

<script type="text/javascript" src ="../js/jquery-3.3.1.js"></script>

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>

<title>Customer Management</title>
</head>
<body>
 <jsp:directive.include file="header.jsp" />
 
   <div align="center">
     <h2 class = "page_header">Customer Management</h2>
     <h3><a class = "create-a" href="new_customer" >Create New Customer</a></h3>
      <c:if test="${message!=  null}">
       <h4 class = "list-message"> ${message} </h4>
      </c:if>
   </div>
   
   <div align ="center"  > 
    <div style = "width : 80%">
      
     <table id = "mytable" class = "table table-striped table-bordered" border="1"   > 
      <thead> 
        <tr> 
       <th>Index</th>
       <th>ID</th>
       <th>Email</th>
       <th>First Name</th>
       <th>Last Name</th>
       <th>City </th>
       <th>Country </th>
       <th>Registered Date </th>
       <th>Action</th>
      </tr>
       </thead>
       <tbody>
           <c:forEach var="customer" items="${customerList }" varStatus="varStatus">
      <tr>
        <td>${varStatus.index }</td>
        <td>${customer.customerId } </td>
        <td>${customer.email } </td>
        <td>${customer.firstname } </td>
        <td>${customer.lastname } </td>
         <td>${customer.city } </td>
        <td>${customer.countryName } </td>
        <td>${customer.registerDate } </td>
        
        <td>
          <a href="edit_customer?id=${customer.customerId}">Edit</a> &nbsp; &nbsp; 
          <a href="javascript:void(0)" class ="deletelink" id="${customer.customerId}">Delete</a>
        </td>
       </tr>
      </c:forEach>
       
       </tbody>
     </table>
    
    </div>
   </div>
 <jsp:directive.include file="footer.jsp" /> 
</body>
<script>
 
 $(function() {
	 
	 $("#mytable").DataTable() ; 
	$(".deletelink").each(function() {
		$(this).on("click" ,function() {
			customerId = $(this).attr("id") ;
			if(confirm("Are you sure you want to delete the user ID " + customerId)) { 
				 
				 window.location = "delete_customer?id=" + customerId ; 
				
			 }
		}); 
	});  
 });
</script>
</html>