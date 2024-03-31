function Window() {
	this.buildWindow();
	this.buildTitleBar();
	this.buildContent();
	this.buildStatusBar();
}
Window.prototype = {
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
				thisBind.element.style.width = "300px";
				thisBind.content.style.height = "200px";
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
		this.content = this.buildElement(this.element,
		{width: "100%", height: "200px", border: "solid 1px"});
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
	buildElement : function(target, styles) {
		var element = document.createElement("div");
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
	setContent : function(content) {
		this.content.innerHTML = content;
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
	}
};