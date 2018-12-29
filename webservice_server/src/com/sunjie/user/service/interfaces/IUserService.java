package com.sunjie.user.service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace="http://sunjie.com/wsdl")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IUserService {

	//��ѯ�༶��Ϣ
	@WebMethod
	public String queryClass();
	
	//��ѯѧ���༶��Ϣ
	@WebMethod
	public String queryStuClass();
	
	//��ѯ**ѧ����ѡ�����
	@WebMethod
	public String queryStu(String name);
	
	//��¼��֤
	@WebMethod
	public int checkLogin(String username,String pwd);
	
	//��ѯ�˵�
	@WebMethod
	public String queryMenu();
	
	//ÿ���༶��������������״ͼ��
	@WebMethod
	public String queryClassCount();
}
