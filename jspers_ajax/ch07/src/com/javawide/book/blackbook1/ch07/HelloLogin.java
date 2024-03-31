package com.javawide.book.blackbook1.ch07;

public class HelloLogin {
	public static void main(String[] args) {
		HelloLogin login = new HelloLogin();
		System.out.println(login.login("hello", "magnetic"));
		System.out.println(login.login("hello", "magneto"));
		System.out.println(login.login("hello", "magneton"));
	}

	public String login(String id, String pw) {
		return (("hello".equals(id) && "magneto".equals(pw))) ? "로그인 성공" : "로그인 실패";
	}
}
