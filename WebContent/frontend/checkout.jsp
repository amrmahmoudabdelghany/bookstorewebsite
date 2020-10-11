<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout - Online Book Store</title>
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

		<h2>Checkout </h2>
		<c:if test="${message != null }">
			<h4 class="list-message">${message }</h4>
		</c:if>

		<c:set var="cart" value="${sessionScope['cart'] }" />

		<c:if test="${cart.totalItems eq 0 }">
			<h2>There is no items in your cart</h2>
		</c:if>

		<c:if test="${cart.totalItems > 0 }">
			<div style = "width : 60%">
				
					<table id = "mytable" class = "table table-striped table-bordered"   border="1" >
					<thead  class = "thead-dark">
						<tr>
							<th>No</th>
							<th >Book</th>
							<th>Author</th>
							<th>Price</th>
							<th>Quantity</th>
 							<th>SubTortal</th>
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
							  	  ${item.key.author }
							  	 </td>
							  	<td><fmt:formatNumber value = "${item.key.price}" type = "currency"/></td>
							  	<td> <input type = "text" name = "quantity${no.index + 1 }" value = "${item.value }"  size = "5" readonly = "readonly"></td>
							  	<td><fmt:formatNumber value = "${item.value * item.key.price}" type = "currency"/></td>
							  	
							 </tr>
						
						</c:forEach>
                       </tbody>

 						<tr>
 						  	<td></td>
 						    <td></td>
 						    <td>${cart.totalQuantity } Books</td>
 						    <td colspan = "2" align = "right">Total : </td>
 						    <td colspan ="2" ><fmt:formatNumber value = "${cart.totalQuantityAmount }" type = "currency"/></td>
 						
 						
 						
 						</tr>

					</table>

				


			</div>
			<div>
			  <h2>Your Shipping Information</h2>
	  <form id="order-form" action="place_order" method="post">
		

		
		<table class = "form">

			<tr>
				<td align = "right">Recipient Name :  </td>
				<td align = "left"><input  name = "recipientName"  id="recipientName" value = "${loggedCustomer.fullName }" size = "45"/>
					</td>
				
			</tr>
			
			<tr>
				<td align = "right">Recipient Phone :</td>
				<td align = "left"><input class="form-input" type="text" size="45"
					id="phoneNumber" name="recipientPhone" value = "${loggedCustomer.phoneNumper }"
					 /></td>
			</tr>
				<tr>
				<td align = "right">Street Address :</td>
				<td align = "left"><input class="form-input" type="text" size="45"
					id="address" name="address" value = "${loggedCustomer.address }" /></td>
			</tr>
			<tr>
				<td align = "right">City :</td>
				<td align = "left"><input class="form-input" type="text" size="45" id="city"
					name="city" value = "${loggedCustomer.city }" /></td>
			</tr>
			
			<tr>
				<td align = "right">Zip Code :</td>
				<td align = "left"><input class="form-input" type="text" size="45" id="zipCode"
					name="zipCode" value = "${loggedCustomer.zipCode }" /></td>
			</tr>
           
			 <tr>
				<td align = "right">Country :</td>
				<td align ="left"><input class="form-input" type="text" size="45" id="country"
					name="country" value = "${loggedCustomer.country }" /></td>
			</tr>
			
		
			
			<tr>
				<td>&nbsp;&nbsp;</td>
			</tr>
			
		</table>
		 <div align = "center"> 
		  <h3>Payment</h3>
		  Choose Payment Method : &nbsp;&nbsp;&nbsp;
		  <select name = "paymentMethod">
		   <option>Cash On Delivery</option>
		    
		  </select>
		  <br> <br>
		 </div>
		             <div align ="center" style = "margin-bottom : 20px">
                       <table > 
                         <tr>
                          <td align = "left"><button type = "submit" >Place Order</button>&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;</td>
                          <td align ="right"><a href ="${pageContext.request.contextPath }">Continue Shopping</a></td>
                         </tr>
                       </table>
                     </div>
		</form>
			
			</div>
			
                    
			
		</c:if>

	</div>

	<script>
	$(document).ready( function () {
		
	    $('#mytable').DataTable({
	    	paging : true
	    });
	    $("#order-form").validate({
	    rules :{
	    	recipientName : "required" , 
	    	recipientPhone : "required", 
	    	address :"required" , 
	    	city : "required",
	    	zipCode : "required", 
	    	country : "required"
	    	
	    } ,
	    messages :{
	    	 
	      	recipientName : "Please Enter Recipient Name "  , 
	    	recipientPhone :"Please Enter Recipient Phone " , 
	    	address : "Please Enter Recipient Address ", 
	    	city : "Please Enter Recipient City ", 
	    	zipCode : "Please Enter Zip Code ", 
	    	country : "Please Enter Country "
	    
	   
	    }
	    }) ; 
	} );
	
	</script>
	<jsp:directive.include file="footer.jsp" />
</body>

</html>