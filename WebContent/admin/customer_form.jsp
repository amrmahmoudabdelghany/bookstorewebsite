<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Customer</title>


<link href="../css/jquery-ui.min.css" rel="stylesheet">

<link href="../css/style.css" rel="stylesheet">
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

</head>

<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<c:if test="${customer == null }">
			<h2 class="page_header">Create New Customer</h2>
		</c:if>
		<c:if test="${customer != null }">
			<h2>Edit Customer</h2>
		</c:if>
	</div>
	<div class = "center" style = "margin-left : auto ; padding-left : auto ; float : none">

		<c:if test="${customer == null }">
			<form id="customer-form" action="create_customer" method="post">
		</c:if>
		<c:if test="${customer != null }">
			<form id="customer-form" action="update_customer" method="post">
		</c:if>

		<input type="hidden" name="customerId" value="${customer.customerId }" />
            <jsp:directive.include file="../common/customer_form.jsp" />
		</form>
	
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>

<script src="../js/customer-form.js" type="text/javascript">

</script>
</html>