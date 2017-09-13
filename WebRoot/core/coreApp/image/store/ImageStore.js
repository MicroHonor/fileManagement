/*
 * ClassName 用户数据集
 */
 Ext.define("core.image.store.ImageStore",{
 	extend:'Ext.data.Store',
	model:'core.image.model.ImageModel',
	pageSize:10,//每页显示5条记录
	//autoSync:true,//与服务器同步
	proxy:{
		type:"ajax",
		url:"image/list.do",
		reader:{
			type:"json",
			root:"rows",
			totalProperty :'totalCount'		
		},
		writer:{
			type:"json"
		}
	},
	autoLoad:true	
 });