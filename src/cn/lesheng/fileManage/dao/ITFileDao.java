package cn.lesheng.fileManage.dao;

import cn.lesheng.fileManage.model.TFile;

public interface ITFileDao extends ICommonDao<TFile> {

	boolean updateFileNo(String oldFileNo, String newFileNo) throws Exception;


}
