package com.bookstore.entity;
// Generated Sep 3, 2020 10:50:24 AM by Hibernate Tools 5.2.12.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "order_detail", catalog = "bookstoredb")
@NamedQueries({
	@NamedQuery(name = "orderDetail.countByBook" , query = "SELECT COUNT(b) FROM OrderDetail b WHERE b.book.bookId = :bookId" ),
	@NamedQuery(name = "orderDetail.bestSelling" , query ="SELECT od.book FROM OrderDetail od GROUP BY od.book.bookId"
			+ " ORDER BY SUM(od.quantity) DESC")
})
public class OrderDetail implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private OrderDetailId id = new OrderDetailId();
	private Book book;
	private BookOrder bookOrder;
	private int quantity;
	private float subTotal;

	public OrderDetail() {
	}

	public OrderDetail(OrderDetailId id, Book book, BookOrder bookOrder , int quantity , int subtotal ) {
		this.id = id;
		this.book = book;
		this.bookOrder = bookOrder;
		
		this.quantity = quantity ; 
		this.subTotal = subtotal ; 
	}

   
	@EmbeddedId
	
	
	@AttributeOverrides({ 
		 @AttributeOverride(name = "orderId", column = @Column(name = "order_id")) , 
		 @AttributeOverride(name = "bookId", column = @Column(name = "book_id"))
	 })
	public OrderDetailId getId() {
		return this.id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id", nullable = false, insertable = false, updatable = false)
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
		this.id.setBook(book);
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "order_id", nullable = false, insertable = false, updatable = false)
	public BookOrder getBookOrder() {
		return this.bookOrder;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
		this.id.setBookOrder(bookOrder);
	}
	
	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "sub_total", nullable = false, precision = 12, scale = 0)
	public float getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

}
