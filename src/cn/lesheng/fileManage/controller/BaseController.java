package cn.lesheng.fileManage.controller;

import javax.servlet.http.HttpServletRequest;

import cn.lesheng.fileManage.model.User;

public class BaseController {
	protected User getCurrentUser(HttpServletRequest request){
		return request.getSession().getAttribute("currentUser")!=null?(User)request.getSession().getAttribute("currentUser"):null ;
	}
}
