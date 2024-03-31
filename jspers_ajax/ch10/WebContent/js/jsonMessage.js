Event.observe(window, "load", function() {
	Event.observe("getJSON", "click", function() {
	new Ajax.Request("Hello_JSON.jsp",
			{method: "get", onComplete: setResult});
	});
});
function setResult(response) {
	var json = response.responseText.evalJSON();
	var body = json.hello;
	$("result").innerHTML += "message : " + body.message + " to : " + body.to + " from : " + body.from;
}