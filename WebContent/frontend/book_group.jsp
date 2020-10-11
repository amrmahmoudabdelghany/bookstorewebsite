<div class="book">
					<div>
						<a href="view_book?id=${book.bookId }"> <img
							alt="Image Preview" id="thumbnail" class="book-small"
							src="data:image/jpg;base64,${book.base64Image }">
						</a>
					</div>
					<div>
						<a class="inhA" href="view_book?id=${book.bookId }"> <b>
								${book.title }</b>
						</a>
					</div>
					<div>
						<jsp:directive.include file="book_rating.jsp" />
					</div>
					<div>
						by <i>${book.author }</i>
					</div>
					<div>
						<b>$${book.price }</b>
					</div>
				</div>