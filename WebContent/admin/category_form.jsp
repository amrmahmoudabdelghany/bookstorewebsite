<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Category</title>
<link href = "../css/style.css" rel = "stylesheet" > 
<script type="text/javascript" src ="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src ="../js/jquery.validate.min.js"></script>

</head>
<body>
 <jsp:directive.include file="header.jsp" />
  <div align = "center" > 
   <c:if test="${category == null }">
 <h2 class = "page_header"> Create New Category</h2>
 </c:if>
 <c:if test = "${category != null }" >
   <h2 > Edit Category</h2>
 </c:if>
  </div>
   <div align="center">
     <c:if test="${category == null }">
      <form id ="category-form" action="create_category" method="post" >
    </c:if>
    <c:if test = "${category != null }" >
    <form  id = "category-form" action="update_category" method="post" >
    </c:if>
        
        <input type = "hidden" name = "categoryId" value = "${category.categoryId }"  />
        <table> 
          
         
          <tr>
          <td> Category Name </td>
          <td><input class = "form-input" type = "text" size = "20" id = "categoryname" name = "categoryname" value  ="${category.categoryName }" /></td>
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
	  $("#category-form").validate({
		  rules : {
			  categoryname : "required" 
		  },
		  messages :{
			  categoryname :"Please enter category name." 
		  }
	  
	  }) ;
	  $("#cancel-btn").on("click" , function(){
		  history.go(-1) ; 
	  });
  });
   
</script>
</html>