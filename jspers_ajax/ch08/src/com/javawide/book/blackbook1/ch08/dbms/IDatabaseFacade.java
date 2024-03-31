package com.javawide.book.blackbook1.ch08.dbms;

public interface IDatabaseFacade {
	void setSQL(String sql);
	void setParameter(int index, Object value);
	javax.sql.rowset.CachedRowSet execute(String sql);
	javax.sql.rowset.CachedRowSet execute();
	void executeUpdate(String sql);
	void executeUpdate();
}