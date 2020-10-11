<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link href = "../css/style.css" rel = "stylesheet"> 

<title>Article Details</title>
  <style >
    #content { 
      width : 300px  ;
       hieght : 200px ; 
       border : 1px solid black ;
        padding : 5px
    }
  </style>
</head>
<body>
<jsp:directive.include file="header.jsp" />
 
 <div align = "center" >
 
    <h2>Details For Id : ${article.articleId }</h2>
    <h2>Article title : ${article.articleTitle }</h2>
     <div  id = "content" style ="width : 60%" style = "">
        <div contenteditable ="false" >
      ${article.articleContent }
    </div>
     </div>
    <br>
    <br>
    <button onclick="javascript:history.go(-1)">Back</button>

 </div>
 
 
 
 <jsp:directive.include file="footer.jsp" />
 
 
</body>

</html>