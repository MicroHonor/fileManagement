/*
 * ClassName 用户数据集
 */
 Ext.define("core.file.store.FileStore",{
 	extend:'Ext.data.Store',
	model:'core.file.model.FileModel',
	pageSize:10,//每页显示5条记录
	//autoSync:true,//与服务器同步
	proxy:{
		type:"ajax",
		url:"file/list.do",
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