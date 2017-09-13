package cn.lesheng.fileManage.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lesheng.fileManage.dto.PageMsg;
import cn.lesheng.fileManage.service.IBaseCatalogService;
import cn.lesheng.fileManage.service.IInnerCatalogService;

@Controller
@RequestMapping("/innerCatalog")
public class InnerCatalogController extends AbstractBaseController {
	
	@Resource
	private IInnerCatalogService innerCatalogServiceImpl;
	
	@RequestMapping("/checkImages")
	@ResponseBody
	public PageMsg checkImages(String ids,PageMsg msg){
		try {
			msg = innerCatalogServiceImpl.checkImages(ids, msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg(e.toString());
		}
		return msg;
	}
	
	
	@Override
	protected IBaseCatalogService getService() {
		return this.innerCatalogServiceImpl;
	}

}
