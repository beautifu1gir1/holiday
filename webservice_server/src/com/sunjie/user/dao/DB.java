package com.sunjie.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunjie.file.FileProperties;
import com.sunjie.model.ClassCount;
import com.sunjie.model.Classes;
import com.sunjie.model.Menu;
import com.sunjie.model.StuClasses;

public class DB {
	
	static String imgPath="";
	
	static{
		
		imgPath = FileProperties.getImgPath();
		
	}

	private Connection conn;
	
	public DB(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/njpi", "root", "root");
			
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//查询班级信息
	public List queryClass(){
		
		String sql="select * from t_classes";
		
		List<Classes> lists = new ArrayList<Classes>();
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			ResultSet res = pst.executeQuery();
			
			while (res.next()) {
				
				Classes c = new Classes();
				c.setCid(res.getInt(1));
				c.setCname(res.getString(2));
				
				lists.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (null!=conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return lists;
	}
	
	//查询学生的班级信息
	public List queryClassDatas(){
		
		String sql="SELECT  cname,COUNT(scid)   FROM  t_students " +
				"INNER JOIN t_classes  ON t_students.scid = t_classes.cid GROUP BY cname";
		
		List<StuClasses> lists = new ArrayList<StuClasses>();
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			ResultSet res = pst.executeQuery();
			
			while (res.next()) {
				
				StuClasses c1 = new StuClasses();
				c1.setCname(res.getString(1));
				c1.setScount(res.getInt(2));
				
				lists.add(c1);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (null!=conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return lists;
	}
	
	//查询**学生选修信息
	public String queryStuAndClass(String stuname){
		
		String sql="SELECT COUNT(kid),sname  FROM (SELECT   * FROM  t_students  WHERE  sname=?)" +
				" tmp INNER  JOIN  t_score  ON tmp.sid=t_score.sid  GROUP  BY sname";
		
		String data="";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, stuname);
			ResultSet res = pst.executeQuery();
			
			while (res.next()) {
				
				data = res.getInt(1) + "," + res.getString(2);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (null!=conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
	//验证登录
	public int checkStuLogin(String name,String pwd){
		String sql="SELECT COUNT(*) FROM t_students WHERE sname=? AND spwd=?";
		int count = 0 ;
		
		try {
			
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, name);
			pst.setString(2, pwd);
			
			ResultSet res = pst.executeQuery();
			
			while(res.next()){
				count = res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (null!=conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return count;
	}
	//菜单
	public List queryMenuList(){
		
		String sql="select * from t_menu";
		
		List<Menu> lists = new ArrayList<Menu>();
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			ResultSet res = pst.executeQuery();
			
			while (res.next()) {
				
				Menu m = new Menu();
				m.setTid(res.getInt(1));
				m.setTname(res.getString(2));
				m.setTimg(imgPath + res.getString(3));
				m.setTurl(res.getString(4));
				
				lists.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (null!=conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return lists;
	}
	
	//每个班级的人数（基础柱状图）
	public List queryClassCount(){
		
		String sql="SELECT cname,COUNT(scid) FROM t_students RIGHT JOIN t_classes ON" +
				" t_students.scid = t_classes.cid GROUP BY scid ";
		
		List<ClassCount> list = new ArrayList<ClassCount>();
		
		try
		{
			PreparedStatement pst = conn.prepareStatement(sql);
			
			ResultSet res= pst.executeQuery();
			
			while(res.next()){
				
				ClassCount cc = new ClassCount();
				cc.setCname(res.getString(1));
				cc.setCount(res.getInt(2));
				
				list.add(cc);
			}
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(null!=conn){
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
}
