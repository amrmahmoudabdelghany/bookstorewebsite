
package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	   @NamedQuery(name = "user.findAll" ,  query = "SELECT u FROM Users u ORDER BY  u.fullName") , 
	   @NamedQuery(name = "user.countAll" , query = "SELECT Count(u) FROM Users u " )  , 
       @NamedQuery(name = "user.findByEmail" , query = "SELECT u FROM Users u WHERE u.email = :email") ,
	   @NamedQuery(name = "user.checkLogin" , query = "SELECT u FROM Users u WHERE u.email = :email AND u.password = :password ")
})

public class Users {
	private Integer userId;
	private String email;
	private String fullName;
	private String password;

	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    @Column
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    @Column(name = "full_name")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
    @Column
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() { 
		String res = "ID : " + String.valueOf(this.userId) + "\n" + 
	            "Full Name : " + this.fullName + "\n" + 
				"Email : " + this.email +  "\n" + 
				"Password : " + this.password ; 
		 
		
		return res ; 
		
	}
}
