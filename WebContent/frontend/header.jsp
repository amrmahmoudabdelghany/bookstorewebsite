<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div align="center">
	<div>
		<a href="${pageContext.request.contextPath}"> <img alt="Logo"
			src="image/BookstoreLogo.png" />
		</a>
	</div>
	<div class="menuitem">
		<form method="GET" action="search">
			<input class="form-input" name="keyword" size="50" />
			<button class="form-btn" type="submit">Search</button>
		</form>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<c:if test="${loggedCustomer == null }">
			<a href="login">Sign In</a>&nbsp;|
            <a href="register">Register</a>&nbsp;|
    </c:if>
		<c:if test="${loggedCustomer != null }">
			<a href="view_profile">Welcome , ${loggedCustomer.fullName }</a>&nbsp;|
            <a href="view_orders">My Orders</a>&nbsp;| 
            <a href="logout">Logout</a>&nbsp;|
    </c:if>
		<a href="view_cart">Cart</a>
	</div>
	<div>&nbsp;</div>
	<div class="menuitem" style ="margin-bottom : 20px">

		<c:forEach var="cat" items="${categorylist }" varStatus="i">

			<font size="+1"> <a class="test"
				href="view_category?id=${cat.categoryId }"> <font size="+1">
						<c:out value="${cat.categoryName }" />
				</font>

			</a>
			</font>
			<c:if test="${not i.last}">
      &nbsp; | &nbsp;
     </c:if>
		</c:forEach>
	</div>
</div>