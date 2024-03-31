package com.javawide.book.blackbook1.ch12.board.behavior;

import java.math.BigDecimal;

import com.javawide.book.blackbook1.ch12.board.AbDatabaseNonQueryBehavior;

public class NewMemo extends AbDatabaseNonQueryBehavior {
	private BigDecimal id;
	private String writer;
	private String content;

	public void execute() {
		String sql = "EXEC hello.NewMemo ?, ?, ?";
		dbFacade.setSQL(sql);
		dbFacade.setParameter(1, id);
		dbFacade.setParameter(2, writer);
		dbFacade.setParameter(3, content);
		dbFacade.executeUpdate();
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setId(BigDecimal id) {
		if(null == id) this.id = new BigDecimal(-1);
		this.id = id;
	}
	public void setId(String id) {
		if(null == id) this.id = new BigDecimal(-1);
		this.id = new BigDecimal(id);
	}
}
