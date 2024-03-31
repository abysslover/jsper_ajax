package com.javawide.book.blackbook1.ch04.webservice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Vector;

public class Proxy {
	public Object invokeTarget;
	public Method method;
	public Proxy(Object invokeTarget, Method method) {
		this.invokeTarget = invokeTarget;
		this.method = method;
	}
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(100)
			.append(String.format("<method name=\"%s\">", method.getName()))
			.append(String.format("<returnType>%s</returnType>", method.getReturnType().getName()))
			.append(getMethodParameters())
			.append("</method>");
		return buffer.toString();
	}
	private String getMethodParameters() {
		StringBuffer buffer = new StringBuffer(100);
		int paramCount = 0;
		String paramName = null;
		for(String pTypes : getParameterTypes()) {
			paramName = "param" + paramCount++;
			buffer.append(String.format("<%s>%s</%s>", paramName, pTypes, paramName));
		}
		return buffer.toString();
	}
	public Collection<String> getParameterTypes() {
		Collection<String> v = new Vector<String>();
		Class<?>[] types = method.getParameterTypes();
		for(Class<?> c : types) {
			v.add(c.getName());
		}
		return v;
	}
	public Object invoke(Object[] args) {
		Object result = new Object();
		try {
			result = method.invoke(invokeTarget, args);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}
}
