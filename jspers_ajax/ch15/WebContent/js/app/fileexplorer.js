var FileExplorer = Class.create();
Object.extend(Object.extend(FileExplorer.prototype, Window.prototype), {
	DIRECTORY_ICON_CLASSNAME : "directoryIcon",
	FILE_ICON_CLASS_NAME : "fileIcon",
	DIRECTORY_READER : "app/fileexplorer",
	FILE_READER : "app/filereader",
	initialize: function(option) {
		Window.call(this);
		Object.extend(this, option);
		this.setVisible(true);
		this.setTitle("File Explorer");
		taskManager.addTask(this);
		this.request();
	},
	request : function() {
		new Ajax.Request(this.DIRECTORY_READER, {method: "get", parameters: {folderName : this.folderName},
			onSuccess : function(response) {
			this.setContent(response.responseText);
			this.attachDirClickHandler();
			this.attachFileClickHandler();
		}.bind(this)});
	},
	attachDirClickHandler : function() {
		var dirs = $(this.content).getElementsBySelector("div[class='" + this.DIRECTORY_ICON_CLASSNAME + "']");
		this.attachParentDirClickHandler();
		dirs.each(function(dir) {
			Event.observe(dir, "click", function(evt) {
				this.folderName = this.folderName || "";
				this.folderName += "/" + dir.innerHTML;
				this.setContentHeader(this.folderName);
				this.request();
			}.bind(this));
		}.bind(this));
	},
	attachParentDirClickHandler : function() {
		var parentDir = document.createElement("div");
		parentDir.className = this.DIRECTORY_ICON_CLASSNAME;
		parentDir.innerHTML = "..";
		this.content.insertBefore(parentDir, this.content.firstChild);
		Event.observe(parentDir, "click", function(evt) {
			var _folderName = this.folderName || "";
			this.folderName = _folderName.substring(0, _folderName.lastIndexOf("/"));
			this.setContentHeader(this.folderName);
			this.request();
		}.bind(this));
	},
	attachFileClickHandler : function() {
		var files = $(this.content).getElementsBySelector("div[class='" + this.FILE_ICON_CLASS_NAME + "']");
		files.each(function(file) {
			Event.observe(file, "click", function(evt) {
				var fileViewer = new Window("FileViewer");
				fileViewer.setVisible(true);
				fileViewer.setTitle("File Viewer - " + file.innerHTML);
				taskManager.addTask(fileViewer);
				new Ajax.Request(this.FILE_READER, {method: "get", parameters: {userFile: this.folderName || "" + "/"+ file.innerHTML},
					onSuccess: function(response) {
						fileViewer.setContent(response.responseText);
					}
				});
			}.bind(this));
		}.bind(this));
	}
});
Event.observe(window, "load", function(evt) {
	Event.observe("fileExplorer", "click", function(evt) {
		new FileExplorer();
	});
});