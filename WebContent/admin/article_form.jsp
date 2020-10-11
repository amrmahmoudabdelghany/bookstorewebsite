<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
      <%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Article</title>
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
   <c:if test="${article == null }">
 <h2 class = "page_header"> Create New Article</h2>
 </c:if>
 <c:if test = "${article != null }" >
   <h2 > Edit Article</h2>
 </c:if>
  </div>
   <div align="center">
     <c:if test="${article == null }">
      <form  id = "form" action="create_article"  method="post" >
    </c:if>
    <c:if test = "${article != null }" >
    <form id ="form" action="update_article" method="post"  >
    </c:if>
        
        <input type = "hidden" name = "articleId" value = "${article.articleId }"  />
        <table width = "80%"> 
          
          <tr >
          
          <td colspan = "1" style = "width : 7%"> Title : </td>
          <td colspan = "3"><input class = "form-input" type = "text"  size = "30" id = "title" name = "title" value  ="${article.articleTitle}" /></td>
          </tr>
          
            <tr>
        
           <td colspan = "4">
           <textarea class ="form-input"  col = "50" rows = "5"  id = "content" name ="content" >
             ${article.articleContent} 
           </textarea>
           
           </td>
          </tr>
        
           <tr>
           <td>&nbsp;&nbsp;</td>
           </tr>
          <tr>
          <td  align = "center" colspan= "2">
          <button id = "btnSave" class = "form-btn" type ="submit">Save</button>
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
	
	
	 $("#content").richText() ; 
	 
	
	 $("#form").validate({
		 rules: { 
			 title : "required" , 
			 content : { 
				 required : true 
			 }
			 
		 } , 
		 messages: { 
			 title :"Please enter title of the article." , 
			 content :{
				 required : "Please enter content for article"
			 }
			
		 }
	 }) ; 
	 
	$("#btnSave").click(function(ev) {
		var textArea = $(".richText-editor") ; 
		var len = textArea.text().trim().length; 
	    
		if(len <= 0) {
		   message  = "Please enter content for article" ; 	
		   var errorLbl =  $("#error-content");
		   if(!errorLbl.length){
			textArea.after("<label id = 'error-content' class = 'error' for ='content'>" + message + "</label>") ;
		   }else {
			   errorLbl.text(message) ; 
		   }
			ev.preventDefault() ; 
		}
		else if(len > 500) { 
			   message  = "Content Must be less than 500 character." ; 	
				
			   var errorLbl =  $("#error-content");
			   if(!errorLbl.length){
				textArea.after("<label id = 'error-content' class = 'error' for ='content'>" + message + "</label>") ;
			   }else {
				   errorLbl.text(message) ; 
			   }
				ev.preventDefault() ; 
		}
		
		return true ; 
	}) ;
	
}) ; 


  
</script>
</html>