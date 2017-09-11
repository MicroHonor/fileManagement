Ext.define("core.household.controller.HouseholdController", {
	extend : "Ext.app.Controller",
	alias:"widget.household_controller",
	init : function() {

		var self = this;
		var uploadWin = null;
		this.control({
			/**householdgrid的添加按钮件*/
			"household_grid button[ref='add']" : {
				click : function(_btn){
					var grid = _btn.up("householdlayout").down("household_grid");
					var form = grid.up("householdlayout").down("householdform");
					form.show();
					grid.hide();
				}
			},
//			/**householdgrid的edit事件*/
//			"household_grid" : {
//			},
//			/**householdgrid的edit事件*/
//			"household_grid plugins[ref='edit']" : {
//				edit : function(){
//					alert("edit");
//				}
//			},
			/**householdlayout的show事件*/
			"householdlayout" : {
				show : function(_this,opt){
					var grid = _this.down("household_grid");
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
			"household_grid button[ref='insert']" : {
				click : function(_btn){
					var grid = _btn.up("household_grid");
					var store = grid.getStore();
//					var rowEditing = Ext.getCmp("household_grid_RowEditing");
					var rowEditing = grid.getPlugin("household_grid_RowEditing");
					rowEditing.cancelEdit();
//	                store.insert(store.getCount(),{});
					var r = Ext.ModelManager.create({
	                    fileYear: 2017,
//	                    email: 'new@sencha-test.com',
	                    beginTime: new Date(),
	                    endTime: new Date(),
	                    piecesCount: 1,
	                    pageCount: 1
                	},'core.household.model.HouseholdModel');
					store.insert(0,r);
					rowEditing.startEdit(0, 0);
				}
			},
//			"household_grid plugin#household_grid_RowEditing" : {
//				beforeedit : function(editer,e,opts){
//					var rows = e.grid.getSelectionModel().getSelection(); //在编辑前获取选中的行
//					alert(rows.length);
//                    setTimeout(function () { e.grid.getSelectionModel().select(rows); }, 0);//延时执行代码重新勾选编辑前选中的行
//				}
//			},
			/**householdgrid的保存按钮事件*/
			"household_grid button[ref='save']" : {
				click : function(_btn){
					var grid = _btn.up("household_grid");
					var store = grid.getStore();
					var mr = store.getModifiedRecords();
					var param=[];
					for(var i=0;i<mr.length;i++){
						param.push(mr[i].data);
					}
					Ext.MessageBox.confirm("确认","是否保存",
					function(e){
						if(e == 'yes'){
							Ext.Ajax.request({
								waitMsg : '正在进行处理,请稍后...', 
								url : "householdCatalog/updateOrSave.do",
//								headers:{
//                            		'Content-Type': 'application/json; charset=utf-8'
//         						},
								params : {
									'listStr' : Ext.encode(param)
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
					});
				}
			},		
			/**householdgrid的比对按钮事件*/
			"household_grid button[ref='compare']" : {
				click : function(_btn){
					var grid = _btn.up("household_grid");
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
						data.push(Ext.JSON.encode(model.get('id')));
					});
//					Ext.MessageBox.confirm("确认","是否比对选中的数据",
//					function(e){
//						if(e == 'yes'){
							Ext.Ajax.request({
								waitMsg : '正在进行处理,请稍后...', 
								url : "householdCatalog/compare.do",
//								headers:{
//                            		'Content-Type': 'application/json; charset=utf-8'
//         						},
								params : {
									ids : data.join(",")
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
			"household_grid button[ref='del']" : {
				click : function(btn){
					var grid = btn.up("household_grid");
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
						data.push(Ext.JSON.encode(model.get('id')));
					});
					
					
					Ext.MessageBox.confirm("重要提示",
					"是否要删除吗？",
					function(e){
						if(e == 'yes'){
							Ext.Ajax.request({
								waitMsg : '正在进行处理,请稍后...', 
								url : "householdCatalog/delete.do",
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
			/**productgrid查看图片事件响应*/
			"actioncolumn#household_grid_actioncolumnview" : {
				viewclick : function(p){
					self.createFileWin(p.record.data.uuid).show();
				}
			},
			
			/**productgrid单元格击事件响应*/
			"household_grid" : {
				edit : function(editor,e){
					if(e.record.data.fileNo==''){
						Ext.Msg.alert("提示","档号不能为空!!");
						return;
					}
					var store = e.grid.getStore();
					var param=[];
					param.push(e.record.data);
					Ext.Ajax.request({
								waitMsg : '正在进行处理,请稍后...', 
								url : "householdCatalog/updateOrSave.do",
//								headers:{
//                            		'Content-Type': 'application/json; charset=utf-8'
//         						},
								params : {
									'listStr' : Ext.encode(param)
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
//				cellclick : function(_grid, td, cellIndex,record,tr,rowIndex,event,eventObj) {
//					var form = _grid.up("productlayout").down("productform");
//					var grid = form.up("productlayout").down("product_grid");
//					//把选择的数据加载到form中去
//					var _record = grid.getSelectionModel().getSelection();
//					form.loadRecord(_record[0]);
//					grid.hide();
//					form.down("image").setSrc("images/product/"+_record[0].get("photo"));
//					form.show();
//					var panel = _grid.up("householdlayout").down("householdUploadGrid");
//					var win = _grid.up("householdlayout").down("householdUploadWin");
//					var fileName=_grid.getHeaderAtIndex(cellIndex).dataIndex;
//					if(fileName=="photo"){
//						var win = self.createUploadWin(record.data.fileNo);
//			            win.show();
//
//				        
//					}
//				}
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
	},
	createUploadWin:function(){
		return Ext.create("Ext.window.Window",{
		 	title: '文件上传',
		    closeAction: 'hide',
		    layout: 'fit',
		    resizable: false,
		    modal: true,
		    items:this.createUploadPanel()
		});
	},
	createUploadPanel:function(){
		return Ext.create("Ext.ux.uploadPanel.UploadPanel",{
	        header: false,
	        addFileBtnText : '选择文件...',
	        uploadBtnText : '上传',
	        removeBtnText : '移除所有',
	        cancelBtnText : '取消上传',
	        file_size_limit : 100,//MB
	        file_types : '*.jpg;*.jpeg;*.png;*.tif',
			file_types_description : '文件格式',
	        width : 750, 
	        height : 300, 
	        closeAction: 'destroy',
	        flash_url : "js/swfupload/swfupload.swf", 
	        flash9_url : "js/swfupload/swfupload_fp9.swf",
	        upload_url : 'householdCatalog/upload.do'
//	        post_params : {'fileNo':fileNo}
		});
	},
	createFileWin : function(uuid){
		var fileStore = Ext.create("core.file.store.FileStore",{
			proxy:{
				type:"ajax",
				url:"file/list.do?doucumentUUID="+uuid,
				reader:{
					type:"json",
					root:"list",
					totalProperty :'totalCount'		
				},
				writer:{
					type:"json"
				}
			}
		});
//		fileStore.on('beforeload', function (store, options) { 
//			 store.baseParams = {
//		 		'fileNo' : fileNo
//			 };
//     	});   
		var panel = Ext.create("core.file.view.FileGrid",{
			store:fileStore,
			bbar:{
				xtype:'pagingtoolbar',
				store:fileStore,
				dock:'bottom',
				displayInfo:true
			}
		});
//		Ext.create("core.file.controller.FileController").init();
		return Ext.create("Ext.window.Window",{
		 	title: '文件列表',
		    closeAction: 'hide',
		    layout: 'fit',
		    resizable: false,
		    modal: true,
		    items:panel
		});
	},
	views : ["core.household.view.HouseholdLayout","core.household.view.HouseholdGrid","core.household.view.HouseholdForm","core.household.view.HouseholdComparedGrid"],
	stores : ["core.household.store.HouseholdStore","core.household.store.HouseholdComparedStore"],
	models : ["core.household.model.HouseholdModel"]
});