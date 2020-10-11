<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookStore Website</title>
<link href="css/style.css" rel="stylesheet">

</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div class="center">

		<div>
			<h3>New Books</h3>
			<c:forEach var="book" items="${listNewBooks}" varStatus="i">
				<jsp:directive.include file = "book_group.jsp" />
			</c:forEach>

		</div>



		<div class="next-row">
			<h3>Best Selling Books</h3>
              <c:forEach var="book" items="${listBestSellingBooks}" varStatus="i">
				<jsp:directive.include file = "book_group.jsp" />
			</c:forEach>

              
		</div>

		<div class="next-row">
			<h3>Most Favorite Books</h3>
			<c:forEach var="book" items="${listMostFavoritBooks}" varStatus="i">
				<jsp:directive.include file = "book_group.jsp" />
			</c:forEach>

              
		</div>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>