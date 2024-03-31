function Window() {
	this.buildWindow();
	this.buildTitleBar();
	this.buildContent();
	this.buildStatusBar();
	this.enStyle();
	this.attachClickEventHandler();
}
Window.prototype = {
	defaultWidth : "305px",
	defaultContentHeight : "200px",
	setVisible : function(visible) {
		this.visible = visible;
		this.element.style.display = visible ? "block" : "none";
	},
	buildWindow : function() {
		this.element = this.buildElement($("desktop"), {width: "305px", display: "none"});
	},
	buildTitleBar : function() {
		this.titlebar = this.buildElement(this.element,
		{width: "100%", height: "30px", border: "solid 1px"});
		this.titlearea = this.buildElement(this.titlebar,
		{width: "218px", styleFloat: "left", cssFloat: "left", height: "30px"});
		this.titlebuttons = this.buildElement(this.titlebar,
		{height: "30px", styleFloat: "left", cssFloat: "left"});
		this.buildTitleButtons();
		new Dragger({element: this.titlearea, target: this.element, _Window : this, events: {
			beforemousemove : function(evt) {
				if(this.maximized) {
					throw $break;
				}
			},
			aftermousemove : function(evt) {
				taskManager.arrangeZIndex(this.target.getStyle("zIndex"));
			}
		}}); 
	},
	buildTitleButtons : function() {
		this.buildMinimizeButton();
		this.buildMaximizeButton();
		this.buildCloseButton();
	},
	buildMinimizeButton : function() {
		var img = document.createElement("img");
		img.setAttribute("src", "css/img/minimize.jpg");
		Event.observe(img, "click", function(evt) {
			this.setVisible(false);
			this.minimized = true;
		}.bind(this));
		this.titlebuttons.appendChild(img);
	},
	buildMaximizeButton : function() {
		var img = document.createElement("img");
		img.setAttribute("src", "css/img/maximize.jpg");
		this.maximzeResizeHandler = function(evt) {
			this.maximize();
		}.bind(this);
		this.maximizeHandler = function(evt) {
			if(this.maximized) {
				this.restorePrevSize();
				Event.stopObserving(window, "resize", this.maximzeResizeHandler);
			} else {
				this.savePrevSize();
				this.maximize();
				Event.observe(window, "resize", this.maximzeResizeHandler);
			}
		}.bind(this);
		Event.observe(img, "click", this.maximizeHandler);
		this.titlebuttons.appendChild(img);
	},
	maximize : function() {
		var taskHeight = taskManager.element.getHeight();
		var clientHeight = desktop.currentDimension().height;
		this.element.setStyle({width: "100%", height: clientHeight - taskHeight + "px"});
		$(this.titlearea).setStyle({width: $(this.titlebar).getWidth() -
		$(this.titlebuttons).getWidth() - 5 + "px"});
		this.content.setStyle({height: "90%"});
		this.setPosition({ x: -10, y: taskHeight-10});
		this.maximized = true;
	},
	savePrevSize : function() {
		this.originalDimension = this.element.getDimensions();
		this.originalContentHeight = $(this.content).getHeight();
		this.originalLocation = {x: this.element.offsetLeft, y: this.element.offsetTop};
	},
	restorePrevSize : function() {
		this.setPosition(this.originalLocation);
		this.element.setStyle({width: this.originalDimension.width+ "px",
		height: this.originalDimension.height+ "px"});
		this.titlearea.setStyle({width: this.titlebar.getWidth() -
		this.titlebuttons.getWidth() - 5 + "px"});
		this.content.style.height = this.originalContentHeight + "px";
		this.maximized = false;
	},	
	buildCloseButton : function() {
		var img = document.createElement("img");
		img.setAttribute("src", "css/img/close.jpg");
		Event.observe(img, "click", function(evt) {
			this.setVisible(false);
			if(this.taskButton) {
				this.taskButton.parentNode.removeChild(this.taskButton);
			}
		}.bind(this));
		this.titlebuttons.appendChild(img);
	},
	buildContent : function() {
		this.contentHeader = this.buildElement(this.element,
		{width: "100%", display : "block", border: "solid 1px"});
		this.contentHeader.innerHTML = "&nbsp;";
		this.content = this.buildElement(this.element,
		{width: "100%", height: this.defaultContentHeight, overflow : "auto",
		borderLeft: "solid 1px", borderRight: "solid 1px"});
		this.contentFooter = this.buildElement(this.element,
		{width: "100%", display : "block", borderLeft: "solid 1px", borderRight: "solid 1px"});
	},
	buildStatusBar : function() {
		this.statusbar = this.buildElement(this.element,
		{width: "100%", height: "15px", border: "solid 1px"});
		this.statusmessage = this.buildElement(this.statusbar,
		{height: "15px", styleFloat: "left", cssFloat: "left"});
		this.statusprogress = this.buildElement(this.statusbar,
		{width: "100px", styleFloat: "right", cssFloat: "right", height: "15px"});
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
		this.title = title;
		this.titlearea.innerHTML = title;
	},
	getTitle : function() {
		return this.title;
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
	},
	setPosition : function(pos) {
		this.element.setStyle({left: pos.x + "px", top: pos.y + "px"});
	},
	enStyle : function() {
		this.titlearea.className = "HelloWindow_TitleArea";
		this.titlebuttons.className = "HelloWindow_TilteButtons";
		this.contentHeader.className = "HelloWindow_ContentHeader";
		this.content.className = "HelloWindow_Content";
		this.contentFooter.className = "HelloWindow_ContentFooter";
		this.statusbar.className = "HelloWindow_StatusBar";
		this.statusmessage.className = "HelloWindow_StatusMessage";
		this.statusprogress.className = "HelloWindow_StatusProgress";
	},
	attachClickEventHandler : function() {
		Event.observe(this.element, "click", function(evt) {
			taskManager.arrangeZIndex(this.element.getStyle("zIndex"));
		}.bind(this));
	},
	resizeToContent: function() {
		var deskDim = desktop.currentDimension();
		var taskDim = taskManager.element.getDimensions();
		var maxWidth = deskDim.width;
		var maxHeight = deskDim.height - taskDim.height;
		var contentMaxWidth = 0;
		var contentMaxHeight = 0;
		$(this.content).immediateDescendants().each(function(elem) {
			contentMaxWidth = Math.max(contentMaxWidth, $(elem).getWidth());
			contentMaxHeight = Math.max(contentMaxHeight, $(elem).getHeight());
		});
		var _width = Math.min(contentMaxWidth + 30, maxWidth);
		var _height = Math.min(contentMaxHeight + 30, maxHeight); 
		this.element.setStyle({width: _width + "px", height: _height + "px"});
		var subtract = 0;
		[this.titlebar, this.contentHeader, this.contentFooter, this.statusbar].each(function(elem) {
			subtract += $(elem).getHeight();
		});
		this.content.setStyle({height: _height - subtract + "px"});
		$(this.titlearea).setStyle({width: $(this.titlebar).getWidth() -
		$(this.titlebuttons).getWidth() - 5 + "px"});
	}
};

