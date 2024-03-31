package com.javawide.book.blackbook1.ch07;

public class Greeting {
	private String name;
	public void setName(String name) {
		this.name = name;
	}
	public String decorateName() {
		String aName = name;
		if(null == name) return "Hello Guest";
		if(aName.length() > 6) {
			aName = aName.substring(0, 6);
		}
		aName = aName.replace('\'', '<');
		aName = "Hello " + aName;
		return aName;
	}
	public String toString() {
		return decorateName();
	}
}
