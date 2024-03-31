package com.javawide.book.blackbook1.ch01;

public class HelloClassLoader extends ClassLoader {
	public HelloClassLoader() {
		super(HelloClassLoader.class.getClassLoader());
	}
	public HelloClassLoader(ClassLoader parent) {
		super(parent);
	}
	@Override
	protected synchronized Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		Class<?> foundClass = findLoadedClass(name);
		if (null == foundClass) {
			try {
				if (null != getParent()) {
					foundClass = getParent().loadClass(name);
				} else {
					foundClass = findSystemClass(name);
				}
			} catch (ClassNotFoundException e) {
				foundClass = findClass(name);
			}
		}
		return foundClass;
	}
}