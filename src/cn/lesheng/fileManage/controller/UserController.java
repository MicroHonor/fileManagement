package cn.lesheng.fileManage.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lesheng.fileManage.dto.PageMsg;
import cn.lesheng.fileManage.model.User;
import cn.lesheng.fileManage.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	@ResponseBody
	public PageMsg login(PageMsg msg,User user,HttpSession session){
		try {
			if(this.userService.login(user,session)){
				msg.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			return msg;
		}
		return msg;
	}
	
	@RequestMapping("/findAllUser")
	public void findAllUser(HttpServletResponse response) {
		try {
			response.setHeader("contentType", "text/text;charset=utf-8");
			PrintWriter writer = response.getWriter();
		    List<User> userList = new ArrayList<User>();
			userList = userService.findAll();
		    String startStr = "rows:[";
		    String dataStr = "";
		    int i=0;
			String douhao = ",";
				for(User user : userList) {
					i++;
					dataStr +="{id:"+user.getId()+", " +
								//"name:'"+user.getName()+"'," +
//								"age:"+user.getAge()+"," +
//								//"sex:'"+user.getSex()+"'," +
//								"email:'"+user.getEmail()+"'," +
//								//"birthday:'"+user.getBirthday()+"'," +
//								//"deposit:"+user.getDeposit()+"," +
//								"isIT:'"+user.getIsit()+
								
						     "'}";
					if(i < userList.size()){
						dataStr += douhao;
					}
			 }

			String endStr = "]";
			String resultStr = startStr+dataStr+endStr;
		    writer.write(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
		
	}

}
