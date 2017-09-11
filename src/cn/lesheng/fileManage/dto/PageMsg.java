package cn.lesheng.fileManage.dto;

public class PageMsg {
	public String msg; 				//返回信息
	public boolean success = false; 	//成功标识

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
