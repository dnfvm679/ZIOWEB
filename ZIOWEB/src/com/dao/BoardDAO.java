package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.dto.BoardVO;
import com.util.DBManager;

public class BoardDAO {
	private Logger log = Logger.getLogger(this.getClass());
	Connection conn = null;

	public BoardDAO() {
		DBManager dbm = DBManager.getInstance();
		conn = dbm.getConn();
	}

	public ArrayList<BoardVO> getBoards(int page) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		String sql = "SELECT ROWNUM, B.* " + "FROM " + "( " + "SELECT * FROM BOARD WHERE USED='Y' "
				+ "ORDER BY BOARDNUM DESC) B " + "WHERE ROWNUM < ?";

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
						BoardVO board = new BoardVO();
						board.setBoardnum(rs.getInt("BOARDNUM"));
						board.setTitle(rs.getString("title"));
						board.setContent(rs.getString("content"));
						board.setWriter(rs.getString("writer"));
						board.setCreatedate(rs.getDate("createdate"));
						board.setStatus(rs.getString("status"));
						list.add(board);
					}
				} while (rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	} // End of getBoards

	public int totalBoard() { // Get User list
		String sql = "SELECT COUNT(*) FROM BOARD WHERE USED='Y'";
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
	} // End of Total Boards

	public boolean writeIssue(BoardVO board) {
		String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL,?,?,?,'N',SYSDATE,SYSDATE,'Y')";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getWriter());
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	} // create new issue

	public BoardVO getBoard(int boardnum) { // Get User list
		BoardVO board = new BoardVO();
		String sql = "SELECT TITLE,CONTENT,WRITER,CREATEDATE,STATUS FROM BOARD WHERE BOARDNUM = ? AND USED='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setCreatedate(rs.getDate("CREATEDATE"));
				board.setStatus(rs.getString("status"));
				return board;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	} // End of getUsers

	public boolean updateBoard(BoardVO board) { // Get User list
		String sql = "UPDATE BOARD SET TITLE=?,CONTENT=? WHERE BOARDNUM=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getBoardnum());
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	} // End of getUsers

	public boolean deleteIssue(int boardnum) {
		String sql = "UPDATE BOARD SET USED='N' WHERE BOARDNUM=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	} // End of Delete User
}
