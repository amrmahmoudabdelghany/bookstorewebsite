
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookStore Administration</title>
<link href = "../css/style.css" rel="stylesheet"></link>


<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" > 

<script
			  src="https://code.jquery.com/jquery-3.5.1.min.js"
			  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
			  crossorigin="anonymous"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>
 
</head>
<body>
 <jsp:directive.include file="header.jsp" />
 <div align ="center">
  <h2>Administrative Dashboard</h2>
 </div>
 <div align="center">
    <div class = "QuickActionCont">
    <hr width = "60%">
      <h2 class = "page_header">Quick Actions </h2>
      <b>
      <a href="new_book">New Book</a>&nbsp;
      <a href="user_form.jsp">New User</a>&nbsp;
      <a href="category_form.jsp">New Category</a>&nbsp;
      <a href="customer_form.jsp">New Customer</a>&nbsp;
      </b>
    </div>
    <div>
    <hr width = "60%">
     <h2 class = "page_header">Recent Sales</h2>
       <table id = "table-1" class = "table table-striped table-bordered" style = "width:80%">
        <thead>
        <tr>
         <th>Order Id</th>
         <th>Ordered By</th>
         <th>Book Copies</th>
         <th>Total</th>
         <th>Payment Method</th>
         <th>Status</th>
         <th>Order Date</th>
         </tr>
        </thead>
        <tbody>
         <c:forEach var = "order" items = "${listRecentSales }">
         <tr>
         <td><a href = "view_order?order_id=${order.orderId }"> ${order.orderId }</a></td>
         <td>${order.customer.fullname }</td>
         <td>${order.copiesNumber }</td>
         <td><fmt:formatNumber type = "currency" value = "${order.orderTotal }" /></td>
         <td>${order.paymentMethod }</td>
         <td>${order.orderStatus }</td>
         <td>${order.orderDate }</td>
         </tr>
         </c:forEach>
        </tbody>
       </table>
    </div>
    <div>
    <hr width = "60%">
     <h2 class = "page_header">Recent Reviews</h2>
     
      <table id = "table-2" class = "table table-striped table-bordered" style = "width:80%">
        <thead>
        <tr>
         <th>Book</th>
         <th>Rating</th>
         <th>Headline</th>
         <th>Customer</th>
         <th>Review On</th>
       
         </tr>
        </thead>
        <tbody>
         <c:forEach var = "review" items = "${recentReviews }">
         <tr>
         <td>${review.book.title }</td>
         <td>${review.rating }</td>
         <td><a href = "edit_review?id=${review.reviewId }"> ${review.headline }</a></td>
         <td>${review.customer.fullname }</td>
         <td>${review.reviewTime }</td>
         
         </tr>
         </c:forEach>
        </tbody>
       </table>
     
    </div>
    <div>
     <hr width = "60%">
     <h2 class = "page_header">Statistics</h2>
      
      Total Users : ${totalUsers} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      Total Books : ${totalBooks }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      Total Customers : ${totalCustomers} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      Total Reviews : ${totalReviews} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      Total Orders : ${totalOrders }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      
     
    </div>
 </div>
 
 <jsp:directive.include file="footer.jsp" />
</body>
</html>