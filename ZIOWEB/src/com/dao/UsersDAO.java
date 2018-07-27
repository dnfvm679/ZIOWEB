package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.dto.CompanyVO;
import com.dto.UserVO;
import com.util.DBManager;

public class UsersDAO {
	private Logger log = Logger.getLogger(this.getClass());
	Connection conn = null;

	public UsersDAO() {
		DBManager dbm = DBManager.getInstance();
		conn = dbm.getConn();
	}

	public String Login(String userid, String userpw) { // Login Action
		String sql = "SELECT PW,NAME FROM TBL_USER_MASTER WHERE ID=? AND USE_YN='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("PW").equals(userpw)) {
					log.info("=====Login : " + userid);
					return rs.getString("name");
				} else
					return null;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	} // End of Login

	public ArrayList<UserVO> getUsers(int page) { // Get User list
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		String sql = "SELECT ROWNUM,COMPANY_ID,ID,NAME,POSITION,EMAIL FROM TBL_USER_MASTER WHERE ROWNUM < ? AND USE_YN='Y'";
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
						users.setCompany_id(rs.getString("company_id"));
						users.setId(rs.getString("id"));
						users.setName(rs.getString("name"));
						users.setPosition(rs.getString("position"));
						users.setEmail(rs.getString("email"));
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
		String sql = "SELECT COUNT(*) FROM TBL_USER_MASTER WHERE USE_YN='Y'";
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
		// TBL_USER_MASTER Column order
		// ID COMPANY_ID NAME POSITION TEAM TEL PHONE EMAIL ADDRESS NAMECARD ETC USE_YN
		// PW
		String sql = "INSERT INTO TBL_USER_MASTER VALUES(?,'ZIONEX0000',?,?,?,?,?,?,?,?,null,null,'Y')";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getCompany_id());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getPosition());
			pstmt.setString(5, user.getTeam());
			pstmt.setString(6, user.getTel());
			pstmt.setString(7, user.getPhone());
			pstmt.setString(8, user.getEmail());
			pstmt.setString(9, user.getAddress());
			pstmt.executeUpdate();
			log.info("=====Create New User : " + user.getId());
			return true;
		} catch (Exception e) {
			log.info(e);
			return false;
		}
	} // create new user

	public ArrayList<UserVO> getUsers(String dataType, String keyword, int page) { // Get User list
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		String sql = null;
		switch (dataType) {
		case "company_id":
			sql = "SELECT ROWNUM,COMPANY_ID,ID,NAME,POSITION,EMAIL FROM TBL_USER_MASTER WHERE ROWNUM < ? AND COMPANY_ID LIKE ? AND USE_YN='Y'";
			break;
		case "id":
			sql = "SELECT ROWNUM,COMPANY_ID,ID,NAME,POSITION,EMAIL FROM TBL_USER_MASTER WHERE ROWNUM < ? AND ID LIKE ? AND USE_YN='Y'";
			break;
		case "name":
			sql = "SELECT ROWNUM,COMPANY_ID,ID,NAME,POSITION,EMAIL FROM TBL_USER_MASTER WHERE ROWNUM < ? AND NAME LIKE ? AND USE_YN='Y'";
			break;
		case "position":
			sql = "SELECT ROWNUM,COMPANY_ID,ID,NAME,POSITION,EMAIL FROM TBL_USER_MASTER WHERE ROWNUM < ? AND POSITION LIKE ? AND USE_YN='Y'";
			break;
		default:
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
						users.setCompany_id(rs.getString("company_id"));
						users.setId(rs.getString("id"));
						users.setName(rs.getString("name"));
						users.setPosition(rs.getString("position"));
						users.setEmail(rs.getString("email"));
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
		switch (dataType) {
		case "company_id":
			sql = "SELECT COUNT(*) FROM TBL_USER_MASTER WHERE COMPANY_ID LIKE ? AND USE_YN='Y'";
			break;
		case "id":
			sql = "SELECT COUNT(*) FROM TBL_USER_MASTER WHERE ID LIKE ? AND USE_YN='Y'";
			break;
		case "name":
			sql = "SELECT COUNT(*) FROM TBL_USER_MASTER WHERE NAME LIKE ? AND USE_YN='Y'";
			break;
		case "position":
			sql = "SELECT COUNT(*) FROM TBL_USER_MASTER WHERE POSITION LIKE ? AND USE_YN='Y'";
			break;
		default:
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
		String sql = "SELECT ROWNUM,ID,COMPANY_ID,NAME,POSITION,TEAM,TEL,PHONE,EMAIL,ADDRESS FROM TBL_USER_MASTER WHERE ID = ? AND USE_YN='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user.setCompany_id(rs.getString("company_id"));
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				
				if (rs.getString("position") != null) {
					user.setPosition(rs.getString("position"));
				} else {
					user.setPosition("null");
				}
				if (rs.getString("email") != null) {
					user.setEmail(rs.getString("email"));
				} else {
					user.setEmail("null");
				}
				
				if (rs.getString("team") != null) {
					user.setTeam(rs.getString("team"));
				} else {
					user.setTeam("null");
				}
				
				if (rs.getString("tel") != null) {
					user.setTel(rs.getString("tel"));
				} else {
					user.setTel("null");
				}
				
				if (rs.getString("phone") != null) {
					user.setPhone(rs.getString("phone"));
				} else {
					user.setPhone("null");
				}
				
				if (rs.getString("address") != null) {
					user.setAddress(rs.getString("address"));
				} else {
					user.setAddress("null");
				}
				
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
		String sql = "UPDATE TBL_USER_MASTER SET ID=?,COMPANY_ID=?,NAME=?,POSITION=?,TEAM=?,TEL=?,PHONE=?,EMAIL=?,ADDRESS=? WHERE ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getCompany_id());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getPosition());
			pstmt.setString(5, user.getTeam());
			pstmt.setString(6, user.getTel());
			pstmt.setString(7, user.getPhone());
			pstmt.setString(8, user.getEmail());
			pstmt.setString(9, user.getAddress());
			pstmt.setString(10, user.getId());
			pstmt.executeUpdate();
			log.info("=====Update " + user.getId() + "'s info");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	} // End of Update User

	public ArrayList<CompanyVO> getCompanyList() { // Get All Client Company list
		String sql = "SELECT ID,NAME FROM TBL_COMPANY_INFO WHERE USE_YN='Y'";
		ArrayList<CompanyVO> list = new ArrayList<CompanyVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					CompanyVO company = new CompanyVO();
					company.setId(rs.getString("id"));
					company.setName(rs.getString("name"));
					list.add(company);
				} while (rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.info(e);
			return null;
		}
	}
	
	public String getCompanyID(String userid) { // Get All Client Company list
		String sql = "SELECT COMPANY_ID FROM TBL_USER_MASTER WHERE ID=? AND USE_YN='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
					String company_id = rs.getString("company_id");
					return company_id;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.info(e);
			return null;
		}
	}

	public String getUserID() { // Get new user id
		String sql = "SELECT LPAD((SELECT COUNT(*) FROM TBL_USER_MASTER),5,'0') USER_ID FROM DUAL";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String userid = rs.getString("USER_ID");
				return userid;
			}
			return null;
		} catch (Exception e) {
			log.debug(e);
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteUser(String userid) {
		String sql = "UPDATE TBL_USER_MASTER SET USE_YN='N' WHERE ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
			log.info("=====Delete User : " + userid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	} // End of Delete User
}
