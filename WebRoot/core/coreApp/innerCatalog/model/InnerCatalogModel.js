/*
 * 户籍档案
 */
 Ext.define("core.innerCatalog.model.InnerCatalogModel",{
 	extend:"Ext.data.Model",
 	fields:[
 		{name:"id",type:"string",srotable:true},
 		{name:"fileNo",type:"string",srotable:true},			//档号
 		{name:"serialNo",type:"string",srotable:true},			//序号
 		{name:"beginPageNo",type:"string",srotable:true},		//页号
 		{name:"endPageNo",type:"string",srotable:true},			//终止页号
 		{name:"referenceNo",type:"string",srotable:true},		//文号
 		{name:"title",type:"string",srotable:true},				//题名
 		{name:"personLiable",type:"string",srotable:true},		//责任者
 		{name:"documentDate",type:"string",srotable:true},		//文件日期
 		{name:"archiveNo",type:"string",srotable:true},		//全宗号
 		{name:"catalogNo",type:"string",srotable:true},		//目录号
 		{name:"securityLevel",type:"string",srotable:true},	//密级
 		{name:"isOpen",type:"string",srotable:true},		//是否开放
 		{name:"dossierNo",type:"string",srotable:true},		//案卷号
 		{name:"kindNo",type:"string",srotable:true},		//类别号
 		{name:"genusNo",type:"string",srotable:true},		//属类号
 		{name:"retentionPeriod",type:"string",srotable:true},		//保管期限
 		{name:"fileDepartment",type:"string",srotable:true},		//归档部门
 		{name:"fileYear",type:"string",srotable:true},		//归档年度
 		{name:"organization",type:"string",srotable:true},		//全宗单位
 		{name:"organizationNo",type:"string",srotable:true},		//机构号
 		{name:"recorder",type:"string",srotable:true},			//著录人
 		{name:"recordDate",type:"string",srotable:true},			//著录日期
 		{name:"errors",type:"string",srotable:true},				//比对失败的数据
 		{name:"imagesCount",type:"int",srotable:true}				//图片数量
 	]
 });