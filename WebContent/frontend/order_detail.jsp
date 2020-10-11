<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Detail</title>
<link href = "../css/style.css" rel = "stylesheet" >


<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" > 
<script
			  src="https://code.jquery.com/jquery-3.5.1.min.js"
			  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
			  crossorigin="anonymous"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>


</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">

		<h2>Details For Id ${order.orderId }</h2>
		
 			<div align ="center" >
 			
 			  <table border = "1"> 
 			   <tr>
 			    <td><b>Order Status : </b></td>
 			    <td> ${order.orderStatus }</td>
 			   </tr>
 			   <tr>
 			    <td><b>Order Date : </b></td>
 			    <td> ${order.orderDate }</td>
 			   </tr>
 			   <tr>
 			    <td><b>Quantity : </b></td>
 			    <td> ${order.copiesNumber }</td>
 			   </tr>
 			   <tr>
 			    <td><b>Total Amount : </b></td>
 			    <td> <fmt:formatNumber value ="${order.orderTotal }" type = "currency" />   </td>
 			   </tr>
 			   <tr>
 			    <td><b>Recipient Name : </b></td>
 			    <td> ${order.recipientName }</td>
 			   </tr>
 			    <tr>
 			    <td><b>Ship To : </b></td>
 			    <td> ${order.shipingAddress }</td>
 			   </tr>
 			  
 			   <tr>
 			    <td><b>Payment Method : </b></td>
 			    <td> ${order.paymentMethod }</td>
 			   </tr>
 			  
 			   
 			  </table>
 			
 			</div>
			<div style = "width : 70% ; margin-top: 10px">
				 <h4> Ordered Books</h4>
					<table id = "mytable" class = "table table-striped table-bordered"   border="1" >
					<thead  class = "thead-dark">
						<tr>
							<th>Index</th>
							<th >Book</th>
							<th>Author</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Subtotal</th>
						
 							

						</tr>
						</thead>
						<tbody>
						
						<c:forEach items ="${order.orderDetails}" var = "orderDetail" varStatus="no">
						 
							 <tr>
							  	<td>${no.index + 1 }</td>
							    <td>
							    <img alt="Book Image" src="data:image/jpg;base64,${orderDetail.book.base64Image }" width = "85" height = "110">
							    ${orderDetail.book.title }
							    </td>
							    <td>${orderDetail.book.author }</td>
							    <td><fmt:formatNumber value = "${orderDetail.book.price}" type = "currency" />     </td>
	                            <td>${orderDetail.quantity }</td>
	                            <td><fmt:formatNumber value = "${orderDetail.subTotal }" type = "currency"/>     </td>
	                           
	                             
	                        </tr>
						
						</c:forEach>
						<tr>
						 <td colspan = "4" align = "right">
						  <b>Total : </b>
						 </td>
						 <td> 
						 ${order.copiesNumber }
						 </td>
						 <td>
						 ${order.orderTotal }
						 </td>
						</tr>
                       </tbody>

 					
					</table>

                    
           	
			


			</div>
		
 
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>

</html>