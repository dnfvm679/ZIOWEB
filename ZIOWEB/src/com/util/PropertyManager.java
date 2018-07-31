package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
	private static PropertyManager Instance = new PropertyManager(); 
	Properties pro = null;
	private PropertyManager() {
		//Properties 파일 객체 생성
		pro = new Properties(); 
		try {
			//Properties 파일에 대한 입력 스트림 설정
			InputStream is = new FileInputStream("/test1.properties");
			//test1.Properties 파일 읽기 
			pro.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static PropertyManager getInstance() {
		return Instance;
	}
	
	public Properties getProperties() {
		return pro;
	}
	
	public static void main(String[] args) {
		new PropertyManager();
	}
}
