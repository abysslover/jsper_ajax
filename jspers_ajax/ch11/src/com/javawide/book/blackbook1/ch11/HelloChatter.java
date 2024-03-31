package com.javawide.book.blackbook1.ch11;

import java.util.*;

public class HelloChatter {
	private static Map<String, HelloChatter> chatters = new Hashtable<String, HelloChatter>();
	private Collection<String> messages = new ArrayList<String>();
	private HelloChatter() {
	}
	public void add(String message) {
		this.messages.add(filter(message));
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer(20)
			.append("<messages>");
		for(String m : messages) {
			buffer.append(String.format("<message>%s</message>", m));
		}
		return buffer.append("</messages>").toString();
	}
	private String filter(String message) {
		return message.replace("<", "&lt;").replace(">", "&gt;");
	}
	
	public static String getChatterNames() {
		StringBuffer buffer = new StringBuffer(20).append("<chatters>");
		for(String n : chatters.keySet()) {
			buffer.append(String.format("<chatter>%s</chatter>", n));
		}
		return buffer.append("</chatters>").toString();
	}
	public static HelloChatter createChatter(String name) {
		HelloChatter chatter = new HelloChatter();
		HelloChatter.chatters.put(name, chatter);
		return chatter;
	}
	public static HelloChatter getChatter(String chatterName) {
		if(null == chatterName) {
			chatterName = "Default";
			HelloChatter.createChatter(chatterName);
		}
		return HelloChatter.chatters.get(chatterName);
	}
	
	public static void main(String[] args) {
		HelloChatter chatter = HelloChatter.createChatter("X-men");
		chatter.add("magneto : Hello");
		chatter.add("abysslover : Hello X-men!");
		chatter.add("<script>alert('악성 사용자');");
		System.out.println(chatter);
		HelloChatter chatterYmen = HelloChatter.createChatter("Y-men");
		chatterYmen.add("storm : Hello");
		chatterYmen.add("psylocke : hi");
		System.out.println(chatterYmen);
		System.out.println(HelloChatter.getChatterNames());
	}
}
