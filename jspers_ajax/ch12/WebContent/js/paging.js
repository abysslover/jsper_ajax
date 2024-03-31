var Paging = Class.create();
Paging.prototype = {
	initialize: function(totalRows, currentPage) {
		this.totalRows = parseInt(totalRows);
		this.currentPage = currentPage;
		this.getTotalPage();
		this.minPage();
		this.maxPage();
	},
	rowCountInPage: 10,
	pageCountInPage: 20,
	maxPage: function() {
		var aMaxPage = this.minPage() + this.pageCountInPage;
		return Math.min(this.getTotalPage(), aMaxPage);
	},
	minPage: function() {
		var aMinPage = this.currentPage - this.pageCountInPage / 2;
		return Math.max(1, aMinPage);
	},
	getTotalPage: function() {
		var remainder = this.totalRows % this.rowCountInPage;
		this.totalPage = this.totalRows / this.rowCountInPage + ((0 == remainder) ? 0:1);
		this.totalPage = parseInt(this.totalPage);
		return this.totalPage;
	},	
	getPageNumbers: function() {
		var pages = [];
		for(var i=this.minPage(); i<=this.maxPage(); ++i) {
			pages.push(i);
		}
		return pages;
	},
	displayRowCount: function() {
		var displayRowCount = this.rowCountInPage;
		if(this.currentPage >= this.totalPage) {
			displayRowCount = this.totalRows - (this.rowCountInPage * (this.totalPage - 1));
		}
		return displayRowCount;
	}
};