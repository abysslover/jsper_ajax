package com.javawide.book.blackbook1.ch07.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class HelloTag extends TagSupport {
	private static final long serialVersionUID = -8126150090163455502L;
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().print("안녕하세요 " + user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	private String user;
	public void setUser(String user) {
		this.user = user;
	}	
}
