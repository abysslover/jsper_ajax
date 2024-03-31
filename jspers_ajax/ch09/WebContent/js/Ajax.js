function Ajax() {
	if(window.XMLHttpRequest) {
		this.ajax = new XMLHttpRequest();
	} else if(window.ActiveXObject) {
		this.ajax = new ActiveXObject("Microsoft.XMLHTTP");
	}
}

Ajax.prototype = {
	HTTP_STATUS : {
		OK:		200
	},
	READY_STATUS : {
		OK:		4
	},
	update : function(id, url, parameter, updateFunction) {
		var thisBind = this;
		var ajaxBind = this.ajax;
		ajaxBind.abort();
		ajaxBind.onreadystatechange = function() {
			if(ajaxBind.readyState == thisBind.READY_STATUS.OK &&
			ajaxBind.status == thisBind.HTTP_STATUS.OK) {
				if(updateFunction) {
					updateFunction(id, ajaxBind);
				} else {
					thisBind.updateContent(id);
				}
			}
		};
		ajaxBind.open("POST", url, true);
		ajaxBind.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		ajaxBind.send(parameter);
	},
	updateContent : function(id) {
		document.getElementById(id).innerHTML = this.ajax.responseText;
	}
}