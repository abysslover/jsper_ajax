package com.javawide.book.blackbook1.ch10;

import org.json.*;

public class HelloJSON {
	private JSONObject json = new JSONObject();
	public String toString() {
		try {
			return json.toString(1);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
	public void setJavaObject(Object object) {
		json = new JSONObject(object);
	}
	public void setXML(String xml) {
		try {
			this.json = XML.toJSONObject(xml);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		HelloJSON json = new HelloJSON();
		System.out.println(json);
		Hello hello = new Hello();
		hello.setMessage("안녕하세요");
		hello.setAge(27);
		hello.setWeight(58.7);
		json.setJavaObject(hello);
		System.out.println(json);
		json.setXML("<hello id=\"hello1\">" +
				"<to>magneto</to>" +
				"<from>tomcat</from>" +
				"<message>Nice to meet you!</message>" +
				"</hello>");
		System.out.println(json);
	}
}