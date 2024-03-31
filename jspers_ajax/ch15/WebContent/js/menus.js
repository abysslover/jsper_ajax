var MenuManager = function() {};
Object.extend(MenuManager, {
	menus : [],
	find: function(menuId){
		return this.menus[menuId];
	},
	register: function(menuId, menu) {
		this.menus[menuId] = menu;
	}
});

Event.observe(window, "load", function(evt) {
	var desktopMenu = new Menu({title: "뿌리", parent: "desktop", id: "HelloMenu", events: {
		click: function(evt) {
			alert(this.title);
		}}
	});
	var direction = new Menu({title: "방향", parent : desktopMenu });
	var horizontal = new Menu({title: "수평", parent : direction, events: {
		click: function(evt) {
			$(this.rootMenu.parent).getElementsBySelector("div").reject(function(elem) {
				return elem.getAttribute("id").match(this.rootMenu.id);
			}.bind(this)).each(function(div) {
				$(div).setStyle({cssFloat: "left", stylefloat: "left", display: "inline"});						
			});
		}}
	});
	var vertical = new Menu({title: "수직", parent : direction, events: {
		click: function(evt) {
			$(this.rootMenu.parent).getElementsBySelector("div").reject(function(elem) {
				return elem.getAttribute("id").match(this.rootMenu.id);
			}.bind(this)).each(function(div) {
				$(div).setStyle({cssFloat: "", stylefloat: "", display: "block"});						
			});
		}}
	});
	var test = new Menu({title: "테스트", parent : desktopMenu });
	var test1 = new Menu({title: "테스트1", parent : test });
	var test2 = new Menu({title: "테스트2", parent : test });
	MenuManager.register("desktop", desktopMenu);
});