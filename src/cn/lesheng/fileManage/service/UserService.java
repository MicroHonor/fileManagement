package cn.lesheng.fileManage.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import cn.lesheng.fileManage.model.User;


public interface UserService {

	List<User> findAll() throws Exception;

	boolean login(User user, HttpSession session)throws Exception;
}
