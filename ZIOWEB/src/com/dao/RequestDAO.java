package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

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
		String sql = "SELECT ROWNUM, B.* " + "FROM  (SELECT RI.*,C.NAME STATE_NAME,U.NAME USER_NAME"
				+ "        FROM TBL_REQUEST_INFO RI,TBL_REQUEST_PROCESS RP,TBL_COMMON_CODE  C,TBL_USER_MASTER U "
				+ "        WHERE RI.ID = RP.ID AND RP.PROCESS_STATE_ID = C.ID AND RI.USER_ID=U.ID AND RI.USE_YN='Y' "
				+ "        ORDER BY REQ_INDEX DESC) B  " + "WHERE ROWNUM < ?";

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
						request.setProcess_state_name(rs.getString("state_name"));
						request.setUser_name(rs.getString("user_name"));
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

	public ArrayList<RequestVO> getRequestList(int page, String id) {
		ArrayList<RequestVO> list = new ArrayList<RequestVO>();
		String sql = "SELECT ROWNUM, B.* " + "FROM  (SELECT RI.*,C.NAME STATE_NAME,U.NAME USER_NAME"
				+ "        FROM TBL_REQUEST_INFO RI,TBL_REQUEST_PROCESS RP,TBL_COMMON_CODE  C,TBL_USER_MASTER U "
				+ "        WHERE RI.ID = RP.ID AND RP.PROCESS_STATE_ID = C.ID AND RI.USER_ID=U.ID AND RI.USER_ID=? "
				+ " 				AND RI.USE_YN='Y' " + " ORDER BY REQ_INDEX DESC) B  " + "WHERE ROWNUM < ?";
		System.out.println(id);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int startNum = (page - 1) * 10 + 1;
		int endNum = startNum + 9;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, endNum + 1);
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
						request.setProcess_state_name(rs.getString("state_name"));
						request.setUser_name(rs.getString("user_name"));
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
				+ " WHERE TO_CHAR(REQUEST_DATE,'yyMMdd') = TO_CHAR(SYSDATE,'yyMMdd')" + " AND ID LIKE ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String company_id_c = "%" + company_id + "%";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company_id_c);
			rs = pstmt.executeQuery();
			if (rs.next()) {
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

	public boolean writeRequest(RequestVO request) { // Write Request
		String sql = "INSERT INTO TBL_REQUEST_INFO " + "VALUES(BOARD_SEQ.NEXTVAL,?,?,?,?,SYSDATE,NULL,'Y')";
		String sql2 = "INSERT INTO TBL_REQUEST_PROCESS(ID,PROCESS_STATE_ID,USE_YN) VALUES (?,'S01','Y')";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getId());
			pstmt.setString(2, request.getUser_id());
			pstmt.setString(3, request.getTitle());
			pstmt.setString(4, request.getContent());
			pstmt.executeUpdate();

			pstmt.close();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, request.getId());
			pstmt.executeUpdate();
			checkFinishCount();
			return true;
		} catch (Exception e) {
			log.info(e);
			return false;
		}
	} // End of Write Requset

	public RequestVO getRequset(String id) {
		RequestVO request = null;
		String sql = "SELECT RI.*  " + "    ,RP.USER_ID MANAGER_ID  " + "    ,RP.PROCESS_STATE_ID  "
				+ "    ,RP.PROCESS_TYPE_ID  " + "    ,RP.PROCESS_FORM_ID  " + "    ,RP.COMPLETE_DATE  "
				+ "    ,RP.PROCESS_CONTENT  " + "    ,RP.PROCESS_HOUR  " + "    ,(SELECT STATE.NAME   "
				+ "        FROM TBL_COMMON_CODE STATE   " + "       WHERE RP.PROCESS_STATE_ID = STATE.ID ) AS S_NAME  "
				+ "    ,(SELECT TYPE.NAME   " + "        FROM TBL_COMMON_CODE TYPE   "
				+ "       WHERE RP.PROCESS_TYPE_ID = TYPE.ID ) AS T_NAME  " + "    ,(SELECT FORM.NAME   "
				+ "        FROM TBL_COMMON_CODE FORM   " + "       WHERE RP.PROCESS_FORM_ID = FORM.ID ) AS F_NAME  "
				+ "    ,RP.PROCESS_FORM_ID  " + "    ,U.NAME USER_NAME   " + "FROM TBL_REQUEST_INFO RI  "
				+ "    , TBL_REQUEST_PROCESS RP  " + "    , TBL_USER_MASTER U  " + "WHERE RI.ID = RP.ID   "
				+ "    AND RI.USER_ID = U.ID   " + "    AND RI.ID=?   " + "    AND RI.USE_YN='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				request = new RequestVO();
				request.setId(rs.getString("id"));
				request.setUser_id(rs.getString("user_id"));
				request.setUser_name(rs.getString("user_name"));
				request.setTitle(rs.getString("title"));
				request.setContent(rs.getString("content"));
				request.setRequest_date(rs.getDate("request_date"));
				request.setProcess_state_id(rs.getString("process_state_id"));
				request.setProcess_state_name(rs.getString("s_name"));
				request.setProcess_form_id(rs.getString("process_form_id"));
				request.setProcess_form_name(rs.getString("f_name"));
				request.setProcess_type_id(rs.getString("process_type_id"));
				request.setProcess_type_name(rs.getString("t_name"));
				request.setManager_id(rs.getString("MANAGER_ID"));
				request.setComplete_date(rs.getString("complete_date"));
				request.setProcess_content(rs.getString("process_content"));
				request.setProcess_hour(rs.getString("process_hour"));
			} else {
				log.info(id + " Request is not exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	public boolean updateProcess(RequestVO r) {
		String sql = "UPDATE TBL_REQUEST_PROCESS "
				+ "SET PROCESS_STATE_ID=?,USER_ID=?,PROCESS_FORM_ID=?,PROCESS_TYPE_ID=? " + "WHERE ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getProcess_state_id());
			pstmt.setString(2, r.getManager_id());
			pstmt.setString(3, r.getProcess_form_id());
			pstmt.setString(4, r.getProcess_type_id());
			pstmt.setString(5, r.getId());
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			log.info(e);
			return false;
		}
	}

	public int getRequestNum() {
		String sql = "SELECT COUNT(*) REQUEST_NUM " + "FROM TBL_REQUEST_INFO "
				+ "WHERE ID LIKE '%'||TO_CHAR(SYSDATE,'yyyyMMdd')||'%'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int result = rs.getInt("request_num");
				pstmt.close();
				rs.close();
				return result;
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
			return -1;
		}
	}

	public int getFinished_RequestNum() {
		String sql = "SELECT COUNT(*) REQUEST_NUM" + "  FROM TBL_REQUEST_INFO RI " + "       ,TBL_REQUEST_PROCESS RP "
				+ " WHERE RI.ID = RP.ID " + "       AND RP.PROCESS_STATE_ID = 'S04' "
				+ "       AND RI.ID LIKE '%'||TO_CHAR(SYSDATE,'yyyyMMdd')||'%'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int result = rs.getInt("request_num");
				pstmt.close();
				rs.close();
				return result;
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
			return -1;
		}
	}

	public int getUnfinished_RequestNum() {
		String sql = "SELECT COUNT(*) REQUEST_NUM" + "  FROM TBL_REQUEST_INFO RI " + "       ,TBL_REQUEST_PROCESS RP "
				+ " WHERE RI.ID = RP.ID " + "       AND RP.PROCESS_STATE_ID != 'S04' "
				+ "       AND RI.ID LIKE '%'||TO_CHAR(SYSDATE,'yyyyMMdd')||'%'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int result = rs.getInt("request_num");
				pstmt.close();
				rs.close();
				return result;
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
			return -1;
		}
	}

	public boolean finishRequest(RequestVO requestvo) {
		String sql = "UPDATE TBL_REQUEST_PROCESS " + "   SET PROCESS_CONTENT=? " + "       ,PROCESS_STATE_ID='S04'"
				+ "       ,PROCESS_HOUR=? " + "       ,COMPLETE_DATE=? " + " WHERE ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, requestvo.getProcess_content());
			pstmt.setString(2, requestvo.getProcess_hour());
			pstmt.setString(3, requestvo.getComplete_date());
			pstmt.setString(4, requestvo.getId());
			pstmt.executeUpdate();
			checkFinishCount();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
			return false;
		}
	}

	public JSONObject getPeriodRequestNum() {
		String sql = " SELECT * " + " FROM (SELECT * " + "       FROM TBL_FINISH_COUNT "
				+ "       ORDER BY COUNT_DATE DESC " + "       ) " + "WHERE ROWNUM<7";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				JSONObject jsonobj = new JSONObject();
				int i = 0;
				do {
					jsonobj.put("finish" + i, rs.getInt("finish_count"));
					jsonobj.put("unfinish" + i, rs.getInt("unfinish_count"));
					i++;
				} while (rs.next());
				pstmt.close();
				rs.close();
				return jsonobj;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
			return null;
		}
	}

	public void checkFinishCount() {
		String sql = "SELECT * FROM TBL_FINISH_COUNT "
				+ "WHERE TO_CHAR(COUNT_DATE,'yyyyMMdd') = TO_CHAR(SYSDATE,'yyyyMMdd')";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				updateFinishCount();
			} else {
				createFinishCount();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}

	public void createFinishCount() {
		String sql = "INSERT INTO TBL_FINISH_COUNT VALUES(SYSDATE,0,0)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();
			updateFinishCount();
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}

	public void updateFinishCount() {
		String sql = "UPDATE TBL_FINISH_COUNT SET FINISH_COUNT=?,UNFINISH_COUNT=? "
				+ "WHERE TO_CHAR(COUNT_DATE,'yyyyMMdd') = TO_CHAR(SYSDATE,'yyyyMMdd')";
		PreparedStatement pstmt = null;
		int finish = getFinished_RequestNum();
		int unfinish = getUnfinished_RequestNum();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, finish);
			pstmt.setInt(2, unfinish);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
}
