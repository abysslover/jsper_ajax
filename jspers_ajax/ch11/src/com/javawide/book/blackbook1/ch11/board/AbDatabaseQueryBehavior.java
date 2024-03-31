package com.javawide.book.blackbook1.ch11.board;

import java.math.BigDecimal;
import java.sql.SQLException;

public abstract class AbDatabaseQueryBehavior extends AbDatabaseBehavior {
	public abstract void execute();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs = null;
		return buffer.append("</board>").toString();
	}
	protected abstract BigDecimal getTotalRows();
}
