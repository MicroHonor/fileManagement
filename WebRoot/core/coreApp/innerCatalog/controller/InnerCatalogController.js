Ext.define("core.innerCatalog.controller.InnerCatalogController", {
	extend : "Ext.app.Controller",
	alias:"widget.innerCatalogController",
	statics: {  
        needInit:true  				//第一次初始化时需要绑定
    },  
	init : function() {

		var self = this;
		var saveOrUpdateUrl = "innerCatalog/saveOrUpdate.do";
		if(core.innerCatalog.controller.InnerCatalogController.needInit){
			this.control({
				/**innerCataloglayout的show事件*/
				"innerCataloglayout" : {
					show : function(_this,opt){
						var grid = _this.down("innerCataloggrid");
						var store = grid.getStore();
						store.load();
					}
				},
				/**返回事件*/
				"innerCatalogComparedGrid button[ref=return]" : {
					click : function(btn) {
						var layout = btn.up("innerCataloglayout");
						var grid = btn.up("innerCataloglayout").down("innerCataloggrid");
						var comparedGrid = grid.up("innerCataloglayout").down("innerCatalogComparedGrid");
						comparedGrid.hide();
						grid.show();
						layout.setTitle("卷内目录输入");
					}
				},
				
				/**挂接照片检查*/
				"innerCatalogComparedGrid button[ref='checkImages']" : {
					click : function(btn) {
						var grid = btn.up("innerCatalogComparedGrid");
						var store = grid.getStore();
						//你选择的将要检查的记录
						var records = grid.getSelectionModel().getSelection();
						var data = [];
						Ext.Array.each(records, function(model) {
							data.push(model.get('id'));
						});
						
						Ext.Ajax.request({
							waitMsg : '正在进行检查,请稍后...', 
							url : "innerCatalog/checkImages.do",
							params : {
								ids : data.join(",")
							},// 根据id删除
							method : "POST",
							timeout : 4000,
							success : function(response, opts) {
								var resObj = Ext.decode(response.responseText);
								if (resObj.success) {
									// 不用查询，从grid中去掉对应的记录就OK了
									store.load();
									Ext.Msg.alert("提示", resObj.msg);
								} else {
									Ext.Msg.alert("提示", resObj.msg);
								}
							}
						});
					}
				},
				/**innerCatalogComparedGrid点击事件*/
				"innerCatalogComparedGrid" : {
					cellclick : function(grid,td,cellIndex,record,tr,rowIndex,e){
						if(e.getTarget().innerHTML=="查看"){
							self.application.getController("core.image.controller.ImageController").init();
							Ext.create("core.image.view.ImageWin").show();
						}
					}
				},
				/**householdgrid的查看已比对事件*/
				"innerCataloggrid button[ref='viewCompared']" : {
					click : function(_btn){
						var layout = _btn.up("innerCataloglayout");
						var grid = _btn.up("innerCataloglayout").down("innerCataloggrid");
						var comparedGrid = grid.up("innerCataloglayout").down("innerCatalogComparedGrid");
						comparedGrid.show();
						layout.setTitle("卷内目录已比对数据");
						grid.hide();
					}
				},
				/**householdgrid的插入按钮事件*/
				"innerCataloggrid button[ref='insert']" : {
					click : function(_btn){
						var grid = _btn.up("innerCataloggrid");
						var store = grid.getStore();
						var rowEditing = grid.getPlugin("innerCataloggrid_RowEditing");
						rowEditing.cancelEdit();
						store.insert(0,{});
						rowEditing.startEdit(0, 0);
					}
				},
				/**householdgrid的比对按钮事件*/
				"innerCataloggrid button[ref='compare']" : {
					click : function(_btn){
						var grid = _btn.up("innerCataloggrid");
						var store = grid.getStore();
						var mr = store.getModifiedRecords();
						if(mr.length>0){
							Ext.MessageBox.alert("提示","请先保存编辑过的数据!!");
							return;
						}
						var records = grid.getSelectionModel().getSelection();
						if (!records || records.length <= 0) {
							Ext.Msg.alert("提示", "请选择需要比对的数据!");
							return;
						}
						var data = [];
						Ext.Array.each(records, function(model) {
//							data.push(Ext.JSON.encode(model.get('id')));
							data.push(model.get('id'));
						});
						Ext.Ajax.request({
							waitMsg : '正在进行处理,请稍后...', 
							url : "innerCatalog/compare.do",
//								headers:{
//                            		'Content-Type': 'application/json; charset=utf-8'
//         						},
							params : {
								ids : data.join(",")
							},
							method : "POST",
							timeout : 4000,
							success : function(response, opts) {
								var resObj = Ext.decode(response.responseText);
								if (resObj.success) {
									// 不用查询，从grid中去掉对应的记录就OK了
									store.load();
									Ext.Msg.alert("提示", resObj.msg);
								} else {
									Ext.Msg.alert("提示", resObj.msg);
								}
							},
							failure : function(){
								alert("操作失败，程序出错!!");
							}
						});
					}
				},					
				/**householdgrid的删除按钮事件*/
				"innerCataloggrid button[ref='del']" : {
					click : function(btn){
						var grid = btn.up("innerCataloggrid");
						var store = grid.getStore();
						//你选择的将要删除的记录
						var records = grid.getSelectionModel().getSelection();
						if (!records || records.length <= 0) {
							Ext.Msg.alert("提示", "请选择需要删除的数据!");
							return;
						}
						// 根据id删除多条记录
						var data = [];
						Ext.Array.each(records, function(model) {
							if(model.get('id')==""){
								store.remove(model);
							}else{
								data.push(model.get('id'));
							}
						});
						if(data.length==0){
							return;
						}
						Ext.MessageBox.confirm("重要提示",
						"是否要删除吗？",
						function(e){
							if(e == 'yes'){
								Ext.Ajax.request({
									waitMsg : '正在进行处理,请稍后...', 
									url : "innerCatalog/delete.do",
									params : {
										ids : data.join(",")
									},// 根据id删除
									method : "POST",
									timeout : 4000,
									success : function(response, opts) {
										var resObj = Ext.decode(response.responseText);
										if (resObj.success) {
											// 不用查询，从grid中去掉对应的记录就OK了
											store.load();
											Ext.Msg.alert("提示", resObj.msg);
										} else {
											Ext.Msg.alert("提示", resObj.msg);
										}
									}
								});
							}
						});
						
						
					}
				},
				
				
				/**innerCataloggrid事件响应*/
				"innerCataloggrid" : {
					edit : function(editor,e){
						if(e.record.data.fileNo==''){
							Ext.Msg.alert("提示","档号不能为空!!");
							return;
						}
						var store = e.grid.getStore();
	//					var param=[];
	//					param.push(e.record.data);
						Ext.Ajax.request({
									waitMsg : '正在进行处理,请稍后...', 
									url : saveOrUpdateUrl,
	//								headers:{
	//                            		'Content-Type': 'application/json; charset=utf-8'
	//         						},
									params : {
										'jsonStr' : Ext.encode(e.record.data)
									},// 根据id删除
	//         						jsonData : {
	//									list : param
	//								},// 根据id删除
									method : "POST",
									timeout : 4000,
									success : function(response, opts) {
										var resObj = Ext.decode(response.responseText);
										if (resObj.success) {
											// 不用查询，从grid中去掉对应的记录就OK了
											store.load();
	//										Ext.Msg.alert("提示", resObj.msg);
										} else {
											Ext.Msg.alert("提示", resObj.msg);
										}
									},
									failure : function(){
										alert("操作失败，程序出错!!");
									}
								});
					}
				}
				
			});
			core.innerCatalog.controller.InnerCatalogController.needInit = false;  
		}
	},
	views : ["core.innerCatalog.view.InnerCatalogLayout","core.innerCatalog.view.InnerCatalogGrid","core.innerCatalog.view.InnerCatalogComparedGrid"],
	stores : ["core.innerCatalog.store.InnerCatalogStore","core.innerCatalog.store.InnerCatalogComparedStore"],
	models : ["core.innerCatalog.model.InnerCatalogModel"]
});