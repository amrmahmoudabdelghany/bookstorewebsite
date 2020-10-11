<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${book.title }</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<link href="css/style.css" rel="stylesheet">

</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="center">
		<table class="book">
			<tr>
				<td colspan="3" align="left"><p id="book-title">${book.title }
					</p>by <span id="author"> ${book.author } </span></td>
			</tr>
			<tr>
				<td rowspan="2"><img alt="Image Preview" id="thumbnail"
					class="book-large" src="data:image/jpg;base64,${book.base64Image }">

				</td>
				<td valign="top" align="left">
					<div>
						<jsp:directive.include file="book_rating.jsp" />&nbsp;&nbsp;
						<a href ="#reviews">${fn:length(book.reviews) } reviews</a>
					</div>
				</td>
				<td valign="top" rowspan="2" width="20%">
					<h2>$${book.price }</h2> <br /> <br />
					<button id = "btnAddToCart">Add To Cart</button>
				</td>
			</tr>
			<tr>
				<td id="description">${book.description }</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
			<td >
			  <a id ="reviews"><h2>Customer Reviews</h2></a>
			</td>
			 <td colspan="2" align="center"><button id ="btnWriteReview">Write a
						customer review</button>
			</td>
			
			</tr>
			<tr>
				<td colspan="3" align="left">
					<table>
					<c:forEach var="review" items="${book.reviews }">
						<tr>
							<td>
									<c:forEach var="rating" items="${review.stars }">
										<c:if test="${rating eq 'on' }">
											<img alt="on" src="image/rating_on.png">
										</c:if>
										<c:if test="${rating eq 'off' }">
											<img alt="off" src="image/rating_off.png" />
										</c:if>
										
									</c:forEach>
									- <b>${review.headline }</b>
									<br>
								</td>

						</tr>
                       <tr>
                         <td> by <i>${review.customer.fullName }</i> on ${review.reviewTime }</td>
                       
                       </tr>
                       <tr><td><i>${review.comment}</i></td></tr>
                       <tr><td>&nbsp;</td></tr>
                  </c:forEach>
					</table>

				</td>
				
			</tr>


		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
	
	<script type="text/javascript">
	  $(function() { 
		  $("#btnWriteReview").on("click" , function(){
			 window.location = "write_review?id=" + ${book.bookId} ;  
		  });
		  
		  $("#btnAddToCart").on("click" , function() {
			  window.location = "add_to_cart?bookId=" + ${book.bookId} ;  
		});
	  }) ;
	</script>
</body>
</html>