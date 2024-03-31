package com.javawide.book.blackbook1.ch11.board;

import java.math.BigDecimal;

public class GetContent extends AbDatabaseQueryBehavior {
	private BigDecimal id;
	public void execute() {
		String sql = "EXEC hello.GetContent ?";
		dbFacade.setSQL(sql);
		dbFacade.setParameter(1, id);
		rs = dbFacade.execute();
	}
	public void setId(BigDecimal id) {
		if(null == id) this.id = new BigDecimal(-1);
		this.id = id;
	}
	public void setId(String id) {
		if(null == id) this.id = new BigDecimal(-1);
		this.id = new BigDecimal(id);
	}
	protected BigDecimal getTotalRows() {
		return BigDecimal.ONE;
	}
}
