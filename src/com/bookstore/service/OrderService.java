package com.bookstore.service;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderService {
	private OrderDAO orderDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public OrderService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		orderDao = new OrderDAO();

	}

	
	public void listAll(String message) throws ServletException, IOException { 
		request.setAttribute("message", message);
		listAll() ;
	}
	public void listAll() throws ServletException, IOException {
		List<BookOrder> orderList = orderDao.listAll();
		System.out.println("OrderList Size : " + orderList.size());
		request.setAttribute("orderList", orderList);
		Redirector.forwardToPage(request, response, "order_list.jsp");
	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("order_id"));	
		BookOrder order = orderDao.get(orderId) ; 
		
		if(order != null) { 
		  	request.setAttribute("order", order);
		  	Redirector.forwardToPage(request, response, "order_detail.jsp");
		}else { 
			Redirector.showMessageBackend(request, response, "Could not find order with id [ " + orderId + " ] ");
		}
		
	}

	public void checkoutForm() throws ServletException, IOException {
	   
		Redirector.forwardToPage(request, response, "frontend/checkout.jsp");
		
	}

	public void placeOrder() throws ServletException, IOException {
		String recipientName = request.getParameter("recipientName") ; 
		String recipientPhone = request.getParameter("recipientPhone") ;
		String address = request.getParameter("address") ;
		String city = request.getParameter("city") ;
		String zipCode = request.getParameter("zipCode"); 
		String country = request.getParameter("country") ; 
		String paymentMethod = request.getParameter("paymentMethod") ;
		
		BookOrder order = new BookOrder() ; 
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		String shippingAddress = address + " , " + city + " , " + zipCode + " , " + country ; 
		order.setShipingAddress(shippingAddress);
		order.setPaymentMethod(paymentMethod);
		
		HttpSession session = request.getSession() ; 
		Customer customer = (Customer)session.getAttribute("loggedCustomer") ; 
		order.setCustomer(customer);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart") ; 
		Map<Book, Integer> items = cart.getItems() ; 
		
	     Iterator<Book>  ite =  items.keySet().iterator() ; 
		 Set<OrderDetail> orderDetails = new HashSet<OrderDetail>() ; 
	     while(ite.hasNext()) { 
	    	 Book book = ite.next() ; 
	    	 Integer quantity = items.get(book) ; 
	    	 float subTotal = quantity * book.getPrice() ; 
	    	 
	    	 OrderDetail detail = new OrderDetail() ;
	    	 detail.setBook(book);
	    	 detail.setQuantity(quantity);
	    	 detail.setSubTotal(subTotal);
	    	 detail.setBookOrder(order);
	    	 
	    	 orderDetails.add(detail);
	    	 
	    	 
	     }
	     order.setOrderDetails(orderDetails);
	     order.setOrderTotal((float)cart.getTotalQuantityAmount());
	     cart.clear();
	     orderDao.create(order); 
	     
	     Redirector.showMessageFrontend(request, response, "Thank you , Your order has been recived." + 
	     "We will deliver your books within few days.");
	     
		
	}

	public void listOrderByCustomer() throws ServletException, IOException {
		Customer customer  = (Customer) request.getSession().getAttribute("loggedCustomer") ; 
		response.getWriter().print("User try to access history..");
		if(customer != null) { 
			List<BookOrder> listOrders = orderDao.listByCustomer(customer.getCustomerId()) ; 
			 request.setAttribute("orderList", listOrders);
			
			Redirector.forwardToPage(request, response, "frontend/order_list.jsp");
		
		}else { 
			response.getWriter().print("Logged Customer eq Null");
		}
		
	}

	public void showOrderDetailForCustomer() throws ServletException, IOException {
		Integer bookId =Integer.parseInt( request.getParameter("orderId")) ; 
		Customer customer = (Customer)request.getSession().getAttribute("loggedCustomer") ; 
		BookOrder order = orderDao.get(bookId , customer.getCustomerId()) ; 
		if(order != null) { 
			request.setAttribute("order", order);
			Redirector.forwardToPage(request, response, "frontend/order_detail.jsp");
		}else { 
			Redirector.showMessageFrontend(request, response,
					"Sorry, you are not authorized to view this order");
		}
		
	}
	
	public void updateOrder() throws ServletException, IOException { 
		
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String recipientAddress = request.getParameter("recipientAddress");
		String paymentMethod = request.getParameter("paymentMethod") ; 
		String orderStatus  = request.getParameter("orderStatus") ; 
		String bookId[] = request.getParameterValues("bookId") ; 
		String quantities[] = new String[bookId.length] ; 
		String prices[] = request.getParameterValues("bookPrice"); 
		
		for(int i = 1 ; i <= quantities.length ; i++) { 
			quantities[i - 1] = request.getParameter("quantity" + i);
		}
		
		
		OrderDAO orderDao = new OrderDAO() ; 
		
		HttpSession session = request.getSession() ; 
		BookOrder order =(BookOrder) session.getAttribute("order");
		
		
		order.setRecipientName(recipientName.trim());
		order.setRecipientPhone(recipientPhone.trim());
		order.setShipingAddress(recipientAddress.trim());
		order.setOrderStatus(orderStatus);
		order.setPaymentMethod(paymentMethod);
		//orderDao.update(order);
		Set<OrderDetail> orderDetails = order.getOrderDetails() ; 
		orderDetails.clear();
		order.setOrderTotal(0.0f);
		for(int i = 0 ; i < bookId.length ; i++) { 
			OrderDetail orderDetail = new OrderDetail() ; 
			Book book = new Book(Integer.parseInt(bookId[i])) ; 
			book.setPrice(Float.parseFloat(prices[i]));
			orderDetail.setBook(book);
			orderDetail.setQuantity(Integer.parseInt(quantities[i]));
			float subTotal = book.getPrice() * orderDetail.getQuantity() ; 
			
			orderDetail.setSubTotal(subTotal);
			orderDetail.setBookOrder(order);
			float total = order.getOrderTotal() + subTotal ; 
			order.setOrderTotal(total);
			
			
			orderDetails.add(orderDetail);
			
		}
		orderDao.update(order);
		session.removeAttribute("order");
		String message = "Order with id [ " + order.getOrderId() + " ] has been updated successfully." ;
		listAll(message);
	}

	public void editOrder() throws IOException, ServletException {
		
		Integer orderId = Integer.parseInt(request.getParameter("orderId")) ; 
		HttpSession session = request.getSession() ; 
		if(session.getAttribute("on_add_book_to_order") == null) { 
		
		BookOrder order = orderDao.get(orderId) ; 
		if(order != null) { 
			
			session.setAttribute("order", order);
			Redirector.forwardToPage(request, response, "order_form.jsp");
		}else { 
			Redirector.showMessageBackend(request, response, "Could not find order with ID [ " + orderId + " ]");
		}
		}else { 
			
			session.removeAttribute("on_add_book_to_order");
			Redirector.forwardToPage(request, response, "order_form.jsp");
		}
		
	}


	public void deleteOrder() throws ServletException, IOException {
		
	    int orderId = Integer.parseInt(request.getParameter("orderId")) ; 
	    
	   BookOrder order =  orderDao.get(orderId)  ; 
	   String message = "" ; 
	   if(order != null) { 
		   orderDao.delete(orderId);
		   message = "Order with id [ "+ orderId + " ] has been deleted successfully." ; 
		   
	   }else { 
		   message = "Could not found order with id [ " + orderId + " ]." ; 
		 
	   }
	   listAll(message) ; 
		
	}

}
