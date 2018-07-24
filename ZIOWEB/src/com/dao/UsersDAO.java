package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DBManager;

public class UsersDAO {
	Connection conn = null;
	public UsersDAO() {
		DBManager dbm = DBManager.getInstance();
		conn = dbm.getConn();
	}
	
	public boolean Login(String userid,String userpw) {
		String sql = "SELECT USERPW FROM USERS WHERE USERID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("USERPW").equals(userpw)) {
					return true;	
				} else
					return false;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
