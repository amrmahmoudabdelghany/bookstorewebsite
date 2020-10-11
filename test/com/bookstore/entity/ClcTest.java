package com.bookstore.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClcTest {
 
	@Test
	void testAdd() {
     int a , b ; 
     Clc clc = new Clc() ; 
     a = 1 ; 
     b = 2 ; 
     int e = 3 ; 
     assertEquals(e , clc.add(a, b) );
	}

}
