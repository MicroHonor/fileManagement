Ext.define("core.image.controller.ImageController", {
	extend : "Ext.app.Controller",
	
	init : function() {
		var self = this;
		this.control({

		});
	},
	views : [
		"core.image.view.ImageGrid","core.image.view.ImageWin"
	],
	stores : ["core.image.store.ImageStore"],
	models : ["core.image.model.ImageModel"]
});