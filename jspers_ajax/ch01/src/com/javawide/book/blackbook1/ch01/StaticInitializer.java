package com.javawide.book.blackbook1.ch01;

class OtherStaticFields {
	public static int STATIC_VALUE;
}
public class StaticInitializer {
	public static int STATIC_VALUE;
	static {
		OtherStaticFields.STATIC_VALUE = 26;
	}
	public StaticInitializer() {
		System.out.println(STATIC_VALUE);
		System.out.println(OtherStaticFields.STATIC_VALUE);
	}
	public static void main(String[] args) {
		new StaticInitializer();
	}
}