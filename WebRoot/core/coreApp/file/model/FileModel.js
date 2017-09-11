/*
 * 文件实体类
 */
 Ext.define("core.file.model.FileModel",{
 	extend:"Ext.data.Model",
 	fields:[
 		{name:"id",type:"long",srotable:true},
 		{name:"fileName",type:"string",srotable:true},
 		{name:"fileSize",type:"long",srotable:true},
 		{name:"filePath",type:"string",srotable:true}
 	]
 });