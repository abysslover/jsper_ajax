package com.javawide.book.blackbook1.ch08.board;

import java.sql.SQLException;
import java.util.*;

import javax.sql.rowset.CachedRowSet;

import com.javawide.book.blackbook1.ch08.dbms.IDatabaseFacade;
import com.javawide.book.blackbook1.ch08.dbms.MSSQLDatabaseFacade;

public class BoardManager {
	private IDatabaseFacade facade = new MSSQLDatabaseFacade("/db.properties");

	public void createBoard(String boardName) {
		facade.executeUpdate("CREATE TABLE " + boardName + " (id INT IDENTITY PRIMARY KEY," +
		"subject VARCHAR(100), content TEXT, writer VARCHAR(24), written DATETIME DEFAULT GETDATE())");
	}
	public void dropBoard(String boardName) {
		facade.executeUpdate("DROP TABLE " + boardName);
	}
	
	public Collection<String> getBoardTableNames() {
		CachedRowSet crs = facade
				.execute("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE "
						+ "OBJECTPROPERTY(OBJECT_ID(TABLE_NAME), N'IsUserTable') = 1 AND TABLE_NAME LIKE 'BOARD%'");
		Collection<String> tables = new ArrayList<String>();
		try {
			while (crs.next()) {
				tables.add(crs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tables;
	}

	public static void main(String[] args) {
		BoardManager manager = new BoardManager();
		manager.createBoard(String.format("%s%2$tY%2$tm", "BOARD", Calendar.getInstance()));
		/*for(int i=1; i<=12; ++i) {
			manager.createBoard(String.format("%s%tY%02d", "BOARD", Calendar.getInstance(), i));
		}*/
		//manager.dropBoard("BOARD200803");
		for(String s : manager.getBoardTableNames()) {
			System.out.println(s);
		}
		/*for(String s : manager.getBoardTableNames()) {
			manager.dropBoard(s);
		}*/
	}
}
