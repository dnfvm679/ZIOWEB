package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.dto.RequestVO;
import com.util.DBManager;

public class RequestDAO {
	private Logger log = Logger.getLogger(this.getClass());
	Connection conn = null;

	public RequestDAO() {
		DBManager dbm = DBManager.getInstance();
		conn = dbm.getConn();
	}

	
	public ArrayList<RequestVO> getRequestList(int page) {
		ArrayList<RequestVO> list = new ArrayList<RequestVO>();
		String sql = "SELECT ROWNUM, B.*  FROM  (SELECT * FROM TBL_REQUEST_INFO WHERE USE_YN='Y' ORDER BY ROWNUM DESC) B WHERE ROWNUM < ?";

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
						RequestVO request = new RequestVO();
						request.setReq_index(rs.getInt("req_index"));
						request.setId(rs.getString("id"));
						request.setTitle(rs.getString("title"));
						request.setContent(rs.getString("content"));
						request.setUser_id(rs.getString("user_id"));
						request.setRequest_date(rs.getDate("request_date"));
						list.add(request);
					}
				} while (rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
			return null;
		}
	} // End of getBoards

	public String getBoardNum(String company_id) {
		String sql = "SELECT LPAD(COUNT(*)+1,4,'0') C FROM TBL_REQUEST_INFO"
				+ " WHERE TO_CHAR(REQUEST_DATE,'yyMMdd') = TO_CHAR(SYSDATE,'yyMMdd')"
				+ " AND ID LIKE ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String company_id_c = "%"+company_id+"%";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company_id_c);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String boardNum = rs.getString("C");
				return boardNum;
			} else {
				String boardNum = "0001";
				return boardNum;
			}
		} catch (Exception e) {
			log.info(e);
		}
		return null;
	}
	
	public int totalRequest() { // Get Reqeust number
		String sql = "SELECT COUNT(*) FROM TBL_REQUEST_INFO WHERE USE_YN='Y'";
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
			return -1;
		}
	} // End of Total Request
	
	public boolean writeRequest(RequestVO request){ // Write Request
		String sql = "INSERT INTO TBL_REQUEST_INFO "
				+ "VALUES(?,?,?,?,SYSDATE,NULL,'Y')";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getId());
			pstmt.setString(2, request.getUser_id());
			pstmt.setString(3, request.getTitle());
			pstmt.setString(4, request.getContent());
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			log.info(e);
			return false;
		}
	} // End of Write Requset
	
	public RequestVO getRequset(String id) {
		RequestVO request = null;
		String sql = "SELECT * FROM TBL_REQUEST_INFO WHERE ID=? AND USE_YN='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				request = new RequestVO();
				request.setId(rs.getString("id"));
				request.setUser_id(rs.getString("user_id"));
				request.setTitle(rs.getString("title"));
				request.setContent(rs.getString("content"));
				request.setRequest_date(rs.getDate("request_date"));
			}else {
				log.info(id + " Request is not exist");
			}
		} catch (Exception e) {
			log.info(e);
		}
		return request;
	}
	
	public boolean updateRequest(RequestVO request) {
		String sql = "UPDATE TBL_REQUEST_INFO SET TITLE=?,CONTENT=? WHERE ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getTitle());
			pstmt.setString(2, request.getContent());
			pstmt.setString(3, request.getId());
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			log.info(e);
			return false;
		}
	}
	
	public boolean deleteRequest(String id) {
		String sql = "UPDATE TBL_REQUEST_INFO SET USE_YN='N' WHERE ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			log.info(e);
			return false;
		}
	}
	
}
