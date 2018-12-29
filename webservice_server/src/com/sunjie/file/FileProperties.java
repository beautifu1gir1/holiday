package com.sunjie.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileProperties {

	public static String getImgPath(){
		
		Properties p = new Properties();
		
		try {
			
			p.load(new FileInputStream("./src/urlpath.properties"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p.getProperty("imgurl");
	}
	
}
