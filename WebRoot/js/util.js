//更换颜色修饰的脚本
function changeColor(obj,color){
	obj.bgColor= color;
}
function validateEquals(IdA,IdB){
	var objA = document.getElementById(IdA);
	var objB = document.getElementById(IdB);
	var span = document.getElementById(IdB+"Msg");
	if(objA.value==objB.value){
		objB.className="success";
		span.className="right";
		return true;
	} else{
		objB.className="fail";
		span.className="wrong";
		return false;
	}
}
function validateEmpty(id){
	var obj = document.getElementById(id);
	var span = document.getElementById(id+"Msg");
	if(obj.value.length>0){
		obj.className="success";
		span.className="right";
		return true;
	} else{
		obj.className="fail";
		span.className="wrong";
		return false;
	}
}
function validateRegex(id,regex){
	var obj = document.getElementById(id);
	var span = document.getElementById(id+"Msg");
	if(regex.test(obj.value)){
		obj.className="success";
		span.className="right";
		return true;
	} else{
		obj.className="fail";
		span.className="wrong";
		return false;
	}
}
//第一个参数是控制全选的参数名称，第二个参数是实现全选功能的组件名称
function selectAll(controlElement,selectElement){
	var instObj = document.all(selectElement);
	if(instObj.length==undefined){	//不是数组
		instObj.checked = doucument.getElementById(controlElement).checked;
	} else {
		for(var i=0; i<instObj.length;i++){
			instObj[i].checked=document.getElementById(controlElement).checked;
		}
	}
}
//确认删除单条数据
function deleteConfirm(){
	return window.confirm("确认删除此数据吗？");
}
//执行数据批量删除,不使用表单提交
function deleteBatchHandle(ele,url){
	var count = 0; 	//保存元素的选中个数
	var Obj = document.all(ele);	
	if(Obj.length == undefined){	//不是数组
		if(Obj.checked){
			count++;
		}
	} else {		//是数组
		for(var i=0;i<Obj.length;i++){
			if(Obj[i].checked){
				count++;
			}
		}
	}
	if(count == 0){		//没有选中数据
		alert("没有选中任何数据！");
		return false;
	}
	if(confirm("确认删除选中的"+count+"条数据吗？")){	//确认删除操作
		for(var i=0;i<Obj.length;i++){
			if(Obj[i].checked){
				url=url+"deptno="+Obj[i].value+"&";
			}
		}
		url = url+"backUrl="+backUrl;
		window.location=url;
	}		
}
//批量删除数据前检查选中情况并确认删除操作
function deleteBatchConfirm(ele){
	var count = 0; 	//保存元素的选中个数
	var Obj = document.all(ele);	
	if(Obj.length == undefined){	//不是数组
		if(Obj.checked){
			count++;
		}
	} else {		//是数组
		for(var i=0;i<Obj.length;i++){
			if(Obj[i].checked){
				count++;
			}
		}
	}
	if(count == 0){		//没有选中数据
		alert("没有选中任何数据！");
		return false;
	}
	return window.confirm("确认删除选中的"+count+"条数据吗？");		//确认删除操作
}
//弹出一个窗口的函数，并提供了控制参数
function openWindow(thisurl, title, width, height) {
	window.open(thisurl, title, "width=" + width + ",height=" + height
			+ ",scrollbars=no,resizable=no");
}
//上次预览的脚本
function preview(file) {
	var prevDiv = document.getElementById('preview');
	if (file.files && file.files[0]) {
		var reader = new FileReader();
		reader.onload = function(evt) {
			prevDiv.innerHTML = '<img src="' + evt.target.result + '" />';
		}
		reader.readAsDataURL(file.files[0]);
	} else {
		prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\''
				+ file.value + '\'"></div>';
	}
}