package cn.lesheng.fileManage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.dto.PageMsg;
import cn.lesheng.fileManage.model.User;
import cn.lesheng.fileManage.service.IBaseCatalogService;

public abstract class AbstractBaseController {
	
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<Map<String,Object>> list(PageInfo<Map<String,Object>> page,
			HttpServletRequest request){
		try {
			page = getService().list(page,getCurrentUser(request));
			page.setSuccess(true);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		page.setSuccess(false);
		return page;
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public PageMsg saveOrUpdate(String jsonStr,PageMsg msg,
			HttpServletRequest request){
		try {
			msg = getService().saveOrUpdate(jsonStr,msg,getCurrentUser(request));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg(e.getMessage());
			e.printStackTrace();
			return msg;
		}
		return msg;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public PageMsg delete(String ids,PageMsg msg){
		try {
			msg = getService().delete(ids,msg);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg(e.getMessage());
			e.printStackTrace();
			return msg;
		}
		return msg;
	}
	
	@RequestMapping("/compare")
	@ResponseBody
	public PageMsg compare(String ids,PageMsg msg){
		try {
			msg = getService().compare(ids);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg(e.toString());
		}
		return msg;
	}
	
	@RequestMapping("/listCompared")
	@ResponseBody
	public PageInfo<Map<String,Object>> listCompared(PageInfo<Map<String,Object>> page,
			HttpServletRequest request){
		try {
			page = getService().listCompared(page);
			page.setSuccess(true);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		page.setSuccess(false);
		return page;
	}
	
	protected User getCurrentUser(HttpServletRequest request){
		return request.getSession().getAttribute("currentUser")!=null?
				(User)request.getSession().getAttribute("currentUser"):null ;
	}
	
	protected abstract IBaseCatalogService getService();
}
