function setResult(response) {
	$("result").innerHTML = response.responseText;
}
Event.observe(window, "load", function() {
	Event.observe("getList", "click", function() {
		new Ajax.Request("/ch09/HelloAJAXUserList.jsp",
		{method: "get", onComplete: setResult});
	});
	Event.observe("greet", "click", function() {
		new Ajax.Request("/ch09/HelloAJAX.jsp",
			{method: "get", parameters: "name=magneto", onComplete: setResult});
	});
	new Ajax.PeriodicalUpdater("result", "/ch09/HelloAJAXUserList.jsp",
		{method: "get"});
	new Ajax.Updater("onetimeResult", "/ch09/HelloAJAXUserList.jsp",
		{method: "get"});
	new Loader("helloloader");
	new Loader("magnetoloader");
});

var Loader = Class.create();
Loader.prototype = {
	initialize : function(loaderElement) {
		this.loaderElement = $(loaderElement);
		Ajax.Responders.register(this);
	},
	onCreate : function(evt) {
		if(null == this.loaderElement) return;
		this.loaderElement.innerHTML += "데이터 받기 시작";
	},
	onComplete : function(evt) {
		if(null == this.loaderElement) return;
		this.loaderElement.innerHTML += "데이터 받기 완료";
	}
}