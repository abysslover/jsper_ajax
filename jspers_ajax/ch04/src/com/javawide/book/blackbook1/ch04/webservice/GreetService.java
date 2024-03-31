package com.javawide.book.blackbook1.ch04.webservice;

public class GreetService {
	@WebMethod
	public String doGreet(String name) {
		return String.format("안녕하세요. %s 님", name); 
	}
	@WebMethod(level=2)
	public int add(int op1, int op2) {
		return op1 + op2;
	}
	@WebMethod(level=2)
	public int minus(int op1, int op2) {
		return op1 - op2;
	}
	@WebMethod(level=2)
	public long multiply(int op1, int op2) {
		return op1 * op2;
	}
	@WebMethod(level=2)
	public double divide(int op1, int op2) {
		return op1 / op2; 
	}
	public String hiddenMethod(String who) {
		return String.format("Hidden %", who);
	}
}