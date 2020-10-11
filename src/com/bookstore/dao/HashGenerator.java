package com.bookstore.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class HashGenerator {
  
	
	public static String  md5(String str) { 
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		md.update(str.getBytes()); 
		byte [] dig = md.digest() ; 
		String hex = DatatypeConverter.printHexBinary(dig) ;
		
		return hex ; 
	}
	
	
}
