<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order List</title>
<link href = "../css/style.css" rel = "stylesheet" >


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

		<h2>Order List Management</h2>
          <c:if test="${message != null }">
           <h3>${message }</h3>
          </c:if>
			<div style = "width : 70%">
				<form id = "order-form" action="update_cart" method = "post">
					<table id = "mytable" class = "table table-striped table-bordered"   border="1" >
					<thead  class = "thead-dark">
						<tr>
							<th>No</th>
							<th >Order Id</th>
							<th>Ordered By </th>
							<th>Book Copies</th>
							<th>Total</th>
							<th>Payment Method</th>
							<th>Status</th>
							<th>Order Date</th>
 							<th>
 							Action(s)
 							</th>

						</tr>
						</thead>
						<tbody>
						
						<c:forEach items ="${orderList}" var = "order" varStatus="no">
						 
							 <tr>
							  	<td>${no.index + 1 }</td>
							    <td>${order.orderId }</td>
							    <td>${order.customer.fullName }</td>
							    <td>${order.copiesNumber}</td>
							    <td><fmt:formatNumber type = "currency" value = " ${order.orderTotal}"/> </td>
							    <td>${order.paymentMethod }</td>
							    <td>${order.orderStatus}</td>
							    <td>${order.orderDate }</td>
							    <td>
							     <a href ="view_order?order_id=${order.orderId }">Detail</a> |
							     <a href = "edit_order?orderId=${order.orderId }">Edit</a> |
							     <a href = "javascript:void(0)" class = "delete-link" id = "${order.orderId }">Delete</a>
							    </td>
							 </tr>
						
						</c:forEach>
                       </tbody>

 					
					</table>

                    

				</form>


			</div>
		

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
	    
	    $('.delete-link').click(function(ev) { 
	    	
	    	var orderid = $(this).attr("id") ;
	    	
	    	if(confirm("Are you sure you want to delete order with id [ " + orderid + " ] ." )) { 
	    		window.location = "delete_order?orderId=" +orderid ; 
	    	}
	    });
	    
	} );
	
	</script>
	<jsp:directive.include file="footer.jsp" />
</body>

</html>