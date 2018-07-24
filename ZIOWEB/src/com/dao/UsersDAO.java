package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dto.usersVO;
import com.util.DBManager;

public class UsersDAO {
	Connection conn = null;

	public UsersDAO() {
		DBManager dbm = DBManager.getInstance();
		conn = dbm.getConn();
	}

	public boolean Login(String userid, String userpw) { // Login Action
		String sql = "SELECT PW FROM USERS WHERE ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("PW").equals(userpw)) {
					return true;
				} else
					return false;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	} // End of Login

	public ArrayList<usersVO> getUsers(int page) { // Get User list
		ArrayList<usersVO> list = new ArrayList<usersVO>();
		String sql = "SELECT ROWNUM,ID,NAME,POSITION,EMAIL FROM USERS WHERE ROWNUM < ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int startNum = (page - 1) * 10 + 1;
		int endNum = startNum + 9;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, endNum + 1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					if (rs.getInt("ROWNUM") >= startNum && rs.getInt("ROWNUM") <= endNum) {
						usersVO users = new usersVO();
						users.setId(rs.getString("id"));
						users.setEmail(rs.getString("email"));
						users.setPosition(rs.getString("position"));
						users.setName(rs.getString("name"));
						list.add(users);
					}
				} while (rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	} // End of getUsers

	public int totalUser() { // Get User list
		ArrayList<usersVO> list = new ArrayList<usersVO>();
		String sql = "SELECT COUNT(*) FROM USERS";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("COUNT(*)");
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	} // End of getUsers

	public boolean addUser(usersVO user) {
		String sql = "INSERT INTO USERS VALUES(?,'1234',?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPosition());
			pstmt.setString(4, user.getEmail());
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public ArrayList<usersVO> getUsers(String dataType, String keyword, int page) { // Get User list
		ArrayList<usersVO> list = new ArrayList<usersVO>();
		String sql = null;
		if (dataType.equals("name")) {
			sql = "SELECT ROWNUM,ID,NAME,POSITION,EMAIL FROM USERS WHERE ROWNUM < ? AND NAME LIKE ?";
		} else if (dataType.equals("position")) {
			sql = "SELECT ROWNUM,ID,NAME,POSITION,EMAIL FROM USERS WHERE ROWNUM < ? AND POSITION LIKE ?";
		} else if (dataType.equals("id")) {
			sql = "SELECT ROWNUM,ID,NAME,POSITION,EMAIL FROM USERS WHERE ROWNUM < ? AND ID LIKE ?";
		} else {
			return null;
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int startNum = (page - 1) * 10 + 1;
		int endNum = startNum + 9;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, endNum + 1);
			String keyword_s = "%"+keyword+"%";
			pstmt.setString(2, keyword_s);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					if (rs.getInt("ROWNUM") >= startNum && rs.getInt("ROWNUM") <= endNum) {
						usersVO users = new usersVO();
						users.setId(rs.getString("id"));
						users.setEmail(rs.getString("email"));
						users.setPosition(rs.getString("position"));
						users.setName(rs.getString("name"));
						list.add(users);
					}
				} while (rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	} // End of getUsers

	public int totalUser(String dataType, String keyword) { // Get User list
		String sql = null;
		if (dataType.equals("name")) {
			sql = "SELECT COUNT(*) FROM USERS WHERE NAME LIKE ?";
		} else if (dataType.equals("position")) {
			sql = "SELECT COUNT(*) FROM USERS WHERE POSITION LIKE ?";
		} else if (dataType.equals("id")) {
			sql = "SELECT COUNT(*) FROM USERS WHERE ID LIKE ?";
		} else {
			return -1;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			String keyword_s = "%"+keyword+"%";
			pstmt.setString(1, keyword_s);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("COUNT(*)");
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	} // End of getUsers
}
