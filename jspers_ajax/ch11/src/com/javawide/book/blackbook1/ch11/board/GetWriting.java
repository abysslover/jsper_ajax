package com.javawide.book.blackbook1.ch11.board;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import com.javawide.book.blackbook1.ch08.dbms.MSSQLDatabaseFacade;

public class GetWriting extends AbDatabaseQueryBehavior {
	private BigDecimal page = BigDecimal.ONE;
	private String searchtype;
	private String keyword;
	private int rowcountinpage = 10;
	private Paging paging;
	private BigDecimal totalRows;
	public void execute() {
		createPaging();
		if(null != searchtype && null != keyword) {
			String predicate = String.format("%s LIKE '%%%s%%'", searchtype, keyword);
			execute(predicate);
			return;
		}
		String sql = "EXEC hello.GetWriting ?, ?, ?";
		dbFacade.setSQL(sql);
		dbFacade.setParameter(1, page);
		dbFacade.setParameter(2, paging.displayRowCount());
		dbFacade.setParameter(3, rowcountinpage);
		rs = dbFacade.execute();
	}
	private void execute(String predicate) {
		String sql = "EXEC hello.GetWriting ?, ?, ?, ?";
		dbFacade.setSQL(sql);
		dbFacade.setParameter(1, page);
		dbFacade.setParameter(2, paging.displayRowCount());
		dbFacade.setParameter(3, rowcountinpage);
		dbFacade.setParameter(4, predicate);
		rs = dbFacade.execute();
	}
	private void createPaging() {
		MSSQLDatabaseFacade dbFacade = new MSSQLDatabaseFacade("/db.properties");
		String predicate = (null == searchtype || null == keyword) ? "" : String.format("'%s LIKE ''%%%s%%'''", searchtype, keyword);
		String sql = String.format("hello.GetCountWithPredicate %s", predicate);
		CachedRowSet rs = dbFacade.execute(sql);
		BigDecimal count = BigDecimal.ZERO;
		try {
			while(rs.next()) {
				count = rs.getBigDecimal(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.paging = new Paging(count, page);
		this.totalRows = count;
	}
	protected BigDecimal getTotalRows() {
		return this.totalRows;
	}

	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setPage(String page) {
		if(null == page) page = "1";
		this.page = new BigDecimal(page);
	}
	public void setPage(BigDecimal page) {
		if(null == page) page = BigDecimal.ONE;
		this.page = page;
	}
	public void setRowcountinpage(int rowcountinpage) {
		this.rowcountinpage = rowcountinpage;
	}
}
