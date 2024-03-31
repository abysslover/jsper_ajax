var AJAXBoard = Class.create();
Object.extend(Object.extend(AJAXBoard.prototype, Window.prototype), {
	page : 1,
	param : {
			t: new Date().getTime(),
			page: this.page,
			behavior: "listwrite"
	},
	initialize : function(option) {
		Object.extend(this, option);
		Window.call(this);
		this.setControlAttribute();
		this.getWriting();

	},
	setControlAttribute : function() {
		this.setTitle(this.title);
		this.titlearea.style.width = "557px";
		this.defaultWidth = "640px";
		this.element.style.width = "640px";
		this.defaultContentHeight = "400px";
		this.content.style.height = "400px";
	},
	buildBoardTable : function() {	
		this.boardTable = this.buildElement(this.content, {}, {name: "table"});
		this.boardTableHeader = this.buildElement(this.boardTable, {}, {name: "thead"});
		this.boardTableBody = this.buildElement(this.boardTable, {}, {name: "tbody"});
		this.boardTableFooter = this.buildElement(this.boardTable, {}, {name: "tfoot"});
	},
	getBoardData : function() {
		this.resetContent();
		new Ajax.Request("HelloBoard.jsp", {method: "get", parameters : this.param,
			onSuccess: function(response) {
				var behavior = this.display[this.param.behavior].bind(this, response);
				setTimeout(behavior, 10);
			}.bind(this),
			onFailure : function(response) {
				this.getWriting();
			}.bind(this)
		});
	},
	display: {
		"listwrite": function(response) {
			var elem = response.responseXML.firstChild.firstChild;
			elem = this.processTotalRows(elem);
			this.buildHeader(elem.firstChild);
			while(elem) {
				this.buildRow(elem.firstChild);
				elem = elem.nextSibling;
			}
			this.buildNewWritingButton();
		},
		"listcontent": function(response) {
			var elem = response.responseXML.firstChild.firstChild.nextSibling.firstChild;
			var content = {};
			while(elem) {
				content[elem.tagName] = elem.firstChild.nodeValue;
				elem = elem.nextSibling;
			}
			new Ajax.Request("HelloBoardModifyForm.jsp", {onSuccess: function(response) {
				this.content.innerHTML = response.responseText;
				Form.deserialize("HelloBoardModifyForm", $H(content).toQueryString());
			}.bind(this)});
			this.buildGetWritingButton();
			this.buildUpdateWritingButton();
			this.buildDeleteWritingButton();
			this.buildNewMemoButton();
			this.getMemo();
		},
		"newwrite": function(response) {
			this.getWriting();
		},
		"updatewrite" : function(response) {
			this.getContent(this.param.id);
		},
		"deletewrite" : function(response) {
			this.getWriting();
		},
		"newmemo" : function(response) {
			this.getContent(this.param.id);
		}
	},
	buildGetWritingButton : function() {
		this.btnGetWriting = this.buildElement(this.contentFooter, {},
			{name: "input", type: "button", value: "목록 보기"});
		Event.observe(this.btnGetWriting, "click", function() { this.getWriting(); }.bind(this));
	},
	buildNewWritingButton : function() {
		this.btnNewWriting = this.buildElement(this.contentFooter, {},
			{name: "input", type: "button", value: "새글 추가"});
		Event.observe(this.btnNewWriting, "click", function() { this.newWritingForm(); }.bind(this));
	},
	buildUpdateWritingButton : function() {
		this.btnUpdateWriting = this.buildElement(this.contentFooter, {},
			{name: "input", type: "button", value: "수정 하기"});
		Event.observe(this.btnUpdateWriting, "click", function() { this.updateWriting(); }.bind(this));
	},
	buildDeleteWritingButton : function() {
		this.btnDeleteWriting = this.buildElement(this.contentFooter, {},
			{name: "input", type: "button", value: "삭제 하기"});
		Event.observe(this.btnDeleteWriting, "click", function() { this.deleteWriting(); }.bind(this));
	},
	buildNewMemoButton : function() {
		this.btnNewMemo = this.buildElement(this.contentFooter, {},
			{name: "input", type: "button", value: "메모 달기"});
		Event.observe(this.btnNewMemo, "click", function() { this.newMemoForm(); }.bind(this));
	},
	resetContent : function() {
		[this.contentHeader, this.boardTableHeader, this.boardTableBody, this.contentFooter].each(
		function(aNode) {
			$A(aNode.getElementsByTagName("*")).each(function(aTag) {
				aTag.parentNode.removeChild(aTag);
			});
		});
		this.contentHeader.innerHTML = "&nbsp";
	},
	buildHeader : function(elem) {
		var tr = this.buildElement(this.boardTableHeader, {}, {name: "tr"});
		while(elem) {
			var th = this.buildElement(tr, {}, {name: "th"});
			th.innerHTML = elem.tagName;
			elem = elem.nextSibling;
		}
	},
	buildRow : function(elem) {
		var tr = this.buildElement(this.boardTableBody, {}, {name: "tr"});
		while(elem) {
			if("id" == elem.tagName) {
				var id = elem.firstChild.nodeValue;
				Event.observe(tr, "click", function() { this.getContent(id); }.bind(this));
			}
			var td = this.buildElement(tr, {}, {name: "td"});
			td.innerHTML = elem.firstChild.nodeValue;
			elem = elem.nextSibling;
		}
	},
	processTotalRows : function(elem) {
		this.contentHeader.innerHTML = "";
		var div = this.buildElement(this.contentHeader, {}, {name: "div"});
		var totalRows = elem.firstChild.nodeValue;
		div.innerHTML = "Total Rows : " + totalRows;
		var paging = new Paging(totalRows, this.page);
		paging.getPageNumbers().each(function(pageNum) {
			var aPage = this.buildElement(this.contentHeader, {display: "inline"}, {name: "div"});
			aPage.innerHTML = "[" + pageNum + "]";
			aPage.style.cursor = "pointer";
			Event.observe(aPage, "click", function() { this.setPage(pageNum); }.bind(this));
		}.bind(this));
		return elem.nextSibling;
	},
	setPage : function(page) {
		this.param.behavior = "listwrite";
		this.param.page = page;
		this.getBoardData();
	},
	getContent : function(id) {
		this.param.id = id;
		this.param.behavior = "listcontent";
		this.getBoardData();
	},
	getWriting : function() {
		this.content.innerHTML = "";
		this.buildBoardTable();
		this.param.behavior = "listwrite";
		this.getBoardData();
	},
	newWritingForm : function() {
		new Ajax.Request("HelloBoardNewForm.jsp", {onSuccess: function(response) {
			this.resetContent();
			this.content.innerHTML = response.responseText;
			Event.observe("HelloBoardNewWrite", "click", function(){ this.newWriting() }.bind(this));
		}.bind(this)});
	},
	newWriting : function() {
		Object.extend(this.param, $("HelloBoardNewForm").serialize(true));
		this.param.behavior = "newwrite";
		this.getBoardData();
	},
	updateWriting : function() {
		Object.extend(this.param, $("HelloBoardModifyForm").serialize(true));
		this.param.behavior = "updatewrite";
		this.getBoardData();
	},
	deleteWriting : function() {
		this.param.behavior = "deletewrite";
		this.getBoardData();
	},
	newMemoForm : function() {
		new Ajax.Request("HelloBoardNewMemoForm.jsp", {onSuccess: function(response) {
			this.resetContent();
			this.content.innerHTML = response.responseText;
			Event.observe("HelloBoardNewMemoWrite", "click", function(){ this.newMemo() }.bind(this));
		}.bind(this)});
	},
	newMemo : function() {
		Object.extend(this.param, $("HelloBoardNewMemoForm").serialize(true));
		this.param.behavior = "newmemo";
		this.getBoardData();
	},
	getMemo : function() {
		var param = {};
		Object.extend(param, this.param);
		param.behavior = "listmemo";
		new Ajax.Request("HelloBoard.jsp", {parameters : param,
			onSuccess: function(response) {
			var elem = response.responseXML.firstChild.firstChild.nextSibling;
			while(elem) {
				this.buildMemo(elem.firstChild);
				elem = elem.nextSibling;
			}
		}.bind(this)});
	},
	buildMemo : function(elem) {
		var memo = {};
		while(elem) {
			memo[elem.tagName] = elem.firstChild.nodeValue;
			elem = elem.nextSibling;
		}
		var div = this.buildElement(this.content, {}, {name: "div"});
		div.innerHTML = "from " + memo["writer"] + " at " + memo["writtentime"] + "<br />" +
					memo["content"] + "<br />";
		var aForm = this.buildElement(this.content, {display: "none"}, {name: "form"});
		var content = this.buildElement(aForm, {}, {name:"input", type: "text"});
		content.setAttribute("name", "content");
		var id = this.buildElement(aForm, {}, {name:"input", type: "hidden", value: memo["id"]});
		id.setAttribute("name", "id");
		var btnUpdateMemo = this.buildElement(aForm, {}, {name:"input", type: "button", value: "수정 하기"});
		var btnCancelUpdateMemo = this.buildElement(aForm, {}, {name:"input", type: "button", value: "취소 하기"});
		var btnDeleteMemo = this.buildElement(aForm, {}, {name:"input", type: "button", value: "삭제 하기"});
		Event.observe(div, "click", function() { Element.hide(div); Element.show(aForm); });
		Event.observe(btnCancelUpdateMemo, "click", function() { Element.show(div); Element.hide(aForm); });
		Event.observe(btnUpdateMemo, "click", function() { this.updateMemo(aForm); }.bind(this));
		Event.observe(btnDeleteMemo, "click", function() { this.deleteMemo(memo["id"]); }.bind(this));
	},
	updateMemo : function(aForm) {
		var param = Object.extend(Object.extend({}, this.param), 
		$(aForm).serialize(true));
		param.behavior = "updatememo";
		new Ajax.Request("HelloBoard.jsp", {parameters : param,
			onSuccess: function(response) {
				this.getContent(this.param.id);
			}.bind(this)
		});
	},
	deleteMemo : function(id) {
		var param = Object.extend({}, this.param);
		param.id = id;
		param.behavior = "deletememo";
		new Ajax.Request("HelloBoard.jsp", {parameters : param,
			onSuccess: function(response) {
				this.getContent(this.param.id);
			}.bind(this)
		});
	}
});