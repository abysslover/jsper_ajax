package com.javawide.book.blackbook1.ch04.webservice;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class ParameterSelector {
	private HttpServletRequest request;

	public ParameterSelector(HttpServletRequest request) {
		this.request = request;
	}
	public String getMethod() {
		return request.getParameter("method");
	}
	public Vector<Object> getParameters() {
		Vector<Object> args = new Vector<Object>();
		int paramCount = 0;
		Object paramValue;
		while(true) {
			paramValue = getRealTypedObject(request.getParameter("param" + paramCount++));
			if(null == paramValue) break;
			args.add(paramValue);
		}
		return args;
	}
	private Object getRealTypedObject(String paramValue) {
		if(null == paramValue) return null;
		Pattern pattern = Pattern.compile("\\d+");
		Matcher mat = pattern.matcher(paramValue);
		if(mat.find()) {
			System.out.println(mat.group(0));
			return Integer.parseInt(mat.group(0));
		}
		return paramValue;
	}
}