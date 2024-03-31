package com.javawide.book.blackbook1.ch12.board.behavior;

import java.math.BigDecimal;

import com.javawide.book.blackbook1.ch12.board.AbDatabaseNonQueryBehavior;

public class UpdateMemo extends AbDatabaseNonQueryBehavior {
	private BigDecimal id;
	private String content;

	public void execute() {
		String sql = "EXEC hello.UpdateMemo ?, ?";
		dbFacade.setSQL(sql);
		dbFacade.setParameter(1, id);
		dbFacade.setParameter(2, content);
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
	public void setContent(String content) {
		this.content = content;
	}
}
