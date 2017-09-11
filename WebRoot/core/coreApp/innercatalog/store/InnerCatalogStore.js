/*
 * 户籍档案
 */
 Ext.define("core.household.store.InnerCatalogStore",{
 	extend:'Ext.data.Store',
	model:'core.household.model.InnerCatalogModel',
	buffered: true,
    pageSize: 100,
	//autoSync:true,//与服务器同步
	proxy:{
		type:"ajax",
		//url:"./householdCatalog/list.do",
		api:{
			read:"./innerCatalog/list.do"
		},
		reader:{
			type:"json",
			root:"list",
			totalProperty :'totalCount'		
		},
		writer:{
			type:"json"
		}
	},
	autoLoad:true	
 });