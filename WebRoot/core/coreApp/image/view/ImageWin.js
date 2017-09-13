/**
 * ClassName 流程挂接视图
 * */
Ext.define("core.image.view.ImageWin",{
	extend:"Ext.window.Window",
	alias:"widget.ImageWin",
 	title: '文件上传',
    closeAction: 'hide',
    layout: 'fit',
    resizable: false,
    modal: true
    ,
    items:[{
    	xtype:"imagegrid"
    }]
	,
	initComponent:function(){
		this.callParent(arguments);
	}
});