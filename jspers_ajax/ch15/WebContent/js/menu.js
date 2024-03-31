var Menu = Class.create();
Menu.prototype = {
	initialize : function(option) {
		this.items = [];
		Object.extend(this, Object.extend({title : ""}, option));
		if(this.parent instanceof Menu) {
			this.parentId = this.parent.id;
			this.rootMenu = this.parent.rootMenu;
		} else {
		 	this.parentId = this.parent;
		 	this.rootMenu = this;
		}
		this.buildElement();
		this.buildLeftMouseClickHandler();
	},
	buildLeftMouseClickHandler : function() {
		Event.observe("desktop", "click", function(evt) {
			Event.stop(evt);
			var id = Event.element(evt).getAttribute("id");
			var menuId = id.match(this.rootMenu.id);
			if(!menuId) $(this.rootMenu.id).hide();
			else {
				this.items.findAll(function(elem) {
					return elem.id.match(id + "_.$");
				}).each(function(found) {
					var style = found.element.style;
					style.display = ("block" == style.display) ? "none" : "block";
					style.visibility = ("visible" == style.visibility) ? "hidden" : "visible"; 
				});
			}
		}.bind(this));
		for(var method in this.events) {
			var func = function(evt) {
				if(this.id == Event.element(evt).getAttribute("id"))
					this.events[method].call(this, evt);
			}.bind(this);
			Event.observe(this.element, method, func);
		}
	},
	add : function(subMenu) {
		this.items.push(subMenu);
	},
	buildElement : function() {
		this.createId();
		var elem = $(this.id);
		if(elem) {
			return (this.element = elem);
		}
		var _parent = $(this.parentId);
		return this.createElement({parent : _parent,
			elementName: "div",
			attr : {id : this.id},
			text : this.title,
			style : {
				backgroundColor: "limegreen",
				display : "none",
				visibility : "hidden"
			}
		});
	},
	createId : function() {
		if(this.id) return;
		this.parent.add(this);
		this.sequence = this.parent.items.length;
		this.id = this.parentId + "_" + this.sequence;
	},
	createElement : function(option) {
		elem = document.createElement(option.elementName);
		var attr = option.attr;
		for(var v in attr) {
			elem.setAttribute(v, attr[v]);
		}
		Object.extend(elem.style, option.style);
		elem.innerHTML = option.text;
		this.element = elem;
		option.parent.appendChild(elem);
	},
	setVisible : function(visible) {
		var style = this.element.style;
		with(style) {
			display = (visible) ? "block" : "none";
			visibility =(visible) ? "visible" : "hidden";
		}
	},
	setPosition : function(pos) {
		this.element.setStyle({left: pos.x + "px", top: pos.y + "px"});
	}	
};