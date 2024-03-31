package com.javawide.book.blackbook1.ch12.board;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Paging {
	private BigDecimal totalRows = BigDecimal.ZERO;
	private BigDecimal currentPage = BigDecimal.ZERO;
	private BigDecimal totalPage = BigDecimal.ZERO;
	private int rowCountInPage = 10;
	private int pageCountInPage = 20;

	public Paging(BigDecimal totalRows, BigDecimal currentPage) {
		this.totalRows = totalRows;
		this.currentPage = currentPage;
		getTotalPage();
		minPage();
		maxPage();
	}
	public void setRowCountInPage(int rowCountInPage) {
		this.rowCountInPage = rowCountInPage;
	}
	private void setPageCountInPage(int pageCountInPage) {
		this.pageCountInPage = pageCountInPage;
	}
	public BigDecimal maxPage() {
		BigDecimal maxPage = minPage().add(new BigDecimal(pageCountInPage));
		BigDecimal totalPage = getTotalPage();
		return maxPage.compareTo(totalPage) > 0 ? totalPage : maxPage;
	}
	public BigDecimal minPage() {
		BigDecimal minPage = currentPage.subtract(new BigDecimal(pageCountInPage / 2));
		return minPage.compareTo(BigDecimal.ONE) < 0 ? BigDecimal.ONE : minPage; 
	}
	public BigDecimal getTotalPage() {
		BigDecimal remainder = totalRows.remainder(new BigDecimal(rowCountInPage));
		this.totalPage = totalRows.divide(new BigDecimal(rowCountInPage)).add(
				((0 == remainder.intValue()) ? BigDecimal.ZERO : BigDecimal.ONE)).setScale(0, BigDecimal.ROUND_DOWN);
		return totalPage;
	}	
	public Collection<BigDecimal> getPageNumbers() {
		ArrayList<BigDecimal> pages = new ArrayList<BigDecimal>();
		for(BigDecimal i = minPage(); maxPage().compareTo(i) == -1; i = i.add(BigDecimal.ONE)) {
			pages.add(i);
		}
		return pages;
	}
	public int displayRowCount() {
		int displayRowCount = rowCountInPage;
		if(currentPage.compareTo(totalPage) >= 0) {
			displayRowCount = totalRows.subtract(totalPage.subtract(BigDecimal.ONE).multiply(
					new BigDecimal(rowCountInPage))).intValue();
		}
		return displayRowCount;
	}
	public int getRowCountInPage() {
		return rowCountInPage;
	}
	public static void main(String[] args) {
		Paging paging = new Paging(new BigDecimal(201), new BigDecimal(1));
		paging.setRowCountInPage(10);
		paging.setPageCountInPage(20);
		System.out.println(paging.displayRowCount());
	}
	public BigDecimal getCurrentPage() {
		return currentPage;
	}
}
