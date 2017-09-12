/**
 * 户籍档案列表
 * */
Ext.define("core.innerCatalog.view.InnerCatalogGrid",{
	extend:"Ext.grid.Panel",
	alias:"widget.innerCataloggrid",
	store:"core.innerCatalog.store.InnerCatalogStore",
//	title : "户籍档案输入",
	border:0,
	selModel:{
		selType:"checkboxmodel",
		checkOnly:true,
		listeners: {
            selectionchange: function(sm, selections) {
//            	alert("selectionchange");
//            	alert(sm.up("household_grid"));
            	Ext.getCmp('innerCataloggriddel').setDisabled(selections.length == 0);
            	Ext.getCmp('innerCataloggridcompare').setDisabled(selections.length == 0);
            }
        }
	},
	multiSelect:true,
	frame:true,
	tbar:[
//		{xtype:'button',text:'添加',ref:'add',iconCls:'table_add'},'|',
		{xtype:'button',text:'增加',ref:'insert',iconCls:'table_add'},'|',
		{xtype:'button',text:'删除',ref:'del',iconCls:'table_remove',id:'innerCataloggriddel',disabled: true},'|',
//		{xtype:'button',text:'保存',ref:'save',iconCls:'table_save'},'|',
		{xtype:'button',text:'比对',ref:'compare',iconCls:'table_go',id:'innerCataloggridcompare',disabled: true},
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
		store:'core.innerCatalog.store.InnerCatalogStore',
		dock:'bottom',
		displayInfo:true
	},
	enableKeyNav:true,  //可以使用键盘控制上下
	columnLines:true, //展示竖线
	columns:[
//		{xtype: 'rownumberer'},
		{text:"档号",dataIndex:"fileNo",width:120,field:{
			xtype:"textfield",allowBlank:false
		}, renderer: function (value, meta, record,rowIndex,colIndex,store) {
			if(record.data.errors.indexOf("|fileNo|")>-1){
                meta.style = 'color:green';
			}
                return value;
        }},
		{text:"序号",dataIndex:"serialNo",width:70,field:{
			xtype:"textfield",allowBlank:false
		}, renderer: function (value, meta, record,rowIndex,colIndex,store) {
			if(record.data.errors.indexOf("|serialNo|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"页号",dataIndex:"beginPageNo",width:70,field:{
			xtype:"numberfield"
		}, renderer: function (value, meta, record,rowIndex,colIndex,store) {
			if(record.data.errors.indexOf("|beginPageNo|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"终止页号",dataIndex:"endPageNo",width:70,field:{
			xtype:"numberfield"
		}, renderer: function (value, meta, record,rowIndex,colIndex,store) {
			if(record.data.errors.indexOf("|endPageNo|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"文号",dataIndex:"referenceNo",width:70,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record,rowIndex,colIndex,store) {
			if(record.data.errors.indexOf("|referenceNo|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"题名",dataIndex:"title",width:120,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|title|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"责任者",dataIndex:"personLiable",width:80,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|personLiable|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"文件日期",dataIndex:"documentDate",width:80,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|documentDate|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"全宗号",dataIndex:"archiveNo",width:50,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|archiveNo|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"目录号",dataIndex:"catalogNo",width:70,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|catalogNo|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"密级",dataIndex:"securityLevel",width:40,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|securityLevel|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"是否开放",dataIndex:"isOpen",width:40,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|isOpen|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"案卷号",dataIndex:"dossierNo",width:70,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|dossierNo|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"类别号",dataIndex:"kindNo",width:50,field:{
			xtype:"textfield",
			width:150
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|kindNo|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"属类号",dataIndex:"genusNo",width:50,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|genusNo|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"保管期限",dataIndex:"retentionPeriod",width:70,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|retentionPeriod|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"归档部门",dataIndex:"fileDepartment",width:50,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|fileDepartment|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"归档年度",dataIndex:"fileYear",width:50,field:{
			xtype:"numberfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|fileYear|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"全宗单位",dataIndex:"organization",width:100,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|organization|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"机构号",dataIndex:"organizationNo",width:70,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|organizationNo|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"著录人",dataIndex:"recorder",width:70,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|recorder|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }},
		{text:"著录时间",dataIndex:"recordDate",width:70,field:{
			xtype:"textfield"
		}, renderer: function (value, meta, record) {
			if(record.data.errors.indexOf("|recordDate|")>-1){
                meta.style = 'color:red';
			}
                return value;
        }}
	],
	plugins:[//建立插件
  			Ext.create("Ext.grid.plugin.RowEditing",{//建立编辑单元格插件
  				pluginId:'innerCataloggrid_RowEditing',
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