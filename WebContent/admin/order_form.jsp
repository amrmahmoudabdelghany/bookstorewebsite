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
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>


</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<form id = "update-order-form" action = "update_order" method ="post" >
	<div align="center">

		<h2>Details For Id ${order.orderId }</h2>
		
 			<div align ="center" >
 			 
 			  <table border = "0"> 
 			   <tr>
 			    <td><b>Ordered by : </b></td> 
 			    <td>${order.customer.fullName }</td>
 			   </tr>
 			    <tr>
 			    <td><b>Order Date : </b></td>
 			    <td> ${order.orderDate }</td>
 			   </tr>
 			   <tr>
 			    <td><b>Recipient Name : </b></td>
 			    <td> <input type = "text" name = "recipientName" value = "${order.recipientName }" size = "45"/> </td>
 			   </tr>
 			   <tr>
 			     <td><b>Recipient Phone : </b></td>
 			     <td><input type = "text" name = "recipientPhone" value = " ${order.recipientPhone }" size = "45"/></td>
 			   </tr>
 			   <tr>
 			    <td><b>Ship To  : </b></td>
 			    <td><input type = "text" name = "recipientAddress" value = " ${order.shipingAddress }" size = "45"/></td>
 			   </tr>
 			   <tr>
 			    <td><b>Payment Method : </b></td>
 			    <td> 
 			    <select name = "paymentMethod">
 			     <option value = "Cash On Delivery">Cash On Delivery</option>
 			    </select>
 			    </td>
 			   </tr>
 			   
 			   <tr>
 			    <td><b>Order Status : </b></td>
 			    <td> 
 			     <select name = "orderStatus" >
 			      <option value = "Processing" <c:if test = "${order.orderStatus eq 'Processing' }" >selected = "selected" </c:if> >Processing</option>
 			      <option value = "Shipping" <c:if test = "${order.orderStatus eq 'Shipping' }" >selected = "selected" </c:if>>Shipping</option>
 			      <option value = "Delivered" <c:if test = "${order.orderStatus eq 'Delivered' }" >selected = "selected" </c:if>>Delivered</option>
 			      <option value = "Completed" <c:if test = "${order.orderStatus eq 'Completed' }" >selected = "selected" </c:if>>Completed</option>
 			      <option value = "Cancelled" <c:if test = "${order.orderStatus eq 'Cancelled' }" >selected = "selected" </c:if>>Cancelled</ption>
 			     </select>
 			    </td>
 			   </tr>
 			  
 			  </table>
 			
 			</div>
			<div style = "width : 70% ; margin-top: 10px">
				 <h4> Ordered Books</h4>
					<table id = "mytable" class = "table table-striped table-bordered"   border="1" >
					<thead  class = "thead-dark">
						<tr>
							<th>Index</th>
							<th >Book Title</th>
							<th>Author</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Subtotal</th>
						    <th>Action(s)</th>
						</tr>
						</thead>
						<tbody>
						
						<c:forEach items ="${order.orderDetails}" var = "orderDetail" varStatus="no">
						 
							 <tr>
							  	<td>${no.index + 1 }</td>
							    <td>
							    <img alt="Book Image" src="data:image/jpg;base64,${orderDetail.book.base64Image }" width = "85" height = "110">
							    ${orderDetail.book.title }</td>
							    <td>${orderDetail.book.author }</td>
							    <td><fmt:formatNumber value = "${orderDetail.book.price}" type = "currency" />     </td>
	                            <td>
	                            <input type = "hidden" name = "bookPrice" value = "${orderDetail.book.price }" />
	                            <input type = "hidden" name = "bookId" value = "${orderDetail.book.bookId }" />
	                            <input type = "text" name = "quantity${no.index + 1 }" value = "${orderDetail.quantity }"/>
	                            </td>
	                            <td><fmt:formatNumber value = "${orderDetail.subTotal }" type = "currency"/>     </td>
	                            <td><a href="remove_book_from_order?bookId=${orderDetail.book.bookId }" >Remove</a> </td>
	                             
	                        </tr>
						
						</c:forEach>
						<tr>
						 <td colspan = "4" align = "right">
						  <b>Total : </b>
						 </td>
						 <td> 
						 ${order.copiesNumber }
						 </td>
						 <td colspan = "2">
						 <fmt:formatNumber value = "${order.orderTotal }" type = "currency"/>
						 
						 </td>
						</tr>
                       </tbody>

 					
					</table>

                    
           	
			


			</div>
		
    <div align = "center" style = "margin-bottom: 10px">
    
     <button type = "submit">Save</button>
     <button id = "btnAddBook">Add Book</button>
     <button id = "btnCancel">Cancel</button>
     
    </div>
	</div>
</form>
	<jsp:directive.include file="footer.jsp" />
	
	<script>
	  $(function() {
		  $("#btnAddBook").click(function(ev) {
			 ev.preventDefault() ; 
			 var width = 500 ; 
			 var height = 400; 
			 var left  = (screen.width - width ) / 2; 
			 var top = (screen.height - height) / 2; 
			 
			 window.open("add_book_form" ,"_blank","width =" + width +" ,height ="+height+", top = "+top+" , left =" + left) ; 
		  });
		  $("#btnCancel").click(function(ev){
			  ev.preventDefault() ; 
			  window.location.href = "order_list" ; 
		  });
		  
		  $("#update-order-form").validate({
				 rules: { 
					 recipientName : "required" , 
					 recipientPhone : "required" , 
					 recipientAddress  : "required" , 
					 <c:forEach items ="${order.orderDetails}" var = "orderDetail" varStatus="no">
				       quantity${no.index + 1 } : {
				         number : true  , 
				         required : true , 
				         min : 1 
				       } ,
			      </c:forEach>
					 
				 } , 
				 messages: { 
					 recipientName:"Please enter recipient name." ,
					 recipientPhone:"Please enter recipient phone.",
					 recipientAddress:"Please enter recipient address",
					 
					 <c:forEach items ="${order.orderDetails}" var = "orderDetail" varStatus="no">
			            quantity${no.index + 1 } : {
			         number :"Quantity Must be a number" , 
			         required : "Quantity is required" , 
			         min : "Quantity Must be 1 or grater"
			       } ,
		      </c:forEach>
				 }
			 }) ; 
	  });
	  
	</script>
</body>

</html>