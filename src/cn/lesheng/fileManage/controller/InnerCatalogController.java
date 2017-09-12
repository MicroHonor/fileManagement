package cn.lesheng.fileManage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.dto.PageMsg;
import cn.lesheng.fileManage.service.IInnerCatalogService;

@Controller
@RequestMapping("/innerCatalog")
public class InnerCatalogController extends BaseController {
	
	@Resource
	private IInnerCatalogService innerCatalogServiceImpl;
	
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<Map<String,Object>> list(PageInfo<Map<String,Object>> page,
			HttpServletRequest request){
		try {
			page = this.innerCatalogServiceImpl.list(page,super.getCurrentUser(request));
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
			msg = innerCatalogServiceImpl.saveOrUpdate(jsonStr,msg,super.getCurrentUser(request));
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
			msg = innerCatalogServiceImpl.delete(ids,msg);
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
			msg = this.innerCatalogServiceImpl.compare(ids);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg(e.toString());
		}
		return msg;
	}

}
