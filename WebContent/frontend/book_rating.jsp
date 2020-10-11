 <c:forEach var = "rating" items="${book.ratingStars }">
          <c:if test="${rating eq 'on' }">
           <img alt="on" src="image/rating_on.png">
          </c:if>
          <c:if test="${rating eq 'off' }">
            <img alt="off" src ="image/rating_off.png"/>
          </c:if>
          <c:if test ="${rating eq 'half' }">
            <img alt ="half" src ="image/rating_half.png"/>
          </c:if>
        </c:forEach>