 <%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div align="center">

  <div>
    <a href = "${pageContext.request.contextPath }/admin/">
    <img alt="Logo" src="../image/BookstoreAdminLogo.png">
    </a>
  </div>
  <div>
   Welcome , <c:out value = "${sessionScope.useremail }" />
   <a href="logout">Logout</a>
   <br><br>
  </div>
  <div>
   <div class = "menuitem">
     <a href ="user_list">
     <img alt="User Image" src="../image/users.png"><br>
     Users</a>&nbsp;
   </div>
   <div class = "menuitem" > 
    <a href ="category_list">
     <img alt="categories image" src="../image/categories.png"><br>
    Categories</a>&nbsp;
   </div>
  <div class = "menuitem" > 
     <a href ="book_list">
     <img alt="books image" src="../image/books.png"> <br>
     Books</a>&nbsp;
  </div>
  <div class = "menuitem"> 
    <a href ="list_customer">
     <img alt="customers" src="../image/customers.png"> <br>
    Customers</a>&nbsp;
  </div>
   <div class = "menuitem" > 
    
  <a href ="list_review">
   <img alt = "reviews image" src="../image/reviews.png" > <br>
  Reviews</a>&nbsp;
   </div>
  <div class = "menuitem" >
    <a href ="order_list">
  <img alt="orders image" src="../image/orders.png"> <br>
  Orders</a>
  </div>
  
   <div class = "menuitem" > 
    <a href = "list_article" > 
      <img alt="Article Preview" src="../image/article.png"> <br>
      Articles
    </a>
   </div>
  </div>
</div>