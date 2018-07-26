package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.dto.UserVO;
import com.util.DBManager;

public class UsersDAO {
	private Logger log = Logger.getLogger(this.getClass());
	Connection conn = null;

	public UsersDAO() {
		DBManager dbm = DBManager.getInstance();
		conn = dbm.getConn();
	}

	public boolean Login(String userid, String userpw) { // Login Action
		String sql = "SELECT PW FROM USERS WHERE ID=? AND USED='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("PW").equals(userpw)) {
					log.info("=====Login : "+userid);
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

	public ArrayList<UserVO> getUsers(int page) { // Get User list
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		String sql = "SELECT ROWNUM,ID,NAME,POSITION,EMAIL FROM USERS WHERE ROWNUM < ? AND USED='Y'";
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
						UserVO users = new UserVO();
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
		String sql = "SELECT COUNT(*) FROM USERS WHERE USED='Y'";
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

	public boolean addUser(UserVO user) {
		String sql = "INSERT INTO USERS VALUES(?,'1234',?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPosition());
			pstmt.setString(4, user.getEmail());
			pstmt.executeUpdate();
			log.info("=====Create New User : "+user.getId());
			return true;
		} catch (Exception e) {
			return false;
		}
	} // create new user

	public ArrayList<UserVO> getUsers(String dataType, String keyword, int page) { // Get User list
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		String sql = null;
		if (dataType.equals("name")) {
			sql = "SELECT ROWNUM,ID,NAME,POSITION,EMAIL FROM USERS WHERE ROWNUM < ? AND NAME LIKE ? AND USED='Y'";
		} else if (dataType.equals("position")) {
			sql = "SELECT ROWNUM,ID,NAME,POSITION,EMAIL FROM USERS WHERE ROWNUM < ? AND POSITION LIKE ? AND USED='Y'";
		} else if (dataType.equals("id")) {
			sql = "SELECT ROWNUM,ID,NAME,POSITION,EMAIL FROM USERS WHERE ROWNUM < ? AND ID LIKE ? AND USED='Y'";
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
			String keyword_s = "%" + keyword + "%";
			pstmt.setString(2, keyword_s);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					if (rs.getInt("ROWNUM") >= startNum && rs.getInt("ROWNUM") <= endNum) {
						UserVO users = new UserVO();
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
			sql = "SELECT COUNT(*) FROM USERS WHERE NAME LIKE ? AND USED='Y'";
		} else if (dataType.equals("position")) {
			sql = "SELECT COUNT(*) FROM USERS WHERE POSITION LIKE ? AND USED='Y'";
		} else if (dataType.equals("id")) {
			sql = "SELECT COUNT(*) FROM USERS WHERE ID LIKE ? AND USED='Y'";
		} else {
			return -1;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			String keyword_s = "%" + keyword + "%";
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
	} // End of totalUsers

	public UserVO getUser(String userid) { // Get User list
		UserVO user = new UserVO();
		String sql = "SELECT ROWNUM,ID,NAME,POSITION,EMAIL FROM USERS WHERE ID = ? AND USED='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user.setId(rs.getString("id"));
				user.setEmail(rs.getString("email"));
				user.setPosition(rs.getString("position"));
				user.setName(rs.getString("name"));
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	} // End of getUsers

	public boolean updateUser(UserVO user) {
		String sql = "UPDATE USERS SET NAME=?,POSITION=?,EMAIL=? WHERE ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPosition());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getId());
			pstmt.executeUpdate();
			log.info("=====Update "+user.getId()+"'s info");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	} // End of Update User

	public boolean deleteUser(String userid) {
		String sql = "UPDATE USERS SET USED='N' WHERE ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
			log.info("=====Delete User : "+userid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	} // End of Delete User
}
