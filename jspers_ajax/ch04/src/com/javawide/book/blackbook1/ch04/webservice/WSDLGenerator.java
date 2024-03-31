package com.javawide.book.blackbook1.ch04.webservice;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Vector;

public class WSDLGenerator {
	private String className;
	private int level;
	public WSDLGenerator(String className) {
		this.className = className;
	}
	public Collection<Proxy> getWebMethod() {
		Collection<Proxy> v = new Vector<Proxy>();
		Class<?> c = loadClass();
		Object invokeTarget = newInovokeTarget(c);
		if(null == c) return v;
		for(Method m : c.getMethods()) {
			for(Annotation a : m.getAnnotations()) {
				if(isAllowed(a)) v.add(new Proxy(invokeTarget, m));
			}
		}
		return v;
	}
	private boolean isAllowed(Annotation a) {
		Integer methodLevel = 0;
		try {
			methodLevel = (Integer)a.getClass().getMethod("level", null).invoke(a, null);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return a instanceof WebMethod && methodLevel <= level;
	}
	private Class<?> loadClass() {
		Class<?> c = null;
		try {
			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}
	private Object newInovokeTarget(Class<?> c) {
		try {
			return c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setLevel(String aLevel) {
		this.level = (null == aLevel) ? 1 : Integer.parseInt(aLevel);
	}
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(100)
			.append("<webmethods>");
		for(Proxy p : getWebMethod()) {
			buffer.append(p).append(System.getProperty("line.separator"));
		}
		return buffer.append("</webmethods>").toString();
	}
	public Object invoke(String m, Object[] args) {
		System.out.println(args[0].getClass().getName());
		System.out.println(m);
		for(Proxy p : getWebMethod()) {
			if(m.equals(p.method.getName())) {
				System.out.println(p.method.getName());
				return p.invoke(args);
			}
		}
		return new Object();
	}
	public static void main(String[] args) {
		WSDLGenerator gen = new WSDLGenerator("com.javawide.book.blackbook1.ch04.webservice.GreetService");
		gen.setLevel(2);
		System.out.println(gen);
		System.out.println(gen.invoke("add", new Object[] { 1, 2 }));
	}
}
