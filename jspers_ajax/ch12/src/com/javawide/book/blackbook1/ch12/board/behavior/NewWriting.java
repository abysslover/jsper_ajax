package com.javawide.book.blackbook1.ch12.board.behavior;

import com.javawide.book.blackbook1.ch12.board.AbDatabaseNonQueryBehavior;

public class NewWriting extends AbDatabaseNonQueryBehavior {
	private String writer;
	private String subject;
	private String content;

	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void execute() {
		String sql = "EXEC hello.NewWriting ?, ?, ?";
		dbFacade.setSQL(sql);
		dbFacade.setParameter(1, writer);
		dbFacade.setParameter(2, subject);
		dbFacade.setParameter(3, content);
		dbFacade.executeUpdate();
	}
}
