var TaskManager = Class.create();
TaskManager.prototype = {
	initialize : function(id) {
		this.id = id;
		this.element = $(id);
		this.style = this.element.style;
		this.tasks = [];
	},
	addTask : function(task) {
		this.setTaskLocation(task);
		task.element.setStyle({zIndex : this.tasks.length});
		this.tasks.push(task);
		var taskButton = document.createElement("div");
		taskButton.className = "taskButton";
		taskButton.appendChild(document.createTextNode(task.getTitle()));
		task.taskButton = taskButton;
		this.element.appendChild(taskButton);
		Event.observe(taskButton, "click", function(evt) {
			task.setVisible(true);			
		});
	},
	arrangeZIndex : function(index) {
		var current = this.tasks[index];
		this.tasks = this.tasks.without(current);
		this.tasks.push(current);
		this.tasks.each(function(task, index) {
			task.element.setStyle({zIndex: index}); 
		});
	},
	TASK_WINDOW_START : {x: 100, y: 100},
	TASK_WINDOW_DIFFSIZE : 30,
	windowStartPos : {x:0, y:0},
	setTaskLocation : function(task) {
		var taskPos = {x: this.TASK_WINDOW_START.x + this.windowStartPos.x,
						y: this.TASK_WINDOW_START.y + this.windowStartPos.y};
			this.windowStartPos.x += this.TASK_WINDOW_DIFFSIZE;
			this.windowStartPos.y += this.TASK_WINDOW_DIFFSIZE;
		if(this.windowStartPos.x > $("desktop").getWidth() * 0.3 ||
			this.windowStartPos.y > $("desktop").getHeight() * 0.3) {
			this.TASK_WINDOW_START.x = this.TASK_WINDOW_START.x + this.TASK_WINDOW_DIFFSIZE;
			this.windowStartPos = {x: 0, y: 0};
		}
		task.setPosition(taskPos);
	}
};
Event.observe(window, "load", function(evt) {
	window.taskManager = new TaskManager("helloTaskManager");
});