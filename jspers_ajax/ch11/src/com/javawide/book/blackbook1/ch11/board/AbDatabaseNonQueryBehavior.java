package com.javawide.book.blackbook1.ch11.board;

public abstract class AbDatabaseNonQueryBehavior extends AbDatabaseBehavior {
	public abstract void execute();
	public String toString() {
		return "<board />";
	}
}
