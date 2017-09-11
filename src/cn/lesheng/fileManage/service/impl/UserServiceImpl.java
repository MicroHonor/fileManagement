package cn.lesheng.fileManage.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import cn.lesheng.fileManage.dao.IUserDao;
import cn.lesheng.fileManage.model.User;
import cn.lesheng.fileManage.service.UserService;
import cn.lesheng.fileManage.util.MDD5;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private IUserDao userDaoImpl;

	@Override
	public List<User> findAll() throws Exception{
		return userDaoImpl.findAll();
	}

	@Override
	public boolean login(User user, HttpSession session) throws Exception {
		String whereHql = " and username = ? and password= ?";
		Object[] values = {user.getUsername(),MDD5.generatePassword(user.getPassword())};
		List<User> list = this.userDaoImpl.findByConditionWithOrder(whereHql, values, null);
		if(list.size()>0){
			session.setAttribute("currentUser", list.get(0));
			return true;
		}
		return false;
	}

	
	
}
