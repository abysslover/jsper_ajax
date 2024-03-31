package com.javawide.book.blackbook1.ch11.board;

import java.math.BigDecimal;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import com.javawide.book.blackbook1.ch08.dbms.IDatabaseFacade;
import com.javawide.book.blackbook1.ch08.dbms.MSSQLDatabaseFacade;

public class TSQLMemo {
	private IDatabaseFacade facade = new MSSQLDatabaseFacade("/db.properties");
	public BigDecimal parentId;
	private CachedRowSet rs;
	private ArrayList<String> cols;
	public void setFacade(IDatabaseFacade facade) {
		this.facade = facade;
	}
	public void newMemo(String writer, String content) {
		String sql = "EXEC hello.NewMemo ?, ?, ?";
		facade.setSQL(sql);
		facade.setParameter(1, parentId);
		facade.setParameter(2, writer);
		facade.setParameter(3, content);
		facade.executeUpdate();
	}
	public void getMemo() {
		String sql = "EXEC hello.GetMemo ?";
		facade.setSQL(sql);
		facade.setParameter(1, parentId);
		rs = facade.execute();
	}
	public void updateMemo(int id, String content) {
		String sql = "EXEC hello.UpdateMemo ?, ?";
		facade.setSQL(sql);
		facade.setParameter(1, id);
		facade.setParameter(2, content);
		facade.executeUpdate();
	}
	public void deleteMemo(int id) {
		String sql = "EXEC hello.DeleteMemo ?";
		facade.setSQL(sql);
		facade.setParameter(1, id);
		facade.executeUpdate();
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer(1000).append("<board>");
		if(null == rs) return "<board />";
		try {
			generateColumns();
			while(rs.next()) {
				buffer.append("<row>");
				for(String col : cols) {
					buffer.append(String.format("<%s>%s</%s>", col, rs.getString(col), col));
				}
				buffer.append("</row>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buffer.append("</board>").toString();
	}
	private void generateColumns() {
		cols = new ArrayList<String>();
		ResultSetMetaData data;
		try {
			data = rs.getMetaData();
			int counts = data.getColumnCount();
			for(int i=1; i< counts; ++i) {
				cols.add(data.getColumnName(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}
	public void setParentId(String parentId) {
		if(null == parentId) parentId = "-1";
		this.parentId = new BigDecimal(parentId);
	}
	public static void main(String[] args) {
		TSQLMemo memo = new TSQLMemo();
		memo.setParentId(new BigDecimal(2));
		memo.newMemo("memo", "Hello World");
		memo.getMemo();
		System.out.println(memo);
		memo.updateMemo(1, "Test Memo Content");
		memo.deleteMemo(1);
	}
}
