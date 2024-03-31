var SingleUploader = Class.create();
Object.extend(Object.extend(SingleUploader.prototype, Window.prototype), {
	initialize : function(option) {
		Object.extend(this, option);
		Window.call(this);
		this.setTitle(this.title);
		this.buildUploadForm();
	},
	buildUploadForm : function() {
		var dummy = this.buildElement(this.contentFooter, {display: "none"}, {name: "div"});
		dummy.innerHTML = "<iframe name='singleUploader'></iframe>";
		this.formUpload = this.buildElement(this.contentFooter, {}, {name: "form",
		action: "HelloUploader.jsp", method: "post", target: "singleUploader"});
		this.btnFile = this.buildElement(this.formUpload, {}, {name: "input", type: "file"});
		this.btnFile.setAttribute("name", "uploadFile");
		this.btnUpload = this.buildElement(this.contentFooter, {},
		{name: "input", type: "button", value : "업로드 시작"});
		Event.observe(this.btnUpload, "click", this.upload.bind(this));
		this.formUpload.encoding = "multipart/form-data";
		this.formUpload.submit();
		this.setStatusMessage("");
		this.setProcessed(0);
	},
	upload : function() {
		this.uploadUpdater = new Ajax.PeriodicalUpdater(this.statusmessage, "HelloUploaderReport.jsp", {
		frequency : 0.1,
		onSuccess : function(response) {
			this.displayProcess(response.responseXML.firstChild.firstChild);
		}.bind(this) });
		this.formUpload.encoding = "multipart/form-data";
		this.formUpload.submit();
	},
	displayProcess : function(elem) {
		while(elem) {
			this[elem.tagName] = elem.firstChild.nodeValue;
			elem = elem.nextSibling;
		}
		this.setProcessed(this.processed); 
		this.setStatusMessage(this.bps);
		if(100 == this.processed) {
			this.count = this.count || 0;
			++this.count;
			if(this.count > 2) {
				this.count = 0;
				this.processed = 0;
				this.uploadUpdater.stop();
			}
		}
	}
});