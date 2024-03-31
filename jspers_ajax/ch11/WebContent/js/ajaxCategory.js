var Category = Class.create();
Category.prototype = {
	initialize : function(parent, jsonObj) {
		this.parentElement = $(parent);
		this.parentElement.innerHTML = "";
		var props = this.getProperties(jsonObj);
		jsonObj = jsonObj[props[0]];
		props = this.getProperties(jsonObj);
		for(var i=0; i<props.length; ++i) {
			this.getPrefix(props[i]);
			//this.parentElement.innerHTML += props[i] + ": " + jsonObj[props[i]] + "<p />";
		}
		//this.parentElement.innerHTML += "Prefix : " + this.prefix + "<p />";
		this.getElementName(props);
		//this.parentElement.innerHTML += "ElementName : " + this.elementName + "<p />";
		//this.generateLevel(jsonObj);
		this.rootObject = jsonObj;
		this.buildRootCategory();
		this.createDepth(jsonObj);
	},
	getProperties : function(obj) {
		var props = [];
		for(var v in obj) {
			props.push(v);
		}
		return props;
	},
	getPrefix : function(prop) {
		var start = prop.indexOf("xmlns:");
		if(-1 != start && -1 == prop.indexOf("xsi")) {
			 this.prefix = prop.substring(start + "xmlns:".length) + ":";
		}
	},
	getElementName : function(props) {
		for(var i=0; i<props.length; ++i) {
			if(-1 != props[i].indexOf(this.prefix)) this.elementName = props[i].substring(this.prefix.length);
		}
	},
	buildRootCategory : function() {
		var root = this.rootObject;
		root.depth = 0;
		root.identifier = 0;
		root.children = 0;
		var elem = this.buildCategoryElement(root.depth);
		this.addCategory(elem, root);
		this.attachCategoryChanger(elem, root);
	},
	attachCategoryChanger : function(elem, category) {
		Event.stopObserving(elem, "click", this.categoryChanger.bindAsEventListener(this, category));
		Event.observe(elem, "click", this.categoryChanger.bindAsEventListener(this, category));
	},
	categoryChanger : function(evt, category) {
		var info = this.getSelectedInfo(Event.element(evt));
		var subCategory = this.getSubCategory(category, info.identifier);
		if(!subCategory) return;
		var newDepth = info.depth + 1;
		this.cleanCategoryElement(newDepth);
		var categoryElem = this.buildCategoryElement(newDepth);
		for(var i=0; i<subCategory.length; ++i) {
			this.addCategory(categoryElem, subCategory[i]);
			this.attachCategoryChanger(categoryElem, subCategory[i]);
		}
	},
	getSelectedInfo : function(elem) {
		if("option" == elem.tagName.toLowerCase()) {
			elem = elem.parentNode;
		}
		var _depth = parseInt(elem.getAttribute("depth"));
		var _identifier = elem.options[elem.selectedIndex].value;
		return {depth : _depth, identifier : _identifier};		
	},
	getSubCategory : function(parent, selectedIdentifier) {
		var elementFullName = this.prefix + this.elementName;
		var jsonSubCategory = $A([parent[elementFullName]]).flatten().findAll(function(sub) {
			if(!sub) return false;
			return sub.parent.identifier == selectedIdentifier;
		});
		return (0 == jsonSubCategory.length) ? null : jsonSubCategory;		
	},
	cleanCategoryElement : function(depth) {
		++depth;
		var elementFullName = this.prefix + this.elementName;
		var categoryElement = $(elementFullName+depth);
		while(true) {
			var elemName = elementFullName+depth;
			if(!$(elemName)) {
				break;
			}
			$(elemName).parentNode.removeChild($(elemName)); 
			++depth;
		}
	},
	buildCategoryElement : function(depth, elemName, subElemName) {
		elemName = elemName || "select";
		subElemName = subElemName || "option";
		var elementFullName = this.prefix + this.elementName;
		var elemId = elementFullName + depth;
		var elem = $(elemId);
		if(!elem) {
			elem = document.createElement(elemName);
			elem.setAttribute("id", elemId);
			this.parentElement.appendChild(elem);
		}
		elem.innerHTML = "";
		elem.setAttribute("depth", depth);
		return elem;
	},
	addCategory : function(parent, obj, elemName) {
		elemName = elemName || "option";
		var elem = document.createElement(elemName);
		elem.value = obj.identifier;
		elem.innerHTML = obj.name;
		parent.appendChild(elem);
	},
	createDepth : function(elem, thisBind) {
		thisBind = thisBind || this;
		var elementFullName = thisBind.prefix + thisBind.elementName;
		var jsonSubCategory = elem[elementFullName];
		var subCategories = $A([jsonSubCategory]).flatten();
		if(!jsonSubCategory) return;
		elem.children = elem.children || 0;
		subCategories.each(function(sub) {
			sub.depth = elem.depth + 1;
			sub.identifier = elem.identifier + "-" + elem.children++;
			sub.parent = elem;
			thisBind.createDepth(sub, thisBind);
		});
	},
	displayObject : function(obj, filter) {
		filter = $A(filter).flatten();
		for(var attr in obj) {
			if(0 == filter.length || filter.include(attr)) {
				this.parentElement.innerHTML += attr + " : " + obj[attr] + "<br />";
			}
		}
	}
}

Event.observe(window, "load", function() {
	Event.observe("getCategory", "click", function() {
		new Ajax.Request("HelloCategory.jsp",
			{method: "get", parameters: "t=" + new Date().getTime(), onSuccess: function(response) {
				new Category("result", response.responseText.evalJSON());
			}});
	});
});