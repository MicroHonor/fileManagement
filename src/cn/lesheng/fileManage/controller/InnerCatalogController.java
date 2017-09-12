package cn.lesheng.fileManage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.service.IInnerCatalogService;

@Controller
@RequestMapping("/innerCatalog")
public class InnerCatalogController extends BaseController {
	
	@Resource
	private IInnerCatalogService innerCatalogServiceImpl;
	
	@RequestMapping("/list")
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

}
