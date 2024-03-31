package com.javawide.book.blackbook1.ch12.board;

import java.math.BigDecimal;
import java.sql.SQLException;

public abstract class AbDatabaseQueryBehavior extends AbDatabaseBehavior {
	public abstract void execute();
	protected abstract BigDecimal getTotalRows();
	public String toString() {
		StringBuffer buffer = new StringBuffer(1000).append("<board>")
			.append(String.format("<totalRows>%s</totalRows>", getTotalRows()));
		try {
			generateColumns();
			while(rs.next()) {
				buffer.append("<row>");
				for(String col : cols) {
					buffer.append(String.format("<%s>%s</%s>", col, rs.getString(col), col));
				}
				buffer.append("</row>");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
		}
		return buffer.append("</board>").toString();
	}
	
}
