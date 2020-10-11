<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register as a  Customer</title>


<link href="css/jquery-ui.min.css" rel="stylesheet">

<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

</head>

<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		
	 <h2 class="page_header">Register as a Customer</h2>
		
	</div>
	<div class = "center">
		
		
	  <form id="customer-form" action="register_customer" method="post">
          
          <jsp:directive.include file = "../common/customer_form.jsp" /> 		
    
	</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>

<script src="../js/customer-form.js"></script>
</html>