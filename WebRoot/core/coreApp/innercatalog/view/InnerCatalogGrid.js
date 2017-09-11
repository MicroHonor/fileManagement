/**
 * 户籍档案列表
 * */
Ext.define("core.product.view.InnerCatalogGrid",{
	extend:"Ext.grid.Panel",
	alias:"widget.innerCataloggrid",
	store:"core.household.store.InnerCatalogStore",
//	title : "户籍档案输入",
	border:0,
	selModel:{
		selType:"checkboxmodel",
		checkOnly:true,
		listeners: {
            selectionchange: function(sm, selections) {
//            	alert("selectionchange");
//            	alert(sm.up("household_grid"));
            	Ext.getCmp('householdgriddel').setDisabled(selections.length == 0);
            	Ext.getCmp('householdgridcompare').setDisabled(selections.length == 0);
//                grid4.down('#removeButton').setDisabled(selections.length == 0);
            }
        }
	},
	multiSelect:true,
	frame:true,
	tbar:[
//		{xtype:'button',text:'添加',ref:'add',iconCls:'table_add'},'|',
		{xtype:'button',text:'增加',ref:'insert',iconCls:'table_add'},'|',
		{xtype:'button',text:'删除',ref:'del',iconCls:'table_remove',id:'householdgriddel',disabled: true},'|',
//		{xtype:'button',text:'保存',ref:'save',iconCls:'table_save'},'|',
		{xtype:'button',text:'比对',ref:'compare',iconCls:'table_go',id:'householdgridcompare',disabled: true},
//		{xtype:'button',text:'挂接档案图片上传',ref:'upload',iconCls:'table_go'},
		"->",
		{xtype:'button',text:'查看已比对数据',ref:'viewCompared',iconCls:'table_lightning'}
		
//		"->"
		/*
		'按名称查询:',
		{
			xtype: 'triggerfield', 
			triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
			listeners:{
            	"change":function(_this,_new,_old,_opt){ 
            		var _store = _this.ownerCt.ownerCt.getStore();
            		_store.clearFilter(false);
            		_store.filter("name",_new);
                }
            },
            onTriggerClick: function() {
            	var _store = this.ownerCt.ownerCt.getStore();
            	_store.clearFilter(false);
            	_store.filter("name",this.getValue());
		    }
		},
		'按编号查询:',
		{
			xtype: 'triggerfield', 
			triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
			listeners:{
            	"change":function(_this,_new,_old,_opt){ 
            		var _store = _this.ownerCt.ownerCt.getStore();
            		_store.clearFilter(false);
            		_store.filter("id",_new);
                }
            },
            onTriggerClick: function() {
            	var _store = this.ownerCt.ownerCt.getStore();
            	_store.clearFilter(false);
            	_store.filter("id",this.getValue());
		    }
		}*/
	],
	bbar:{
		xtype:'pagingtoolbar',
		store:'core.household.store.HouseholdStore',
		dock:'bottom',
		displayInfo:true
	},
	enableKeyNav:true,  //可以使用键盘控制上下
	columnLines:true, //展示竖线
	columns:[
//		{xtype: 'rownumberer'},
		{text:"档号",dataIndex:"fileNo",width:70,field:{
			xtype:"textfield"
		}},
		{text:"全宗单位",dataIndex:"organization",width:100,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
//                    meta.style = 'color:red';
                    return value;
        }},
		{text:"全宗号",dataIndex:"archiveNo",width:50,field:{
			xtype:"textfield"
		}},
		{text:"属类号",dataIndex:"genusNo",width:50,field:{
			xtype:"textfield"
		}},
		{text:"类别号",dataIndex:"kindNo",width:50,field:{
			xtype:"textfield",
			width:150
		}},
		{text:"归档年度",dataIndex:"fileYear",width:50,field:{
			xtype:"textfield"
		}},
		{text:"归档部门",dataIndex:"fileDepartment",width:50,field:{
			xtype:"textfield"
		}},
		{text:"保管期限",dataIndex:"retentionPeriod",width:70,field:{
			xtype:"textfield"
		}},
		{text:"目录号",dataIndex:"catalogNo",width:70,field:{
			xtype:"textfield"
		}},
		{text:"案卷号",dataIndex:"dossierNo",width:70,field:{
			xtype:"textfield"
		}},
		{text:"密级",dataIndex:"securityLevel",width:40,field:{
			xtype:"textfield"
		}},
		{text:"案卷题名",dataIndex:"dossierTitle",width:70,field:{
			xtype:"textfield"
		}},
		{text:"起始时间",dataIndex:"beginTime",width:70,field:{
			xtype:"datefield"
		},renderer: Ext.util.Format.dateRenderer('Ymd')},
		{text:"截止时间",dataIndex:"endTime",width:70,field:{
			xtype:"datefield"
		},renderer: Ext.util.Format.dateRenderer('Ymd')},
		{text:"共几件",dataIndex:"piecesCount",width:45,field:{
			xtype:"textfield"
		}},
		{text:"共几页",dataIndex:"pageCount",width:45,field:{
			xtype:"textfield"
		}},
		{text:"著录人",dataIndex:"recorder",width:70,field:{
			xtype:"textfield"
		}},
		{text:"著录时间",dataIndex:"recordDate",width:70,field:{
			xtype:"datefield"
		},renderer: Ext.util.Format.dateRenderer('Ymd')},
		{text:"备注",dataIndex:"remarks",width:170,field:{
			xtype:"textfield"
		}},
		{text:"存放位置",dataIndex:"location",width:70,field:{
			xtype:"textfield"
		}}
//		,{
//			text:"图片",
//	        width:80,
//	        dataIndex: 'photo',
//	        align:"left",
//	        renderer:function(value,cellmeta,record){
//	        	var returnStr = "<INPUT type='button' value='上传' onclick='upload("+record.data.id+");'>";
////	        	return new Ext.button.Button( {
////	        		xtype:'button',text:'上传',ref:'upload',iconCls:'table_insert'
////	        	});
//	        	return returnStr;
//	        }
//		}
//		,
//		{//如果要让操作列不是图标，而是使用文字则这样声明
//			text:'操作',
//			width:100,
////			flex:1,
//			renderer:function(value,cellmeta,record,rowIndex,columnIndex,store){		
//				return "<a onclick='editRow(this)' href='javascript:void(0);' id="+record.data["id"]+" username="+record.data["username"]+" password="+record.data["password"]+">编辑</a>  <a onclick='deleteRow(this)' href='javascript:void(0);' id="+record.data["id"]+">删除</a>"
//			}
//		}
//		,{
//			 xtype:'actioncolumn',
//	            header: "上传",
////	            align: 'center', 
//	            width:35,
//	            id:'household_grid_actioncolumnupload',
//	            items: [{
//	                icon: 'images/icons/upload.png',
////	                text:'上传',
////	                tooltip: '上传',
//	                handler: function(grid, rowIndex, colIndex) {
//	                	var rec = grid.store.getAt(rowIndex);
//	                	this.fireEvent('uploadclick', {  
//	                           record: rec  
//	                       });  
//	                }
//	            }
//	            ]
//		}
//		,{
//			 xtype:'actioncolumn',
//	            header: "查看",
////	            align: 'center', 
//	            width:35,
//	            id:'household_grid_actioncolumnview',
//	            items: [{
//	                icon: 'images/icons/gallery.png',
//	                tooltip: '查看',
//	                text:'查看',
//	                handler: function(grid, rowIndex, colIndex) {
//	                	var rec = grid.store.getAt(rowIndex);
//	                	this.fireEvent('viewclick', {  
//	                           record: rec  
//	                       });  
//	                }
//	            }
//	            ]
//		}
//		,{header: "按钮列", width: 70, dataIndex: '22fileName', renderer: function(){ return '<div class="btn" style="height: 11px; width: 60px"></div>';}} 
//		,
//		{
//			header:"按钮",dataIndex:"button",renderer:function(value){   
//				   var btnId = Ext.id();//获得Ext创建的唯一id   
//				   createGridButton.defer(1, this, [btnId]);   
//				   function createGridButton(){   
//				      return new Ext.Button({text:value}).render(document.body, btnId);   
//				   }   
//				   return "<div id="+btnId+"></div>";   
//				}
//		}
	],
	plugins:[//建立插件
  			Ext.create("Ext.grid.plugin.RowEditing",{//建立编辑单元格插件
  				pluginId:'household_grid_RowEditing',
		    	clicksToMoveEditor:1 //单击次数进行编辑      2次
	    	  	,saveBtnText: '保存',
        	  	cancelBtnText: "取消",
        	  	listeners: {
	                beforeedit : function (editer,e,opts) {//添加编辑前响应的事件
	                    var rows = e.grid.getSelectionModel().getSelection(); //在编辑前获取选中的行
	                    setTimeout(function () { e.grid.getSelectionModel().select(rows); }, 0);//延时执行代码重新勾选编辑前选中的行
	                } ,
	                canceledit : function(editer,e,opts){
//	                	editer.cancelEdit();
//	                	alert(e.record.data.id);
//	                	e.store.load();
	                	if(e.record.data.id==0){
	                		e.store.remove(e.record);
	                	}
	                }
            	}
  			})       
	],
	initComponent:function(){
		this.callParent(arguments);
	}
	
});