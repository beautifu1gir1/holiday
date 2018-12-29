package com.sunjie.user.service.impl;

import java.util.List;

import javax.jws.WebService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sunjie.model.ClassCount;
import com.sunjie.model.Classes;
import com.sunjie.model.Menu;
import com.sunjie.model.StuClasses;
import com.sunjie.user.dao.DB;
import com.sunjie.user.service.interfaces.IUserService;

@WebService(portName="userservice",serviceName="UserServiceImpl",
		targetNamespace="http://sunjie.com/wsdl",
		endpointInterface="com.sunjie.user.service.interfaces.IUserService")
public class UserServiceImpl implements IUserService {

	@Override
	public String queryClass() {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl----queryClass----start");
		
		DB db = new DB();
		
		List<Classes> lists = db.queryClass();
		
		System.out.println("-->" + lists.size());
		
		JSONArray array = new JSONArray();
		
		for (Classes c : lists) {
			
			JSONObject obj = new JSONObject();
			obj.put("id", c.getCid());
			obj.put("cname", c.getCname());
			
			array.add(obj);
		}
		System.out.println("JSON-->" + array.toString());
		
		return array.toString();
	}

	@Override
	public String queryStuClass() {
		System.out.println("UserServiceImpl----queryStuClass----start");
		
		DB db = new DB();
		
		List<StuClasses> lists = db.queryClassDatas();
		
		System.out.println("-->" + lists.size());
		
		JSONArray array = new JSONArray();
		
		for (StuClasses stuc : lists) {
			
			JSONObject obj = new JSONObject();
			obj.put("cname", stuc.getCname());
			obj.put("cname", stuc.getScount());
			
			array.add(obj);
		}
		
		System.out.println("JSON-->" + array.toString());
		
		return array.toString();
	}

	@Override
	public String queryStu(String name) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl----queryStu----start");
		
		DB db = new DB();
		
		String data = db.queryStuAndClass(name);
		System.out.println("data-->" + data);
		
		return data;
	}

	@Override
	public int checkLogin(String name, String pwd) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl----queryLogin----start");
		
		DB db = new DB();
		
		int count = db.checkStuLogin(name, pwd);
		System.out.println("count-->" + count);
		
		return count;
	}

	@Override
	public String queryMenu() {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl----queryUserMenu----start");
		
		DB db = new DB();
		List<Menu> lists = db.queryMenuList();
		
		//阿里的json
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(lists);
		System.out.println("strJson-->" + strJson);
		
		return strJson;
	}

	//每个班级的人数（基础柱状图）
	@Override
	public String queryClassCount()
	{
		// TODO Auto-generated method stub
		System.out.println("----------每个班级的人数（基础柱状图）-------");
		
		DB db = new DB();
		List<ClassCount> lists = db.queryClassCount();
		
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(lists);
		System.out.println("strJson-->" + strJson);
		
		return strJson;
	}
	
//	public static void main(String[] args)
//	{
//		UserServiceImpl u = new UserServiceImpl();
//		u.queryClassCount();
//	}
	
}
