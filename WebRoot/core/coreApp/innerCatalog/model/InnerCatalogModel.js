/*
 * 户籍档案
 */
 Ext.define("core.innerCatalog.model.InnerCatalogModel",{
 	extend:"Ext.data.Model",
 	fields:[
 		{name:"id",type:"int",srotable:true},
 		{name:"fileNo",type:"string",srotable:true},
 		{name:"organization",type:"string",srotable:true},
 		{name:"archiveNo",type:"string",srotable:true},
 		{name:"genusNo",type:"string",srotable:true},
 		{name:"kindNo",type:"string",srotable:true},
 		{name:"fileYear",type:"int",srotable:true},
 		{name:"fileDepartment",type:"string",srotable:true},
 		{name:"retentionPeriod",type:"string",srotable:true},
 		{name:"catalogNo",type:"string",srotable:true},
 		{name:"dossierNo",type:"string",srotable:true},
 		{name:"securityLevel",type:"string",srotable:true},
 		{name:"dossierTitle",type:"string",srotable:true},
 		{name:"beginTime",type:"date",srotable:true, dateFormat: 'Y-m-d'},
 		{name:"endTime",type:"date",srotable:true, dateFormat: 'Y-m-d'},
 		{name:"piecesCount",type:"int",srotable:true},
 		{name:"pageCount",type:"int",srotable:true},
 		{name:"recorder",type:"string",srotable:true},
 		{name:"recordDate",type:"date",srotable:true, dateFormat: 'Y-m-d'},
 		{name:"remarks",type:"string",srotable:true},
 		{name:"location",type:"string",srotable:true},
 		{name:"uuid",type:"string",srotable:true}
 	]
 });