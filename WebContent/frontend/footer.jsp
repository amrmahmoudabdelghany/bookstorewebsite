<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div align = "center" style ="clear :both">
 <h4> Copyright (c) 2020 by Evergreen Books Co.,Ltd</h4> 
 <c:forEach var = "article" items = "${articleList }" varStatus = "i">
   <a href = "article_details?id=${article.articleId }" >${article.articleTitle }</a>
   <c:if test="${!i.last }">|</c:if> 
 </c:forEach>
</div>