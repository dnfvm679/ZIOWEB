package com.util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	private static DBManager instance = new DBManager();
	Context initContext = null;
	Context envContext = null;
	DataSource ds = null;
	Connection conn = null;
	private DBManager() {
		try {
			initContext = new InitialContext();
			envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
			System.out.println("DB연결 성공");
		} catch (Exception e) {
			System.out.println("DB연결 실패");
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static DBManager getInstance() {
		return instance;
	}
}
