package com.javawide.book.blackbook1.ch01;

public class HelloPathClassLoaderTarget implements IHelloClass {
	public HelloPathClassLoaderTarget() {}
	@Override
	public String getClassLoaderName() {
		return getClass().getClassLoader().toString();
	}
}
