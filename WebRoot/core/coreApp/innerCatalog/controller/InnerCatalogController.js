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
				"householdComparedGrid button[ref=return]" : {
					click : function(btn) {
						var layout = btn.up("householdlayout");
						var grid = btn.up("householdlayout").down("household_grid");
						var comparedGrid = grid.up("householdlayout").down("householdComparedGrid");
						comparedGrid.hide();
						grid.show();
						layout.setTitle("户籍档案输入");
					}
				},
				
				/**挂接照片检查*/
				"householdComparedGrid button[ref='checkPhoto']" : {
					click : function(btn) {
						var grid = btn.up("householdComparedGrid");
						var store = grid.getStore();
						//你选择的将要删除的记录
						var records = grid.getSelectionModel().getSelection();
	//					if (!records || records.length <= 0) {
	//						Ext.Msg.alert("提示", "请选择需要删除的数据!");
	//						return;
	//					}
						// 根据id删除多条记录
						var data = [];
						Ext.Array.each(records, function(model) {
							data.push(Ext.JSON.encode(model.get('id')));
						});
						
						
	//					Ext.MessageBox.confirm("重要提示",
	//					"是否要删除吗？",
	//					function(e){
	//						if(e == 'yes'){
						Ext.Ajax.request({
							waitMsg : '正在进行处理,请稍后...', 
							url : "householdCatalog/checkPhoto.do",
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
	//						}
	//					});
					}
				},
				/**householdgrid的查看已比对事件*/
				"household_grid button[ref='viewCompared']" : {
					click : function(_btn){
						var layout = _btn.up("householdlayout");
						var grid = _btn.up("householdlayout").down("household_grid");
						var comparedGrid = grid.up("householdlayout").down("householdComparedGrid");
						comparedGrid.show();
						layout.setTitle("户籍档案已比对数据");
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
	//					Ext.MessageBox.confirm("确认","是否比对选中的数据",
	//					function(e){
	//						if(e == 'yes'){
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
	//						}
	//					});
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
				
				/**productgrid上传按钮事件响应*/
				"actioncolumn#household_grid_actioncolumnupload" : {
					uploadclick : function(p){
						if(!this.uploadWin){
							this.uploadWin = self.createUploadWin();
					        this.uploadWin.on("close",function(obj){
					        	obj.items.getAt(0).onRemove();
					        });
						}
						this.uploadWin.items.getAt(0).post_params = {
							'id':p.record.data.id,
							'uuid':p.record.data.uuid
						};
						this.uploadWin.show();
					}
				},
				
				/**productgrid单元格击事件响应*/
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
				},
				/**productgrid双击事件响应*/
				"product_grid" : {
					itemdblclick : function(_grid, record, item, index, e, eOpts) {
						var form = _grid.up("productlayout").down("productform");
						var grid = form.up("productlayout").down("product_grid");
						//把选择的数据加载到form中去
						var _record = grid.getSelectionModel().getSelection();
						form.loadRecord(_record[0]);
						grid.hide();
						form.down("image").setSrc("images/product/"+_record[0].get("photo"));
						form.show();
					}
				},
				
				
				/**===============================================*/
				"householdform button[ref=return]" : {
					click : function(btn) {
						var form = btn.up("productform");
						var grid = form.up("productlayout").down("product_grid");
						form.hide();
						grid.show();
					}
				},
				
				/**productform的保存按钮事件*/
				"householdform button[ref=save]" : {
					click : function(_btn){
						//1获得form
						var _form = _btn.ownerCt.ownerCt;
						var _grid = _btn.up("productlayout").down("product_grid");
						var id = _form.getForm().findField("id").getValue();
						var url = "";
						if(id == "" || null == id){
							url = "product/add_product.do";
						}else{
							url = "product/update_product.do";
						}
						//alert(url);
						//2.把数据保存到数据库中去
						_form.submit({
							clientValidation : false,
							waitMsg : '正在进行处理,请稍后...', 
							url : url,
							method : 'POST',
							success : function(form, action) {
								var resProductObj = Ext.decode(action.response.responseText);
								if (resProductObj.success) {
									
									if(url != "product/update_product.do"){
										_form.getForm().reset();
									}else{
										_form.down("image").setSrc("images/product/"+resProductObj.photo);
									}
									//3.把这条数据加到grid中
									_grid.getStore().load();
	
									Ext.Msg.alert("提示", resProductObj.msg);
								} else {
									Ext.Msg.alert("提示", resProductObj.msg);
								}
							},
							failure : function(form, action) {
								Ext.Msg.alert("提示","请求处理失败！");
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