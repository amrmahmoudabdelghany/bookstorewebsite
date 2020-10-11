<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page Not Found</title>
</head>
<body>
   
<div align="center" > 
   <div>
    <img alt="LOGO" src="${pageContext.request.contextPath}/image/BookstoreLogo.png">
   </div>
   <div>
    <h2>Sorry ,The Server has encountered an error while fulfilling your request </h2>
    <h3>Please check back later or contact out administrators</h3>
   </div>
    <div>
     <a href ="javascript:history.go(-1)">Go Back</a>
    </div>
</div>

</body>
</html>