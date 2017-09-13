/**
 * ClassName 流程挂接视图
 * */
Ext.define("core.image.view.ImageGrid",{
	extend:"Ext.grid.GridPanel",
	alias:"widget.imagegrid",
	store:"core.image.store.ImageStore",
	width:400,
	height:300,
//	store:Ext.create("core.file.store.FileStore"),
	selModel:{
		selType:"checkboxmodel"
	},
	border:0,
	multiSelect:true,
	frame:true,
	tbar:[
		{xtype:'button',text:'删除',ref:'removeFile',iconCls:'table_remove'}
//		,
//		"->",
//		"按用户名模糊查询:",
//		{
//			xtype: 'triggerfield', 
//			triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
//			listeners:{
//            	"change":function(_this,_new,_old,_opt){ 
//            		var _store = _this.ownerCt.ownerCt.getStore();
//            		_store.clearFilter(false);
//            		_store.filter("name",_new);
//                }
//            },
//            onTriggerClick: function() {
//            	var _store = this.ownerCt.ownerCt.getStore();
//            	_store.clearFilter(false);
//            	_store.filter("name",this.getValue());
//		    }
//		}
	],
	bbar:{
		xtype:'pagingtoolbar',
		store:this.store,
		dock:'bottom',
		displayInfo:true
	},
	plugins:[//建立插件
		      Ext.create("Ext.grid.plugin.CellEditing",{//建立编辑单元格插件
		    	  clicksToEdit:1//单击次数进行编辑      2次
		      })       
	],
	//enableKeyNav:true,  //可以使用键盘控制上下
	columnLines:true, //展示竖线
	columns:[
		{xtype: 'rownumberer'},
		{text:"文件名称",dataIndex:"fileName",width:100},
		{text:"文件大小",dataIndex:"fileSize",width:70,
	         renderer:function(value,cellmeta,record,rowIndex,columnIndex,store){		
				return (value/1024).toFixed(1)+"kb";
	         }
	    },{text:"操作",dataIndex:"filePath",width:100,
	    	renderer:function(value,cellmeta,record,rowIndex,columnIndex,store){		
				return "<div><a href='"+value+"' target='blank'>查看</a></div>";
	         }
	    }
	],
	initComponent:function(){
		this.callParent(arguments);
	}
});