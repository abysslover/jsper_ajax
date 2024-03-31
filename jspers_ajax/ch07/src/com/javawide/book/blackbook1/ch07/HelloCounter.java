package com.javawide.book.blackbook1.ch07;

import java.math.BigDecimal;

public class HelloCounter {
	private BigDecimal counts = BigDecimal.ZERO;
	public String toString() {
		increase();
		return counts.toString();
	}
	private void increase() {
		counts = counts.add(BigDecimal.ONE);
	}
	public static void main(String[] args) {
		HelloCounter counter = new HelloCounter();
		System.out.println(counter);
	}
}
