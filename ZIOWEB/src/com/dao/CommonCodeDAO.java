package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.dto.CommonCodeVO;
import com.util.DBManager;

public class CommonCodeDAO {
	Connection conn;
	private Logger log = Logger.getLogger(this.getClass());

	public CommonCodeDAO() {
		DBManager dbm = DBManager.getInstance();
		conn = dbm.getConn();
	}

	public ArrayList<CommonCodeVO> getCommonCode(String group_id) {
		String sql = "SELECT * FROM TBL_COMMON_CODE WHERE GROUP_ID = ?";
		ArrayList<CommonCodeVO> list = new ArrayList<CommonCodeVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, group_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					CommonCodeVO comm = new CommonCodeVO();
					comm.setId(rs.getString("id"));
					comm.setName(rs.getString("name"));
					list.add(comm);
				}while(rs.next());
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			log.info(e);
		}
		return null;
	}
	
	public ArrayList<CommonCodeVO> getCommonCode() {
		String sql = "SELECT * FROM TBL_COMMON_CODE";
		ArrayList<CommonCodeVO> list = new ArrayList<CommonCodeVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					CommonCodeVO comm = new CommonCodeVO();
					comm.setId(rs.getString("id"));
					comm.setName(rs.getString("name"));
					comm.setGroup_id(rs.getString("group_id"));
					list.add(comm);
				}while(rs.next());
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			log.info(e);
		}
		return null;
	}
	
}
