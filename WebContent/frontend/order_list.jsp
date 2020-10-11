<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Order History</title>
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

		<h2>Order History </h2>
	   <c:if test="${fn:length(orderList) == 0 }">
	     <h4>You have not placed any orders</h4>
	   </c:if>
		
         <c:if test="${fn:length(orderList)  > 0}">
		<div style = "width : 70%">
			
				<table id = "mytable" class = "table table-striped table-bordered"   border="1" >
					<thead  class = "thead-dark">
						<tr>
							<th>No</th>
							<th >Order Id</th>
							<th>Quantity </th>
							<th>Total Amount</th>
							<th>Order Date</th>
							<th>Status</th>
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
							    <td>${order.copiesNumber}</td>
							    <td><fmt:formatNumber type = "currency" value = " ${order.orderTotal}"/> </td>
							  
							    <td>${order.orderDate }</td>
							    <td>${order.orderStatus}</td>
							    <td>
							     <a href ="show_order_detail?orderId=${order.orderId }">Detail</a> 
							    </td>
							 </tr>
						
						</c:forEach>
                       </tbody>

 					
					</table>

                    


			</div>
		</c:if>

	</div>


	<jsp:directive.include file="footer.jsp" />
</body>

</html>