package cn.lesheng.fileManage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.lesheng.fileManage.dto.PageMsg;

@Controller
public class UploadController {
	@RequestMapping("/UploadController/upload")
	public ModelAndView upload( @RequestParam("Filedata") MultipartFile filedata,String fileNo){
//		System.out.println("URL = " + request.getRequestURI());
//		java.util.Enumeration<?> pmNames = request.getParameterNames();
//		System.out.println("===========================================");
//		while (pmNames.hasMoreElements()) {
//			String pmName = (String) pmNames.nextElement();
//			System.out.println(pmName + " = " + request.getParameter(pmName));
//		}
//		System.out.println("===========================================");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("2323");
		return mv;
	}
}
