package com.sunjie.service.application;

import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

import com.sunjie.user.service.impl.UserServiceImpl;

//ʹ��webserviceͳһ�������ݷ��ʽӿ��м��
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class UserDataService {

	public static void main(String[] args) {
		
		System.out.println("----UserDataService---- start------");

		Endpoint.publish("http://127.0.0.1:8200/UserServiceImpl/user",new UserServiceImpl());
		
		System.out.println("UserDataService ���񷢲��ɹ�.....");
	}
	
}
