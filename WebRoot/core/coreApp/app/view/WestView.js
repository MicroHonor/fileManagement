/**
 * 宸﹁竟閮ㄥ垎
 */
Ext.define("core.app.view.WestView",{
	extend: 'Ext.panel.Panel',
	alias: 'widget.westview',
	collapsible: true,
	split: true,
	defaults: {
		bodyStyle: 'padding:2px'
	}, 	
	border:1,
	margins: '2 2 0 0',
	width: 225,
	minSize: 100,
	maxSize: 250,
	title:"功能模块导航",
	layout : 'accordion',
	layoutConfig :{
				titleCollapse: false,
				animate: true,
				activeOnTop: true
			},
	items:[{
		title:"户籍档案",
		items:[{
			xtype:"treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border:0,
			root: {
	       	 	expanded: true,
	        	children: [
	            	{ 
	            		id:"household_catalog",
	            		text: "案卷目录", 
	            	 	leaf: true 
	            	},{
	            		id:"household_inner_catalog",
	            		text: "卷内目录", 
	            	 	leaf: true 
	            	}
	        	]
    		}
		}]
	},{
		title:"文书类别",
		items:[{
			xtype:"treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border:0,
			root: {
	       	 	expanded: true,
	        	children: [{ 
	            		text: "文书目录",
	            		id:"category-product",
	            	 	leaf: true 
	            	}
	        	]
    		}
		}]
	},{
		title:"业务类别",
		items:[{
			xtype:"treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border:0,
			root: {
	       	 	expanded: true,
	        	children: [
	            	{ 
	            		id:"productmanager",
	            		text: "业务目录", 
	            	 	leaf: true 
	            	}
	        	]
    		}
		}]
	},{
		title:"商品类别",
		items:[{
			xtype:"treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border:0,
			root: {
	       	 	expanded: true,
	        	children: [{ 
	            		text: "类别管理",
	            		id:"category-product",
	            	 	leaf: true 
	            	}
	        	]
    		}
		}]
	},{
		title:"商品档案",
		items:[{
			xtype:"treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border:0,
			root: {
	       	 	expanded: true,
	        	children: [
	            	{ 
	            		id:"productmanager",
	            		text: "商品管理", 
	            	 	leaf: true 
	            	}
	        	]
    		}
		}]
	}],
    initComponent: function(){
        this.callParent(arguments);
    }
});



