/**
 * 户籍档案列表
 * */
Ext.define("core.innerCatalog.view.InnerCatalogComparedGrid",{
	extend:"Ext.grid.Panel",
	alias:"widget.innerCatalogComparedGrid",
	store:"core.innerCatalog.store.InnerCatalogComparedStore",
//	title:"已比对数据",
	border:0,
	selModel:{
		selType:"checkboxmodel"
	},
	multiSelect:true,
	frame:true,
	tbar:[
		{xtype:'button',text:'返回',ref:'return',iconCls:'return'},'|',
		{xtype:'button',text:'挂接档案检查',ref:'checkImages',iconCls:'table_file'},
		
		"->"
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
		store:'core.innerCatalog.store.InnerCatalogComparedStore',
		dock:'bottom',
		displayInfo:true
	},
	enableKeyNav:true,  //可以使用键盘控制上下
	columnLines:true, //展示竖线
	columns:[
		{xtype: 'rownumberer'},
		{text:"档号",dataIndex:"fileNo",width:120,field:{
			xtype:"textfield",allowBlank:false
		}, renderer: function (value, meta, record,rowIndex,colIndex,store) {
			if(record.data.errors.indexOf("|fileNo|")>-1){
                meta.style = 'color:green';
			}
                return value;
        }},
		{text:"序号",dataIndex:"serialNo",width:70},
		{text:"页号",dataIndex:"beginPageNo",width:70,field:{
			xtype:"numberfield"
		}},
		{text:"终止页号",dataIndex:"endPageNo",width:70,field:{
			xtype:"numberfield"
		}},
		{text:"文号",dataIndex:"referenceNo",width:70,field:{
			xtype:"textfield"
		}},
		{text:"题名",dataIndex:"title",width:120,field:{
			xtype:"textfield"
		}},
		{text:"责任者",dataIndex:"personLiable",width:80,field:{
			xtype:"textfield"
		}},
		{text:"文件日期",dataIndex:"documentDate",width:80,field:{
			xtype:"numberfield",format:'Ymd'
		}},
		{text:"全宗号",dataIndex:"archiveNo",width:50,field:{
			xtype:"textfield"
		}},
		{text:"目录号",dataIndex:"catalogNo",width:70,field:{
			xtype:"textfield"
		}},
		{text:"密级",dataIndex:"securityLevel",width:40,field:{
			xtype:"textfield"
		}},
		{text:"是否开放",dataIndex:"isOpen",width:40,field:{
			xtype:"textfield"
		}},
		{text:"案卷号",dataIndex:"dossierNo",width:70,field:{
			xtype:"textfield"
		}},
		{text:"类别号",dataIndex:"kindNo",width:50,field:{
			xtype:"textfield",
			width:150
		}},
		{text:"属类号",dataIndex:"genusNo",width:50,field:{
			xtype:"textfield"
		}},
		{text:"保管期限",dataIndex:"retentionPeriod",width:70,field:{
			xtype:"textfield"
		}},
		{text:"归档部门",dataIndex:"fileDepartment",width:50,field:{
			xtype:"textfield"
		}},
		{text:"归档年度",dataIndex:"fileYear",width:50,field:{
			xtype:"numberfield"
		}},
		{text:"全宗单位",dataIndex:"organization",width:100,field:{
			xtype:"textfield"
		}},
		{text:"机构号",dataIndex:"organizationNo",width:70,field:{
			xtype:"textfield"
		}},
		{text:"著录人",dataIndex:"recorder",width:70,field:{
			xtype:"textfield"
		}},
		{text:"著录时间",dataIndex:"recordDate",width:70},
		{text:"图片数量",dataIndex:"imagesCount",width:70,align:'center'},
		{text:"操作",width:70,align:'center', 
			renderer: function (value, meta, record) {
				if(record.get('imagesCount')>0){
					return "<span style='margin-right:10px;font-size:12px'><a href='#'>查看</a></span>";
				}
				return "";
        }}
	],
//	plugins:[//建立插件
//		      Ext.create("Ext.grid.plugin.CellEditing",{//建立编辑单元格插件
//		    	  clicksToEdit:1//单击次数进行编辑      2次
//		      })       
//	],
	initComponent:function(){
		this.callParent(arguments);
	}
	
});