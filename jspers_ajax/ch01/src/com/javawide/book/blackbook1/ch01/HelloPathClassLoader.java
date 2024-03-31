package com.javawide.book.blackbook1.ch01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloPathClassLoader extends ClassLoader {
	public HelloPathClassLoader() {
		System.out.println(getClass().getClassLoader());
	}
	public HelloPathClassLoader(ClassLoader parent) {
		super(parent);
	}
	public IHelloClass helloLoadClass(String className) {
		IHelloClass newObject = null;
		Class<?> foundClass = null;
		try {
			foundClass = findClass(className);
			newObject = (IHelloClass)foundClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return newObject;
	}
	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
		Class<?> foundClass = null;
		String path = String.format("%s/bin/%s.class",
			new Object[] {System.getProperty("user.dir"),
			className.replace('.', '/')});
		byte[] classBytes = readClassBytes(path);
		if(null == classBytes) {
			super.findClass(className);
		} else {
			foundClass = defineClass(className, classBytes, 0, classBytes.length);
		}
		return foundClass;
	}
	
	private byte[] readClassBytes(String path) {
		FileInputStream fis = null;
		byte[] classBytes = null;
		try {
			fis = new FileInputStream(path);
			classBytes = new byte[fis.available()];
			fis.read(classBytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != fis) {
					fis.close();
					fis = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return classBytes;
	}
	public static void main(String[] args) {
		HelloPathClassLoader loader = new HelloPathClassLoader();
		IHelloClass helloObj = loader.helloLoadClass("com.javawide.book.blackbook1.ch01.HelloPathClassLoaderTarget");
		System.out.println(helloObj.getClassLoaderName());
	}
}