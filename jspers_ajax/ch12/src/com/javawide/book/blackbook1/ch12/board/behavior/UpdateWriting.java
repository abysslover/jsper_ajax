package com.javawide.book.blackbook1.ch12.board.behavior;

import java.math.BigDecimal;

import com.javawide.book.blackbook1.ch12.board.AbDatabaseBehavior;

public class UpdateWriting extends AbDatabaseBehavior{
	private BigDecimal id;
	private String subject;
	private String content;
	public void execute() {
		String sql = "EXEC hello.UpdateWriting ?, ?, ?";
		dbFacade.setSQL(sql);
		dbFacade.setParameter(1, id);
		dbFacade.setParameter(2, subject);
		dbFacade.setParameter(3, content);
		dbFacade.executeUpdate();	
	}
	public void setId(BigDecimal id) {
		if(null == id) this.id = new BigDecimal(-1);
		this.id = id;
	}
	public void setId(String id) {
		if(null == id) this.id = new BigDecimal(-1);
		this.id = new BigDecimal(id);
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
