package cn.lesheng.fileManage.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.dto.PageMsg;
import cn.lesheng.fileManage.model.HouseholdCatalog;
import cn.lesheng.fileManage.model.TFile;
import cn.lesheng.fileManage.service.IHouseholdCatalogService;
import cn.lesheng.fileManage.service.ITFileService;
import cn.lesheng.fileManage.util.FileUtil;

@Controller
@RequestMapping("/householdCatalog")
public class HouseholdCatalogController extends BaseController{
	
	@Resource
	private IHouseholdCatalogService householdCatalogService;
	@Resource
	private ITFileService tFileServiceImpl;
	
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<HouseholdCatalog> list(PageInfo<HouseholdCatalog> page,
			HttpServletRequest request){
		try {
			page = this.householdCatalogService.list(page,super.getCurrentUser(request));
			page.setSuccess(true);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		page.setSuccess(false);
		return page;
	}
	@RequestMapping("/listCompared")
	@ResponseBody
	public PageInfo<HouseholdCatalog> listCompared(PageInfo<HouseholdCatalog> page,
			HttpServletRequest request){
		try {
			page = this.householdCatalogService.listCompared(page);
			page.setSuccess(true);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		page.setSuccess(false);
		return page;
	}
	
	@RequestMapping("/updateOrSave")
	@ResponseBody
	public PageMsg updateOrSave(String listStr,PageMsg msg,
			HttpServletRequest request){
		try {
			msg = householdCatalogService.saveOrUpdate(listStr,msg,super.getCurrentUser(request));
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
			msg = householdCatalogService.delete(ids,msg);
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
			msg = this.householdCatalogService.compare(ids);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg(e.toString());
		}
		return msg;
	}
	
	@RequestMapping("/checkPhoto")
	@ResponseBody
	public PageMsg checkPhoto(String ids,PageMsg msg){
		try {
			msg = this.householdCatalogService.checkPhoto(ids);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg(e.toString());
		}
		return msg;
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	public String upload( @RequestParam("fileData") MultipartFile fileData,HouseholdCatalog entity
			,String newFileName,HttpServletRequest request,HttpServletResponse response){
		Map<String,String> map = new HashMap<String,String>();
		String contextRealPath = request.getServletContext().getRealPath("/");
		try {
			String uuid = entity.getUuid();
			if(StringUtils.isBlank(uuid)){
				uuid = this.householdCatalogService.updateUUID(entity);
			}
			TFile tFile = FileUtil.upload(contextRealPath,fileData,
					uuid,newFileName,"upload"+File.separator+"householdCatalog");
			if(tFileServiceImpl.save(tFile)){
				map.put("success", "true");		//设置成功标记	
				map.put("url", tFile.getFilePath());	//设置文件路径
				return JSONObject.fromObject(map).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success","false");
			return JSONObject.fromObject(map).toString();
		}
		return JSONObject.fromObject(map).toString();
	}
}
