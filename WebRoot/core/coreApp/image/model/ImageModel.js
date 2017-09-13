/*
 * 文件实体类
 */
 Ext.define("core.image.model.ImageModel",{
 	extend:"Ext.data.Model",
 	fields:[
 		{name:"id",type:"long",srotable:true},
 		{name:"fileNo",type:"string",srotable:true},
 		{name:"imageName",type:"string",srotable:true},
 		{name:"imagePath",type:"string",srotable:true}
 	]
 });