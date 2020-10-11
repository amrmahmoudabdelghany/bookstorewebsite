<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Book Form</title>
</head>
<body>
	<div align="center">
	
	  <h2>Add Book To Order ID : ${order.orderId }</h2>
	  <form action = "add_book_to_order" method = "post">
	  <table> 
     	<tr>
     	<td>Select a book</td>
     	<td>	  
		<select name="bookId">
			<c:forEach var="book" items="${bookList }">
				<option value ="${book.bookId }">${book.title }</option>
			</c:forEach>
		</select>
	  </td>
	  </tr>
	  <tr>
	  <td>Number of copies : </td>
	  <td>
	  <select name = "quantity" >
	   <option value = "1">1</option>
	   <option value = "2">2</option>
	   <option value = "3">3</option>
	   <option value = "4">4</option>
	   <option value = "5">5</option>
	   
	  </select>
	  </td>
	  </tr>
	  <tr><td>&nbsp;&nbsp;</td></tr>
	  <tr>
	   <td align="center"> 
	   <button>Save</button>
	   <button onclick = "javascript:self.close()">Cancel</button>
	   </td>
	  </tr>
	  </table>
	 </form>
	</div>
	
</body>
</html>