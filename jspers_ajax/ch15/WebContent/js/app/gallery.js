var Gallery = Class.create();
Object.extend(Object.extend(Gallery.prototype, FileExplorer.prototype), {
	DIRECTORY_READER: "app/gallery",
	ROOT_FOLDER: "gallery",
	initialize: function(option) {
		Window.call(this);
		Object.extend(this, option);
		this.setVisible(true);
		this.setTitle("Gallery");
		taskManager.addTask(this);
		this.request();
	},
	attachFileClickHandler : function() {
		var files = $(this.content).getElementsBySelector("div[class='" + this.FILE_ICON_CLASS_NAME + "']");
		files.each(function(file) {
			Event.observe(file, "click", function(evt) {
				var fileViewer = new Window("ImageViewer");
				fileViewer.setVisible(true);
				fileViewer.setTitle("Image Viewer - " + file.innerHTML);
				taskManager.addTask(fileViewer);
				var filePath = this.ROOT_FOLDER + (this.folderName || "") + "/"+ file.innerHTML;
				var img = fileViewer.buildElement(fileViewer.content, {}, {name: "img", src: filePath});
				Event.observe(img, "load", function(evt) {
					fileViewer.setStatusMessage("Image loaded");
					fileViewer.resizeToContent();
				}.bind(this));
			}.bind(this));
		}.bind(this));
	}
});
Event.observe(window, "load", function(evt) {
	Event.observe("gallery", "click", function(evt) {
		new Gallery("gallery");
	});
});