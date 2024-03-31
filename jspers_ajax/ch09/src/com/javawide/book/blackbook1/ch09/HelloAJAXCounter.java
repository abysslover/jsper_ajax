package com.javawide.book.blackbook1.ch09;

import com.javawide.book.blackbook1.ch07.HelloCounter;

public class HelloAJAXCounter extends HelloCounter {
	public static void main(String[] args) {
		HelloAJAXCounter counter = new HelloAJAXCounter();
		System.out.println(counter.getClass().getClassLoader());
	}
}
