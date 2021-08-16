package model.board.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class qnaDAO {
	private qnaDAO(){}
	private static qnaDAO instance = new qnaDAO();
	public static qnaDAO getinstance() {
		return instance;
	}
	
	public List<qnaVO> Index_List()	{
		List<qnaVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		qnaVO vo = null;
		String query = "select a.*, rownum " + 
				"from (select * from sp_board where board_div='Q' order by regdate desc) a " + 
				"where rownum<=5";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			list = new ArrayList<qnaVO>();
			while(rs.next()) {
				vo = new qnaVO();
				vo.setIdx(rs.getString("idx"));
				vo.setSubject(rs.getString("subject"));
				vo.setBoard_div(rs.getString("board_div"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return list;
	}
	
	public int qna_Listcnt(String key, String search)	{
		int cnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(*) cnt from sp_board where board_div='Q' and reply is null"
				+ (key != null ? " and " + search + " like '%" + key + "%'" : "");
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			return cnt;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return 0;
	}
	
	public List<qnaVO> qna_List(int liststart, int listend, String key, String search)	{
		List<qnaVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		qnaVO vo = null;
		String query = "select b.* from (select rownum rn, a.* "
				+ "from (select * from sp_board where board_div='Q' and reply is null "
				+ (key != null ? " and " + search + " like '%" + key + "%' " : "")
				+ "order by regdate desc) a "
				+ "where rownum<=?) b "
				+ "where b.rn>?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, listend);
			pstmt.setInt(2, liststart);
			rs = pstmt.executeQuery();
			list = new ArrayList<qnaVO>();
			while(rs.next()) {
				vo = new qnaVO();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContents(rs.getString("contents"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getString("readcnt"));
				vo.setQuestions(rs.getString("questions"));
				vo.setAnswer(rs.getString("answer"));
				vo.setId_no(rs.getString("id_no"));
				vo.setBoard_div(rs.getString("board_div"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return list;
	}
	
	public int qna_Insert(qnaVO vo) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into sp_board(idx, name, subject, contents, questions, id_no, board_div)" 
				+" values(sp_board_seq_idx.nextval, ?, ?, ?, ?, ?, 'Q')";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getSubject());
			pstmt.setString(3, vo.getContents());
			pstmt.setString(4, vo.getQuestions());
			pstmt.setString(5, vo.getId_no());
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public qnaVO qna_View(String idx) {
		qnaVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from sp_board where idx=? and board_div='Q'";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new qnaVO();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContents(rs.getString("contents"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getString("readcnt"));
				vo.setQuestions(rs.getString("questions"));
				vo.setAnswer(rs.getString("answer"));
				vo.setId_no(rs.getString("id_no"));
				vo.setBoard_div(rs.getString("board_div"));
				vo.setAnswer_name(rs.getString("answer_name"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return vo;
	}
	
	public void qna_Readcnt(String idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update sp_board set readcnt=readcnt+1 where idx=? and board_div='Q'";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
	}
	
	public int qna_Reply(qnaVO vo) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update sp_board set answer=?, answer_name=? where idx=? and board_div='Q'";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getAnswer());
			pstmt.setString(2, vo.getAnswer_name());
			pstmt.setString(3, vo.getIdx());
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public int qna_Delete(String idx, String id_no) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete from sp_board where idx=? and id_no=? and board_div='Q'";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			pstmt.setString(2, id_no);
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public int qna_Modify(qnaVO vo) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update sp_board set subject=?, contents=?, questions=?"
				+ " where idx=? and name=? and id_no=? and board_div='Q'";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContents());
			pstmt.setString(3, vo.getQuestions());
			pstmt.setString(4, vo.getIdx());
			pstmt.setString(5, vo.getName());
			pstmt.setString(6, vo.getId_no());
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
}