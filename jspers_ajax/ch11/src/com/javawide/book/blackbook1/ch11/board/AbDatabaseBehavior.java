package com.javawide.book.blackbook1.ch11.board;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import com.javawide.book.blackbook1.ch08.dbms.IDatabaseFacade;
import com.javawide.book.blackbook1.ch08.dbms.MSSQLDatabaseFacade;

public abstract class AbDatabaseBehavior implements IBehavior {
	public abstract void execute();
	protected IDatabaseFacade dbFacade = new MSSQLDatabaseFacade("/db.properties");
	protected CachedRowSet rs;
	protected ArrayList<String> cols;
	public void setDbFacade(IDatabaseFacade facade) {
		this.dbFacade = facade;
	}
	protected void generateColumns() {
		cols = new ArrayList<String>();
		ResultSetMetaData data;
		try {
			data = rs.getMetaData();
			int counts = data.getColumnCount() + 1;
			for(int i=1; i< counts; ++i) {
				cols.add(data.getColumnName(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String toString() {
		execute();
		return super.toString();
	}
}
