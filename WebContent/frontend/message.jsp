<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href = "css/style.css" rel ="stylesheet" >
</head>
<body>
 <jsp:directive.include file ="header.jsp" />
 
   <div align = "center">
    <h3> ${message} </h3>
    <a class = "inhA" onClick ="javascript:history.go(-1)" >Back </a>
   </div>
 <jsp:directive.include file = "footer.jsp" />
</body>
</html>