package com.sunjie.user.service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace="http://sunjie.com/wsdl")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IUserService {

	//查询班级信息
	@WebMethod
	public String queryClass();
	
	//查询学生班级信息
	@WebMethod
	public String queryStuClass();
	
	//查询**学生的选修情况
	@WebMethod
	public String queryStu(String name);
	
	//登录验证
	@WebMethod
	public int checkLogin(String username,String pwd);
	
	//查询菜单
	@WebMethod
	public String queryMenu();
	
	//每个班级的人数（基础柱状图）
	@WebMethod
	public String queryClassCount();
}
