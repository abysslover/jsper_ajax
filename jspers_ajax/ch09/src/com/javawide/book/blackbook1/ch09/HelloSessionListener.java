package com.javawide.book.blackbook1.ch09;

import javax.servlet.http.*;

public class HelloSessionListener implements HttpSessionListener {
	private static HelloAJAXUserList userList = new HelloAJAXUserList();
	public static HelloAJAXUserList getUserList() {
		return userList;
	}
	public void sessionCreated(HttpSessionEvent evt) {
		HttpSession session = evt.getSession();
		userList.addUser(session.getId(), "GUEST");		
	}
	public void sessionDestroyed(HttpSessionEvent evt) {
		HttpSession session = evt.getSession();
		userList.removeUser(session.getId());
	}
}