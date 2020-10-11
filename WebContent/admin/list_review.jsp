<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reviews List Administration Management</title>
  <link  href = "../css/style.css" rel = "stylesheet" >

<!-- 
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" > 
  
  <link rel="stylesheet"  href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
 -->
 <script type="text/javascript" src ="../js/jquery-3.3.1.js"></script> 


</head>
<body>
 <jsp:directive.include file="header.jsp" />
 
   <div  align="center">
     <h2 class = "page_header">Reviews Management</h2>
     
      <c:if test="${message!=  null}">
       <h4 class = "list-message"> ${message} </h4>
      </c:if>
      
   </div>
   
   <div align ="center" > 
     <table class = "table" border="1" cellpadding ="5px"   > 
      <tr> 
       <th>Index</th>
       <th>ID</th>
       <th>Book</th>
       <th>Rating</th>
       <th>Headline</th>
       <th>Customer</th>
       <th>Review On</th>
       <th>Actions </th>
      </tr>
      <c:forEach var="review" items="${reviewList }" varStatus="varStatus">
      <tr>
        <td>${varStatus.index }</td>
        <td>${review.reviewId } </td>
        <td>${review.book.title } </td>
         <td>${review.rating } </td>
         <td>${review.headline }</td>
          <td>${review.customer.fullName } </td>
           <td>${review.reviewTime } </td>
        <td>
          <a href="edit_review?id=${review.reviewId}">Edit</a> &nbsp; &nbsp; 
          <a href="javascript:void(0)" class="deletelink" id="${review.reviewId }" >Delete</a>
        </td>
       </tr>
      </c:forEach>
     </table>
   </div>
 <jsp:directive.include file="footer.jsp" /> 

	  <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>

<script>
$(function() {
	$("#datatabel").DataTable() ; 
	$(".deletelink").each(function() { 
		$(this).on("click" , function() { 
		  reviewId = $(this).attr("id") ; 
		  if(confirm("Are you sure you want to delete the review with ID " + reviewId)) { 
				 window.location = "delete_review?id=" + reviewId ; 
				 
			 }
		});
	})
}) ; 
 
</script>
</body>
</html>