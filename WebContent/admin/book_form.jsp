<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
      <%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Book</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/richtext.min.css">

<link href = "../css/style.css" rel = "stylesheet"> 
<link href = "../css/jquery-ui.min.css" rel = "stylesheet" > 
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type ="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type ="text/javascript" src="../js/jquery.richtext.min.js"></script>
</head>

<body>
 		<jsp:directive.include file="header.jsp" />
  <div align = "center" > 
   <c:if test="${book == null }">
 <h2 class = "page_header"> Create New Book</h2>
 </c:if>
 <c:if test = "${book != null }" >
   <h2 > Edit book</h2>
 </c:if>
  </div>
   <div align="center">
     <c:if test="${book == null }">
      <form  id = "form" action="create_book"  method="post" enctype = "multipart/form-data" >
    </c:if>
    <c:if test = "${book != null }" >
    <form id ="form" action="update_book" method="post" enctype = "multipart/form-data" >
    </c:if>
        
        <input type = "hidden" name = "bookId" value = "${book.bookId }"  />
        <table width = "80%"> 
          
          <tr>
           <td>Category : </td>
           <td>
           <select name = "category">
           <c:forEach items="${categoryList}" var = "cat">
           <c:if test="${cat.categoryId eq book.category.categoryId }">
           <option value = "${cat.categoryId}" selected >
           </c:if>
           <c:if test="${cat.categoryId ne book.category.categoryId }">
            <option value = "${cat.categoryId}" >
           </c:if>
            
               ${cat.categoryName }
            </option>
           </c:forEach>
           </select>
           </td>
          </tr>
          <tr>
          <td> Title : </td>
          <td><input class = "form-input" type = "text" size = "20" id = "title" name = "title" value  ="${book.title}" /></td>
          </tr>
          <tr>
           <td> Author : </td>
           <td>
           <input class ="form-input" type ="text" size = "20" id = "author" name ="author" value = "${book.author }" />
           </td>
          </tr>
          <tr>
           <td> ISBN : </td>
           <td>
           <input class ="form-input" type ="text" size = "20" id = "isbn" name ="isbn" value = "${book.isbn }" />
           </td>
          </tr>
          <tr>
           <td> Publish Date : </td>
           <td>
           <input class ="form-input" type ="text" size = "20" id = "puplishdate" name ="puplishdate" 
           value = "<fmt:formatDate pattern ="MM/dd/yyyy" value='${book.puplishDate }' />" />
           </td>
          </tr>
             <tr>
           <td> Book Image : </td>
           <td>
           <input class ="form-input" type ="file" size = "20" id = "bookimage" name ="bookimage"  />
           <br>
           <img alt="Image Preview" id="thumbnail" height = "85" style = "width:20% ; margin-top:10px" src = "data:image/jpg;base64,${book.base64Image }">
           </td>
          </tr>
          <tr>
           <td> Price : </td>
           <td>
           <input class ="form-input" type ="text" size = "20" id = "price" name ="price" value = "${book.price }" />
           </td>
          </tr>
            <tr>
           <td> Description : </td>
           <td>
           <textarea class ="form-input"  rows = 5 cols = "50" id = "description" name ="description"   >
             
             ${book.description }
            
         </textarea>
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
	 $("#bookimage").change(function(){
		 showImageThumbnail(this) ; 
	 }) ; 
	$("#puplishdate").datepicker();
	$("#description").richText() ; 
	 $("#form").validate({
		 rules: { 
			 title : "required" , 
			 author : "required" , 
			 isbn  : "required" , 
			 puplishdate : "required" ,
			 <c:if test="${book == null}" > 
			 bookimage : "required"  , 
			 </c:if>
		
			 price : "required" , 
			 description : "required"
			 
		 } , 
		 messages: { 
			 category : "Please select a category for the book." , 
			 title :"Please enter title of the book." , 
			 author : "Please enter  author of the book." , 
			 isbn : "Please enter isbn of the book." , 
			 puplishdate : "Please enter publish date of the book." , 
			 bookimage : "Please choose an image for the book." , 
			 price : "Please enter price of the book." , 
			 description : "Please enter description of the book." 
		 }
	 }) ; 
}) ; 

  function showImageThumbnail(fileInput)  {
	  var file = fileInput.files[0] ; 
	  console.log("On Show Image") ; 
	  var reader = new FileReader() ; 
	  
	  reader.onload = function(e) { 
		  $("#thumbnail").attr("src" , e.target.result);
	  }
	  
	  reader.readAsDataURL(file) ; 
  }
  
</script>
</html>