package com.javawide.book.blackbook1.ch08.board;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import com.javawide.book.blackbook1.ch08.dbms.IDatabaseFacade;
import com.javawide.book.blackbook1.ch08.dbms.MSSQLDatabaseFacade;

public class Board {
	private String boardName;
	private IDatabaseFacade facade = new MSSQLDatabaseFacade("/db.properties");
	private Paging paging;
	public Board(String boardName) {
		this.boardName = boardName;
	}
	public void insert(String subject, String content, String writer) {
		facade.setSQL("INSERT INTO " + boardName + "(subject, content, writer) VALUES (?, ?, ?)");
		facade.setParameter(1, subject);
		facade.setParameter(2, content);
		facade.setParameter(3, writer);
		facade.executeUpdate();
	}
	public CachedRowSet getAll() {
		return facade.execute("SELECT id, subject, writer, written FROM " + boardName);
	}
	public CachedRowSet getPagedList(int page) throws SQLException {
		CachedRowSet rs = facade.execute("SELECT COUNT(*) FROM " + boardName);
		while(rs.next()) {
			paging = new Paging(rs.getBigDecimal(1), page);
		}
		return facade.execute("SELECT * FROM (SELECT TOP " + paging.displayRowCount() + " * FROM (" + 
				"SELECT TOP " + (page * paging.getRowCountInPage()) + " id, subject, content, writer, written " + 
				"FROM " + boardName + " ORDER BY written DESC, id DESC)" +
						" AS A ORDER BY written ASC) AS B ORDER BY id DESC");
	}
	public CachedRowSet getPagedList(String col, String keyword, int page) throws SQLException {
		String sql = String.format("SELECT COUNT(*) FROM %s WHERE %s LIKE ?", boardName, col);
		String searchKeyword = String.format("%%%s%%", keyword);
		facade.setSQL(sql);
		facade.setParameter(1, "%" + searchKeyword + "%");
		CachedRowSet rs = facade.execute();
		while(rs.next()) {
			paging = new Paging(rs.getBigDecimal(1), page);
		}
		sql = String.format("SELECT * FROM (SELECT TOP %d * FROM (SELECT TOP %d " +
				"id, subject, content, writer, written " + 
				"FROM %s WHERE %s LIKE ? ORDER BY written DESC, id DESC)" +
						" AS A ORDER BY written ASC) AS B ORDER BY id DESC", paging.displayRowCount(),
						(page * paging.getRowCountInPage()), boardName, col);
		facade.setSQL(sql);
		facade.setParameter(1, searchKeyword);
		return facade.execute();
	}
	public String view(String id) {
		String content = "";
		if(null == id) return content;
		String sql = String.format("SELECT content FROM %s WHERE id=?", boardName);
		facade.setSQL(sql);
		facade.setParameter(1, new BigDecimal(id));
		CachedRowSet rs = facade.execute();
		try {
			while(rs.next()) {
				content = rs.getString("content");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return content;
	}
	public ArrayList<String> getModifyData(String id) {
		ArrayList<String> data = new ArrayList<String>();
		if(null == id) return data;
		String sql = String.format("SELECT subject, content FROM %s WHERE id=?", boardName);
		facade.setSQL(sql);
		facade.setParameter(1, new BigDecimal(id));
		CachedRowSet rs = facade.execute();
		try {
			while(rs.next()) {
				data.add(rs.getString("subject"));
				data.add(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	public void modify(String subject, String content, String id) {
		String sql = String.format("UPDATE %s SET subject=?, content=? WHERE id=?", boardName);
		facade.setSQL(sql);
		facade.setParameter(1, subject);
		facade.setParameter(2, content);
		facade.setParameter(3, new BigDecimal(id));
		facade.executeUpdate();
	}
	
	public void delete(String id) {
		String sql = String.format("DELETE FROM %s WHERE id=?", boardName);
		facade.setSQL(sql);
		facade.setParameter(1, new BigDecimal(id));
		facade.executeUpdate();
	}
	
	public void setFacade(IDatabaseFacade facade) {
		this.facade = facade;
	}
	public Paging getPaging() {
		return paging;
	}
	public static void main(String[] args) {
		Board board = new Board("BOARD200803");
		/*for(int i=0; i<100; ++i) {
			board.insert("Hello Subject" + i, "Hello Content" + i, "magneto" + i);
		}*/
		/*CachedRowSet rs = board.getAll();
		try {
			while(rs.next()) {
				System.out.println(rs.getString("id") + ", " + rs.getString("subject") + ", " +
						rs.getString("writer") + ", " + rs.getString("written"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		/*try {
			CachedRowSet rs = board.getPagedList(20);
			while(rs.next()) {
				System.out.println(rs.getString("id") + ", " + rs.getString("subject") + ", " +
						rs.getString("writer") + ", " + rs.getString("written"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		try {
			CachedRowSet rs = board.getPagedList("writer", "%magneto%", 1);
			while(rs.next()) {
				System.out.println(rs.getString("id") + ", " + rs.getString("subject") + ", " +
						rs.getString("writer") + ", " + rs.getString("written"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
