package com.javawide.book.blackbook1.ch09;

import java.util.*;

public class HelloAJAXUserList {
	private Map<String, String> users =
		Collections.synchronizedMap(new LinkedHashMap<String, String>());
	private HelloAJAXCounter counter = new HelloAJAXCounter();

	public void addUser(String sessionKey, String id) {
		if("GUEST".equals(id)) {
			id += counter.toString();
		}
		users.put(sessionKey, id);
	}
	public void removeUser(String sessionKey) {
		users.remove(sessionKey);
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer(20).append("<users>");
		for(String key : users.keySet()) {
			buffer.append("<user>")
			.append(users.get(key)).append("</user>");
		}
		buffer.append("<count>")
			.append(users.size())
			.append("</count>")
			.append("</users>");
		return buffer.toString();
	}
	public String getUserId(String sessionKey) {
		return users.get(sessionKey);
	}
	public static void main(String[] args) {
		HelloAJAXUserList users = new HelloAJAXUserList();
		users.addUser("SessingKey", "magneto");
		users.addUser("SessingKey" + new Random().nextInt(), "abysslover");
		users.addUser("SessingKey" + new Random().nextInt(), "hello");
		users.addUser("SessingKey" + new Random().nextInt(), "GUEST");
		users.addUser("SessingKey" + new Random().nextInt(), "GUEST");
		System.out.println(users);
		String sessionKey = "SessingKey" + new Random().nextInt();
		users.addUser(sessionKey, "GUEST");
		System.out.println(users.getUserId(sessionKey));
	}
}
