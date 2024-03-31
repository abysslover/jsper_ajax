package com.javawide.book.blackbook1.ch08.board;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Paging {
	private BigDecimal totalRows;
	private long currentPage;
	private long totalPage;
	private int rowCountInPage = 10;
	private int pageCountInPage = 20;

	public Paging(BigDecimal totalRows, int currentPage) {
		this.totalRows = totalRows;
		this.currentPage = currentPage;
		System.out.println(getTotalPage());
		System.out.println(minPage());
		System.out.println(maxPage());
	}
	public void setRowCountInPage(int rowCountInPage) {
		this.rowCountInPage = rowCountInPage;
	}
	private void setPageCountInPage(int pageCountInPage) {
		this.pageCountInPage = pageCountInPage;
	}
	public long maxPage() {
		long maxPage = minPage() + pageCountInPage;
		return Math.min(getTotalPage(), maxPage);
	}
	public long minPage() {
		long minPage = currentPage - pageCountInPage / 2;
		return Math.max(1, minPage);
	}
	public long getTotalPage() {
		int remainder = totalRows.remainder(new BigDecimal(rowCountInPage)).intValue();
		this.totalPage = totalRows.divide(new BigDecimal(rowCountInPage)).longValue() + ((0 == remainder) ? 0:1);
		return totalPage;
	}	
	public Collection<Long> getPageNumbers() {
		ArrayList<Long> pages = new ArrayList<Long>();
		for(long i = minPage(); i<=maxPage(); ++i) {
			pages.add(i);
		}
		return pages;
	}
	public int displayRowCount() {
		int displayRowCount = rowCountInPage;
		if(currentPage >= totalPage) {
			displayRowCount = totalRows.subtract(new BigDecimal(rowCountInPage * (totalPage-1))).intValue();
		}
		return displayRowCount;
	}
	public int getRowCountInPage() {
		return rowCountInPage;
	}
	public static void main(String[] args) {
		Paging paging = new Paging(new BigDecimal(201), 21);
		paging.setRowCountInPage(10);
		paging.setPageCountInPage(20);
		System.out.println(paging.displayRowCount());
	}
}
