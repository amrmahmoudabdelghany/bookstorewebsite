<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>
<!--<link href="css/style.css" rel="stylesheet">-->

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" > 
<script
			  src="https://code.jquery.com/jquery-3.5.1.min.js"
			  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
			  crossorigin="anonymous"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>

<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">

		<h2>Shopping Cart</h2>
		<c:if test="${message != null }">
			<h4 class="list-message">${message }</h4>
		</c:if>

		<c:set var="cart" value="${sessionScope['cart'] }" />

		<c:if test="${cart.totalItems eq 0 }">
			<h2>There is no items in your cart</h2>
		</c:if>

		<c:if test="${cart.totalItems > 0 }">
			<div style = "width : 60%">
				<form id = "cart-form" action="update_cart" method = "post">
					<table id = "mytable" class = "table table-striped table-bordered"   border="1" >
					<thead  class = "thead-dark">
						<tr>
							<th>No</th>
							<th >Book</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Subtotal</th>
 							<th>
 							Action(s)
 							</th>

						</tr>
						</thead>
						<tbody>
						
						<c:forEach items ="${cart.items}" var = "item" varStatus="no">
						 
							 <tr>
							  	<td>${no.index + 1 }</td>
							  	<td>
							  	<img alt="Book Image" src="data:image/jpg;base64,${item.key.base64Image }" width = "85" height = "110">
							  	 &nbsp;
							  	${item.key.title }</td>
							  	<td>
							  	 <input type = "hidden" name = "bookId" value = "${item.key.bookId }" />
							  	<input type = "text" name = "quantity${no.index + 1 }" value = "${item.value }" >
							  	 </td>
							  	<td><fmt:formatNumber value = "${item.key.price}" type = "currency"/></td>
							  	<td><fmt:formatNumber value = "${item.value * item.key.price}" type = "currency"/></td>
							  	<td><a href = "remove_from_cart?bookId=${item.key.bookId }">Remove</a> </td>
							  	
							 </tr>
						
						</c:forEach>
                       </tbody>

 						<tr>
 						  	<td></td>
 						    <td></td>
 						    <td>${cart.totalQuantity } Books</td>
 						    <td>Total : </td>
 						    <td colspan ="2" ><fmt:formatNumber value = "${cart.totalQuantityAmount }" type = "currency"/></td>
 						
 						
 						
 						</tr>

					</table>

                     <div align ="center" style = "margin-bottom : 20px">
                       <table > 
                         <tr>
                         <td><button id ="btnClear" >Clear Cart</button></td>
                          <td align = "left"><button type = "submit" >Update</button>&nbsp; &nbsp; </td>
                          
                          <td align ="right"><a href ="${pageContext.request.contextPath }">Continue Shopping</a>&nbsp; &nbsp;</td>
                          <td align = "right"><a href="checkout">Check Out</a></td>
                         </tr>
                       
                       </table>
                     </div>

				</form>


			</div>
		</c:if>

	</div>

	<script>
	$(document).ready( function () {
		
		$("#btnClear").click( function(event){
			event.preventDefault(); 
			window.location = "clear_cart" ;
		});
	    $('#mytable').DataTable({
	    	paging : true
	    });
	    $("#cart-form").validate({
	    rules :{
	      <c:forEach items ="${cart.items}" var = "item" varStatus="no">
	    	quantity${no.index + 1} : { 
	    		required : true ,
	    		number : true ,
		    	min : 1 ,
	    	} ,
	    	
	    </c:forEach>
	    	
	    } ,
	    messages :{
	    	 <c:forEach items ="${cart.items}" var = "item" varStatus="no">
		    	quantity${no.index + 1} : { 
		    		 required : "Please enter quantity" ,
		    	     number : "Quantity must be a number" , 
		    	     min : "Quantity must be grater then 0"
		    	} , 
		    	
		    </c:forEach> 
	    
	   
	    }
	    }) ; 
	} );
	
	</script>
	<jsp:directive.include file="footer.jsp" />
</body>

</html>