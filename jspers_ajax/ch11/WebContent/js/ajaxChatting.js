var Chatting = Class.create();
Object.extend(Object.extend(Chatting.prototype, Window.prototype), {
	initialize : function(option) {
		Object.extend(this, option);
		Window.call(this);
		this.setTitle(this.title);
		this.buildSendControls();
		this.buildChatterControls();
		this.getChatters();
		this.attachChatterRefresher();
		this.attachEnterKeySender();
		this.attachChatterSelector();
		this.attachStopForContentReader();
	},
	buildSendControls : function() {
		this.chatMessage = this.buildElement(this.contentFooter, {}, {name: "input", type: "text"});
		this.sendMessage = this.buildElement(this.contentFooter, {},
		{name: "input", type: "button", value: "채팅 내용 전송"});
		this.attachSendButtonEventHandler();
	},
	attachSendButtonEventHandler : function() {
		Event.observe(this.sendMessage, "click", this.sendingMessage.bind(this));
	},
	sendingMessage : function() {
		var param = {
			t: new Date().getTime(),
			message: this.chatMessage.value,
			chattername : this.chatterList[this.chatterList.selectedIndex].value
		}
		new Ajax.Request(this.chatURL, {
		method: "get", parameters: param, onFailure: function(response) {
				this.setStatusMessage("존재하지 않는 URL이거나 오류가 발생했습니다.");					
			}.bind(this)
		});
		this.chatterRefresh();
	},
	buildChatterControls : function() {
		this.chatterName = this.buildElement(this.contentFooter, {}, {name: "input", type: "text"});
		this.newChatter = this.buildElement(this.contentFooter, {},
		{name: "input", type: "button", value: "채터 생성"});
		this.attachNewChatterButtonEventHandler();
		this.getChatterList = this.buildElement(this.contentFooter, {},
		{name: "input", type: "button", value: "채터 목록 얻기"});
		this.chatterList = this.buildElement(this.contentFooter, {},
		{name: "select"});
		this.attachChatterListButonEventHandler();
	},
	attachNewChatterButtonEventHandler : function() {
		Event.observe(this.newChatter, "click", function() {
			var param = {
				t: new Date().getTime(),
				chattername : this.chatterName.value
			};
			new Ajax.Request("HelloNewChatter.jsp", {
			method: "get", parameters: param,
				onSuccess: function(response) {
					this.setStatusMessage(param.chattername + " 채터 생성 성공");
					this.getChatters();					
				}.bind(this),
				onFailure: function(response) {
					this.setStatusMessage("존재하지 않는 URL이거나 오류가 발생했습니다.");					
				}.bind(this)
			});
		}.bind(this));
	},
	attachChatterListButonEventHandler : function() {
		Event.observe(this.getChatterList, "click", this.getChatters.bind(this));
	},
	attachChatterRefresher : function() {
		this.chatterRefresher = new Ajax.PeriodicalUpdater(this.content,
		"HelloChatter.jsp", {frequency : 0.8, 
			onSuccess : function(response) {
				var message = response.responseXML.firstChild.firstChild;
				this.setContent("");
				while(message) {
					var div = this.buildElement(this.content, {}, {name : "div"});
					div.innerHTML = message.firstChild.nodeValue;
					message = message.nextSibling;
				}
				this.scrollToEnd(this.content);
			}.bind(this),
			onFailure: function(response) {
				this.setStatusMessage("존재하지 않는 URL이거나 오류가 발생했습니다.");					
			}.bind(this)
		});
		this.chatterRefresher.stop();
	},
	getChatters : function() {
		var param = {
			t: new Date().getTime()
		}
		new Ajax.Request("HelloChatterList.jsp", {
		method: "get", parameters: param,
			onSuccess: function(response) {
				var chatter = response.responseXML.firstChild.firstChild;
				this.chatterList.innerHTML = "";
				while(chatter) {
					var opt = this.buildElement(this.chatterList, {}, {name : "option"});
					opt.text = chatter.firstChild.nodeValue;
					opt.value = chatter.firstChild.nodeValue;
					chatter = chatter.nextSibling;
				}					
			}.bind(this),
			onFailure: function(response) {
				this.setStatusMessage("존재하지 않는 URL이거나 오류가 발생했습니다.");					
			}.bind(this)
		});
	},
	attachEnterKeySender : function() {
		var VK_ENTER = 13;
		Event.observe(this.element, "keypress", function(evt) {
			var keyCode = evt ? evt.keyCode : event.keyCode;
			if(keyCode == VK_ENTER)
				this.sendingMessage();
		}.bind(this));
	},
	attachChatterSelector : function() {
		Event.observe(this.chatterList, "change", function(evt) {
			this.chatterRefresh();
		}.bind(this));
	},
	chatterRefresh : function() {
		this.chatterRefresher.stop();
		this.chatterRefresher.options.parameters = {
			t : new Date().getTime(),
			chattername : this.chatterList[this.chatterList.selectedIndex].value
		};
		this.chatterRefresher.start();
	},
	attachStopForContentReader : function() {
		Event.observe(this.content, "mouseover", function(evt) { this.chatterRefresher.stop(); }.bind(this));
		[this.contentHeader, this.contentFooter].each(function(elem){
			Event.observe(elem, "mouseover", function(evt) {
				this.chatterRefresher.stop();
				this.chatterRefresher.start();
			}.bind(this));
		}.bind(this));
	}
});