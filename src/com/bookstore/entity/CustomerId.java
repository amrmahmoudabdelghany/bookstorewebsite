package com.bookstore.entity;
// Generated Sep 3, 2020 9:11:41 AM by Hibernate Tools 5.2.12.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CustomerId generated by hbm2java
 */
@Embeddable
public class CustomerId implements java.io.Serializable {

	private int customerId;
	private String email;

	public CustomerId() {
	}

	public CustomerId(int customerId, String email) {
		this.customerId = customerId;
		this.email = email;
	}

	@Column(name = "customer_id", nullable = false)
	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Column(name = "email", nullable = false, length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CustomerId))
			return false;
		CustomerId castOther = (CustomerId) other;

		return (this.getCustomerId() == castOther.getCustomerId())
				&& ((this.getEmail() == castOther.getEmail()) || (this.getEmail() != null
						&& castOther.getEmail() != null && this.getEmail().equals(castOther.getEmail())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCustomerId();
		result = 37 * result + (getEmail() == null ? 0 : this.getEmail().hashCode());
		return result;
	}

}
