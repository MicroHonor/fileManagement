package cn.lesheng.fileManage.dao.impl;

import org.springframework.stereotype.Repository;

import cn.lesheng.fileManage.dao.ITFileDao;
import cn.lesheng.fileManage.model.TFile;

@Repository
public class TFileDaoImpl extends CommonDaoImpl<TFile> implements ITFileDao {

	@Override
	public boolean updateFileNo(String oldFileNo, String newFileNo) throws Exception {
		String hql = " update TFile t set t.fileNo =? where t.fileNo = ? ";
		Object[] values = {newFileNo,oldFileNo};
		return super.doUpdateUseHql(hql, values);
	}
	
	
}
