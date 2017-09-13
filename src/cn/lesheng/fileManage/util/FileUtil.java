package cn.lesheng.fileManage.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import cn.lesheng.fileManage.model.TFile;

public class FileUtil {
	
	private Map<String,List<String>> directories = new HashMap<String,List<String>>();
	
	public static TFile upload(String realPath,
			MultipartFile fileData, String documentUUID,String newFileName,String path)
			throws Exception {
		File dirPath = new File(realPath+path);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		InputStream inputStream = fileData.getInputStream(); // 获得输入流
		String orginalFilename = fileData.getOriginalFilename();
		String ext = orginalFilename.substring(orginalFilename.lastIndexOf("."));
		String filePath = path + File.separator
				+ UUID.randomUUID().toString()+ext;
		OutputStream outputStream = new FileOutputStream(realPath+filePath);// 获得输出流
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
			// 输出到目标文件夹
			outputStream.write(buffer, 0, bytesRead);
		}
		outputStream.close();
		inputStream.close();
		TFile tFile = new TFile();
		tFile.setFileName(newFileName);
		tFile.setDocumentUUID(documentUUID);
		tFile.setFileSize(fileData.getSize());
		tFile.setFilePath(filePath);
		return tFile;
	}

	public static void checkPhoto(String photoPath)throws Exception {
		File dirPath = new File(photoPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}
	
	public Map<String,List<String>> checkImages(String path)throws Exception{
		File file = new File(path);
		this.ergodicDirectories(file);
		this.directories.remove(file.getName());
		return this.directories;
	}
	
	private void ergodicDirectories(File file)throws Exception{
		if(file.exists()){
			File[] fileArray = file.listFiles();
			List<String> list = new ArrayList<String>();
			if(fileArray!=null&&fileArray.length>0){
				for(File f:fileArray){
					if(f.isFile()){
						list.add(file.getPath()+File.separator+f.getName());
					}else if(f.isDirectory()){
						ergodicDirectories(f);
					}
				}
			}
			this.directories.put(file.getName(), list);
		}
	}

}
