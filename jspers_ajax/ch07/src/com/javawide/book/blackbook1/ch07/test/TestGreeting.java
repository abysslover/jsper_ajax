package com.javawide.book.blackbook1.ch07.test;

import com.javawide.book.blackbook1.ch07.Greeting;

import junit.framework.TestCase;

public class TestGreeting extends TestCase {
	Greeting greeting;
	protected void setUp() throws Exception {
		greeting = new Greeting();
		greeting.setName("'y'erMagne");
	}
	public void testDecorateName() throws Exception {
		assertEquals("Hello <y<erM", greeting.decorateName());
		greeting.setName("''''''''''");
		assertEquals("Hello <<<<<<", greeting.decorateName());
		greeting.setName("'y'ArMagnA");
		assertEquals("Hello <y<ArM", greeting.decorateName());
		greeting.setName("'BerMagne");
		assertEquals("Hello <BerMa", greeting.decorateName());
		greeting.setName("'");
		assertEquals("Hello <", greeting.decorateName());
		greeting.setName(null);
		assertEquals("Hello Guest", greeting.decorateName());
	}
	
	public void testToString() throws Exception {
		assertEquals(greeting.decorateName(), greeting.toString());
	}
	
	public void testNullName() throws Exception {
		greeting.setName(null);
		assertEquals("Hello Guest", greeting.decorateName());
	}
}
