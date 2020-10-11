<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result for ${keyword }</title>
<link href = "css/style.css" rel = "stylesheet">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="center">
		<c:if test="${fn:length(search_result) == 0}">
			<h2>No Result for ${keyword }</h2>
		</c:if>
		<c:if test="${fn:length(search_result) > 0}">

			<div class = "book-group">
			     <h3>Search Result for ${keyword }</h3>
				<c:forEach var="book" items="${search_result}" varStatus="i">
				<div>
					<div id="search-image">
						<div >
							<a href="view_book?id=${book.bookId }"> <img
								alt="Image Preview" id="thumbnail"
								class = "book-small"
								src="data:image/jpg;base64,${book.base64Image }">
							</a>
						</div>
					</div>


					<div
						id = "search-description">
						<div>
						 <h2>	<a class="inhA" href="view_book?id=${book.bookId }"> <b>
									${book.title }</b>
							</a></h2>
						</div>
						<div>
						 <jsp:directive.include file="book_rating.jsp" />
						</div>
						<div>
							by <i>${book.author }</i>
						</div>
						<div>${fn:substring(book.description , 0 , 100) }</div>

					</div>
					<div id ="search-price">
					
						<h3><b>$${book.price }</b></h3>
						<h3> <a href ="add_to_cart?bookId=${book.bookId }" class = "inhA">Add To Cart </a></h3>
					</div>
                  </div>
				</c:forEach>
			</div>

		</c:if>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>