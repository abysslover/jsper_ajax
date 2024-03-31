package com.javawide.book.blackbook1.ch13.flex;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

import javax.sql.rowset.CachedRowSet;

import com.javawide.book.blackbook1.ch08.dbms.IDatabaseFacade;
import com.javawide.book.blackbook1.ch08.dbms.MSSQLDatabaseFacade;

public class HelloFlexBoard {
	public static void main(String[] args) {
		HelloFlexBoard board = new HelloFlexBoard();
		try {
			System.out.println(board.getWriting());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<HashMap<String, String>> getWriting() throws SQLException {
		IDatabaseFacade facade = new MSSQLDatabaseFacade("/db.properties");
		CachedRowSet rs = facade.execute("hello.GetWriting 10, 10, 1");
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		fillRows(result, rs);
		return result;
	}

	private void fillRows(List<HashMap<String, String>> result, CachedRowSet rs) throws SQLException {
		ArrayList<String> cols = makeColumnNames(rs);
		HashMap<String, String> aRow = null;
		while(rs.next()) {
			aRow = new LinkedHashMap<String, String>();
			for(String col : cols) {
				aRow.put(col, rs.getString(col));
			}
			result.add(aRow);
		}
	}

	private ArrayList<String> makeColumnNames(CachedRowSet rs)
			throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		int count = meta.getColumnCount() + 1;
		ArrayList<String> cols = new ArrayList<String>();
		for(int i=1; i<count; ++i) {
			cols.add(meta.getColumnName(i));
		}
		return cols;
	}
}
