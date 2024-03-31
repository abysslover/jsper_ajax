function NumericStepper(target, id, range) {
	this.buildNumericStepper(target, id);
	this.buildRange(range);
}
NumericStepper.prototype = {
	buildNumericStepper : function(target, id) {
		if(this.element) return;
		var element = document.createElement("div");
		element.setAttribute("id", id);
		element.setAttribute("tabindex", "0");		
		this.element = element;
		// element.innerHTML = "NumericStepper";
		var targetElem = ("body" == target) ?
			document.getElementsByTagName("body")[0] : document.getElementById(target);
		targetElem.appendChild(element);
		this.attachKeyEventHandler();
	},
	attachKeyEventHandler : function() {
		var VK_UP = 38;
		var VK_DOWN = 40;
		var thisBind = this;
		this.element.onkeydown = function(evt) {
			var keyCode = evt ? evt.keyCode : event.keyCode;
			switch (keyCode) {
				case VK_UP:
					thisBind.increaseValue();
				break;
				case VK_DOWN:
					thisBind.decreaseValue();
				break;
			}
		}		
	},
	buildRange : function(range) {
		if(!range || !range.min || !range.max || this.rangeElem) return;
		this.range = range;
		this.value = range.min;
		this.buildValue();
		this.buildButtons();
	},
	buildValue : function() {
		var valueElem = document.createElement("div");
		valueElem.style.background = "orange";
		valueElem.style.width = "1px";
		valueElem.style.display = "inline";
		valueElem.innerHTML = this.value;
		this.valueElem = valueElem;
		this.element.appendChild(valueElem);		
	},
	buildButtons : function() {
		this.buttons = document.createElement("div");
		this.buttons.style.display = "inline";
		this.element.appendChild(this.buttons);
		this.createUpperButton();
		this.createLowerButton();
		this.enStyleButtons();
	},
	createUpperButton : function() {
		var btn = document.createElement("input");
		btn.setAttribute("type", "button");
		btn.setAttribute("value", "▲");
		var thisBind = this;
		btn.onclick = function() {
			thisBind.increaseValue();
		}
		this.buttons.appendChild(btn);
	},
	createLowerButton : function() {
		var btn = document.createElement("input");
		btn.setAttribute("type", "button");
		btn.setAttribute("value", "▼");
		var thisBind = this;
		btn.onclick = function() {
			thisBind.decreaseValue();
		}
		this.buttons.appendChild(btn);
	},
	enStyleButtons : function() {
		var len = this.buttons.childNodes.length;
		for(var i=0; i<len; ++i){
			var btn = this.buttons.childNodes[i];
			btn.style.cursor = "pointer";
			btn.style.border = "0px";
		}
	},	
	increaseValue : function() {
		if((this.range.max-1) <= this.value) return;
		++this.value;
		this.refresh();
	},
	decreaseValue : function() {
		if(this.range.min >= this.value) return;
		--this.value;
		this.refresh();
	},
	refresh : function() {
		this.valueElem.innerHTML = this.value;
	}	
}