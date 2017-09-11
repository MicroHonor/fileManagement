package cn.lesheng.fileManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.model.TFile;
import cn.lesheng.fileManage.service.ITFileService;

@Controller
@RequestMapping("/file")
public class FileController {
	@Resource
	private ITFileService tFileServiceImpl;
	
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<TFile> list(PageInfo<TFile> page,String doucumentUUID,HttpServletRequest request){
		try {
			page = this.tFileServiceImpl.findPageByDocumentUUID(page,doucumentUUID);
			page.setSuccess(true);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
}
