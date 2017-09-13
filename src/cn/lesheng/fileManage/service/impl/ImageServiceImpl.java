package cn.lesheng.fileManage.service.impl;


import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.lesheng.fileManage.dao.IImageDao;
import cn.lesheng.fileManage.model.Image;
import cn.lesheng.fileManage.service.IImageService;

@Service
public class ImageServiceImpl implements IImageService {
	
	@Resource
	private IImageDao imageDaoImpl;

	@Override
	public void saveForList(List<String> paths,String fileNo) throws Exception {
		for(String path:paths){
			String imageName = path.substring(path.lastIndexOf(File.separator));
			this.imageDaoImpl.doInsert(new Image(fileNo,imageName,path));
		}
		
	}
}
