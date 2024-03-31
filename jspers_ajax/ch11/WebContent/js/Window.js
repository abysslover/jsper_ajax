function Window() {
	this.buildWindow();
	this.buildTitleBar();
	this.buildContent();
	this.buildStatusBar();
}
Window.prototype = {
	defaultWidth : "300px",
	defaultContentHeight : "200px",
	setVisible : function(visible) {
		this.visible = visible;
		this.element.style.display = visible ? "block" : "none";
	},
	buildWindow : function() {
		this.element = this.buildElement(document.body,
		{width: "300px", border: "solid 1px", display: "none"});
	},
	buildTitleBar : function() {
		this.titlebar = this.buildElement(this.element,
		{width: "100%", height: "30px", border: "solid 1px"});
		this.titlearea = this.buildElement(this.titlebar,
		{width: "218px", styleFloat: "left", cssFloat: "left", height: "30px", border: "solid 1px"});
		this.titlebuttons = this.buildElement(this.titlebar,
		{height: "30px", styleFloat: "left", cssFloat: "left", border: "solid 1px"});
		this.buildTitleButtons();
	},
	buildTitleButtons : function() {
		this.buildMinimizeButton();
		this.buildMaximizeButton();
		this.buildCloseButton();
	},
	buildMinimizeButton : function() {
		var img = document.createElement("img");
		img.setAttribute("src", "css/img/minimize.jpg");
		var thisBind = this;
		img.onclick = function(evt) {
			if(this.minimized) {
				thisBind.content.style.display = "block";
				thisBind.statusbar.style.display = "block";
				this.minimized = false;
			} else {
				thisBind.content.style.display = "none";
				thisBind.statusbar.style.display = "none";
				this.minimized = true;
			}
		}
		this.titlebuttons.appendChild(img);
	},
	buildMaximizeButton : function() {
		var img = document.createElement("img");
		img.setAttribute("src", "css/img/maximize.jpg");
		var thisBind = this;
		var deskHeight = document.documentElement.clientHeight || document.body.clientHeight;
		img.onclick = function(evt) {
			if(thisBind.maximized) {
				thisBind.element.style.width = thisBind.defaultWidth;
				thisBind.content.style.height = thisBind.defaultContentHeight;
				thisBind.maximized = false;
			} else {
				thisBind.content.style.height = deskHeight - 100 + "px";
				thisBind.element.style.width = "100%";
				thisBind.maximized = true;
			}
		}
		this.titlebuttons.appendChild(img);
	},
	buildCloseButton : function() {
		var img = document.createElement("img");
		img.setAttribute("src", "css/img/close.jpg");
		var thisBind = this;
		img.onclick = function(ev) {
			thisBind.setVisible(false);
		}
		this.titlebuttons.appendChild(img);
	},
	buildContent : function() {
		this.contentHeader = this.buildElement(this.element,
		{width: "100%", display : "block"});
		this.contentHeader.innerHTML = "&nbsp;";
		this.content = this.buildElement(this.element,
		{width: "100%", height: this.defaultContentHeight, border: "solid 1px", overflow : "auto"});
		this.contentFooter = this.buildElement(this.element,
		{width: "100%", display : "block"});
	},
	buildStatusBar : function() {
		this.statusbar = this.buildElement(this.element,
		{width: "100%", height: "15px", border: "solid 1px"});
		this.statusmessage = this.buildElement(this.statusbar,
		{height: "15px", styleFloat: "left", cssFloat: "left", border: "solid 1px"});
		this.statusprogress = this.buildElement(this.statusbar,
		{width: "100px", styleFloat: "right", cssFloat: "right", height: "15px", border: "solid 1px"});
		this.progressbar = this.buildElement(this.statusprogress,
		{width: "0%", height: "15px"});
		this.progressbar.style.backgroundColor = "orange";
	},
	buildElement : function(target, styles, elementAttr) {
		elementAttr = elementAttr || {name : "div"};
		var element = document.createElement(elementAttr.name);
		for(var attr in elementAttr) {
			if("name" == attr) continue;
			element.setAttribute(attr, elementAttr[attr]);
		}
		var eStyle = element.style;
		for(var style in styles) {
			eStyle[style] = styles[style];
		}
		target.appendChild(element);
		return element;
	},
	setTitle : function(title) {
		this.titlearea.innerHTML = title;
	},
	setContentHeader : function(header) {
		this.contentHeader.innerHTML = header;
	},
	setContent : function(content) {
		this.content.innerHTML = content;
	},
	setContentFooter : function(footer) {
		this.contentFooter.innerHTML = footer;
	},
	setStatusMessage : function(statusMessage) {
		this.statusmessage.innerHTML = statusMessage;
	},
	setProcessed : function(processed) {
		this.progressbar.style.width = processed + "%";
	},
	setProgressImageSrc : function(imageSrc) {
		this.progressbar.style.backgroundImage = "url(" + imageSrc + ")";
		this.progressbar.style.backgroundRepeat = "repeat-x";
	},
	scrollTo : function(elem, where) {
		elem.scrollTop = where;
	},
	scrollToTop : function(elem) {
		elem.scrollTop = 0;
	},
	scrollToEnd : function(elem) {
		elem.scrollTop = elem.scrollHeight;
	}
};