var Dragger = Class.create();
Dragger.prototype = {
	initialize : function(option) {
		Object.extend(this, option);
		this.target = $(this.target);
		this.target.setStyle({ position: "absolute" });
		this.attachMouseDownEventHandler();
		this.attachMouseUpEventHandler();
		this.attachMouseOverEventHandler();
		this.attachMouseMoveEventHandler();
	},
	isDrag : false,
	attachMouseDownEventHandler : function() {
		Event.observe(this.element, "mousedown", function(evt) {
			this.isDrag = true;
			this.draggerDiff = {x: Event.pointerX(evt) - this.target.offsetLeft,
				y : Event.pointerY(evt) - this.target.offsetTop};
		}.bind(this));
	},
	attachMouseUpEventHandler : function() {
		Event.observe(document, "mouseup", function(evt) {
			this.isDrag = false;
			this.element.setStyle({cursor : "move"});
		}.bind(this));
	},
	attachMouseOverEventHandler : function() {
		Event.observe(this.element, "mouseover", function(evt) {
			this.element.setStyle({cursor : "move"});
		}.bind(this));
	},
	attachMouseMoveEventHandler : function() {
		Event.observe(document, "mousemove", function(evt) {
			Event.stop(evt);
			try {
			if(this.isDrag) {
				if(this.events.beforemousemove)
					this.events.beforemousemove.call(this._Window, evt);
				this.target.setStyle({left : Event.pointerX(evt) - this.draggerDiff.x + "px",
					top: Event.pointerY(evt) - this.draggerDiff.y + "px" });
				if(this.events.aftermousemove)
					this.events.aftermousemove.call(this, evt);
			}
			}catch(e) {}
		}.bind(this));
	}
};