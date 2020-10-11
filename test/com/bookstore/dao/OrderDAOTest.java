package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

class OrderDAOTest {
  private static OrderDAO orderDao ; 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		orderDao = new OrderDAO() ; 
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		orderDao.close();
	}
    
	@Test 
	void testListByCustomer() { 
		List<BookOrder> listByCustomer = orderDao.listByCustomer(197) ; 
		assertEquals(9, listByCustomer.size());
	}
	@Test 
	void testListByCustomerEmpty() { 
		List<BookOrder> listByCustomer = orderDao.listByCustomer(1) ; 
		assertEquals(0, listByCustomer.size());
	}
	
	@Test
	void testCreateBookOrder() {
		BookOrder order = new BookOrder() ; 
		order.setCustomer(new Customer(197));
		order.setShipingAddress("Alexandria - El Agamy - El bitash ");
		order.setRecipientName("Amr Mahmoud Abd Elghany");
		order.setRecipientPhone("01283612465");
		order.setPaymentMethod("Cash");
		order.setOrderTotal(100.0f);
		order.setOrderStatus("Shipping");
		
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>() ; 
		OrderDetail orderDetail = new OrderDetail() ; 
		orderDetail.setBook(new Book(17));
		orderDetail.setQuantity(2);
		orderDetail.setSubTotal(60f);
		orderDetail.setBookOrder(order);
		
		orderDetails.add(orderDetail) ; 
 		order.setOrderDetails(orderDetails);
		
		BookOrder res = orderDao.create(order); 
		
		assertTrue(res.getOrderId() > 0) ; 
	}
    
	@Test
	void testCreateBookOrder2() {
		BookOrder order = new BookOrder() ; 
		order.setCustomer(new Customer(196));
		order.setShipingAddress("Alexandria - El Agamy - El bitash ");
		order.setRecipientName("Amr Mahmoud Abd Elghany");
		order.setRecipientPhone("01283612465");
		order.setPaymentMethod("Cash");
		order.setOrderTotal(170f);
		order.setOrderStatus("Shipping");
		
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>() ; 
		OrderDetail orderDetail = new OrderDetail() ; 
		orderDetail.setBook(new Book(19));
		orderDetail.setQuantity(2);
		orderDetail.setSubTotal(60f);
		orderDetail.setBookOrder(order);
		
		orderDetails.add(orderDetail) ; 
		
		OrderDetail orderDetail2 = new OrderDetail() ; 
		orderDetail2.setBook(new Book(15));
		orderDetail2.setQuantity(1);
		orderDetail2.setSubTotal(50.0f);
		orderDetail2.setBookOrder(order);
		
		orderDetails.add(orderDetail2) ; 
 		order.setOrderDetails(orderDetails);
		
		BookOrder res = orderDao.create(order); 
		
		assertTrue(res.getOrderId() > 0 && res.getOrderDetails().size() == 2) ; 
	}
    @Test
    void countBookByOrderDetailFail() { 
    	int bookId = 10 ; 
    	long exp = 2 ; 
    	long act = orderDao.countDetailByBook(bookId) ; 
    	assertNotEquals(exp, act);
    }
    
    @Test
    void countBookByOrderDetail() { 
    	int bookId = 14 ; 
    	long exp = 2 ; 
    	long act = orderDao.countDetailByBook(bookId) ; 
    	assertEquals(exp, act);
    }
    
    @Test
    void countOrderByCustomer() { 
    	
    	int customerId = 196 ; 
    	
    	long act = orderDao.countOrderByCustomer(customerId) ; 
    	long exp = 1 ; 
    	assertEquals(exp, act);
    	
    }
	@Test
	void testUpdateBookOrder() {
		BookOrder order = orderDao.get(1) ; 
		
		order.setRecipientName("Mahmoud Abd Elghany");
		order.setRecipientPhone("01003447881");
		BookOrder res = orderDao.update(order); 
		assertEquals( "Mahmoud Abd Elghany" , res.getRecipientName() );
		assertEquals("01003447881", order.getRecipientPhone());
		assertEquals(1, res.getOrderId());
	}

	@Test
	void testGet() {
		BookOrder order = orderDao.get(22) ; 
		assertNotNull(order);
		assertTrue(order.getOrderDetails().size() == 2);
		
	}

	@Test
	void testDeleteObject() {
		orderDao.delete(1);
		assertNull(orderDao.get(1));
	}

	@Test 
	void testCreateArbitaryList() { 
		for(int i = 0 ; i < 5 ; i++) { 
			BookOrder order = new BookOrder() ; 
			order.setCustomer(new Customer(197));
			order.setShipingAddress("Alexandria - El Agamy - El bitash ");
			order.setRecipientName("Amr Mahmoud Abd Elghany");
			order.setRecipientPhone("01283612465");
			order.setPaymentMethod("Cash");
			order.setOrderTotal(100.0f);
			order.setOrderStatus("Shipping");
			
			BookOrder res = orderDao.create(order); 
			
			assertTrue(res.getOrderId() > 0) ; 
		}
	}
	

	@Test
	void testListAll() {
		List<BookOrder> orders =orderDao.listAll() ; 
		assertEquals(1, orders.size());
		
		for(BookOrder order : orders) { 
			System.out.println("Order Id : " + order.getOrderId()) ; 
			System.out.println("Order Customer : " + order.getCustomer().getFirstname()) ; 
			for(OrderDetail orderDetail : order.getOrderDetails()) { 
				System.out.println("Book : " + orderDetail.getBook().getTitle()) ; 
				System.out.println("Quantity : " + orderDetail.getQuantity()) ; 
				System.out.println("SubTotal : " + orderDetail.getSubTotal()) ; 
				System.out.println("---------------------------"); 
			}
		}
		
		
	}
	
	@Test
	void testUpdateOrderDetail() { 
		Integer orderID = 22 ; 
	    BookOrder order = orderDao.get(orderID) ; 
	    Set<OrderDetail> details = order.getOrderDetails() ; 
	    
	    for(OrderDetail detail : details) { 
	    	if(detail.getBook().getBookId() == 18 ) { 
	    	detail.setQuantity(3);
	    	detail.setSubTotal(60f);
	    	}
	    }
	    orderDao.update(order); 
	    
	    int expQuantity = 3 ; 
	    float expTotal = 60 ; 
	    int actQuantity  = 0; 
	    float actTotal  = 0; 
	    
	    BookOrder updated  = orderDao.get(orderID) ; 
	    details = updated.getOrderDetails() ; 
	    for(OrderDetail detail : details) { 
	    	if(detail.getBook().getBookId() == 18 ) { 
	    	    actQuantity = detail.getQuantity(); 
	    	    actTotal = detail.getSubTotal() ; 
	    	    
	    	}
	    }
	    assertEquals(expQuantity , actQuantity) ; 
	    assertEquals(expTotal , actTotal);
	    
	    
	    
 	}

	@Test
	void testCount() {
		 long res = orderDao.count() ; 
	
		assertEquals(5, res);
	}
  @Test 
  void testGetByIdAndCustomerNull() { 
	 int customerId = 1 ; 
	 int orderId = 2 ; 
	 
	 BookOrder order = orderDao.get(orderId , customerId) ; 
	 
	 assertNull(order);
	 
  }
  @Test 
  void testGetByIdAndCustomer() { 
	 int customerId = 196 ; 
	 int orderId = 23 ; 
	 
	 BookOrder order = orderDao.get(orderId , customerId) ; 
	 
	 assertNotNull(order);
	 
  }
  
  
  @Test
  void testlistRecentSales() { 
	  List<BookOrder> orders = orderDao.listRecentSales() ; 
	  
	  assertEquals(3, orders.size());
  }
}
