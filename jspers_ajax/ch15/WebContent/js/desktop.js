var Desktop = Class.create();
Desktop.prototype = {
	initialize : function(id) {
		this.id = id;
		this.element = $(id);
		this.resize();
		this.attachRightMouseClickHandler();
		this.attachResizeHandler();
	},
	resize : function() {
		var taskHeight = taskManager.element.getHeight();
		var clientHeight = this.currentDimension().height;
		this.element.setStyle({height: clientHeight - taskHeight + "px"});
	},
	currentDimension : function() {
		return {
			width:	document.documentElement.clientWidth,
			height:	document.documentElement.clientHeight
		}
	},
	attachRightMouseClickHandler : function() {
		Event.observe("desktop", "contextmenu", function(evt) {
			Event.stop(evt);
			var pos = { x: Event.pointerX(evt), y: Event.pointerY(evt) };
			var menuId = Event.element(evt).getAttribute("id");
			var menu = MenuManager.find(menuId);
			if(menu) {
			menu.setVisible(true);
			menu.setPosition(pos);
		}});		
	},
	attachResizeHandler : function() {
		Event.observe(window, "resize", function(evt){
			this.resize();
		}.bind(this));
	}	
};
Event.observe(window, "load", function(evt) {
	window.desktop = new Desktop("desktop");
});