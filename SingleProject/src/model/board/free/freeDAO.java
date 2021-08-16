package model.board.free;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class freeDAO {
	private freeDAO(){}
	private static freeDAO instance = new freeDAO();
	public static freeDAO getinstance() {
		return instance;
	}
	
	public int free_Listcnt(String key, String search)	{
		int cnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(*) cnt from sp_board where board_div='U' and reply is null"
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
	
	public List<freeVO> free_List(int liststart, int listend, String key, String search)	{
		List<freeVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		freeVO vo = null;
		String query = "select b.* from (select rownum rn, a.* "
				+ "from (select idx, name, subject, contents, regdate, readcnt, board_div, id_no from sp_board"
				+ " where board_div='U' and reply is null "
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
			list = new ArrayList<freeVO>();
			while(rs.next()) {
				vo = new freeVO();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContents(rs.getString("contents"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getString("readcnt"));
				vo.setBoard_div(rs.getString("board_div"));
				vo.setId_no(rs.getString("id_no"));
				vo.setReply_cnt(this.reply_cnt(vo.getIdx()));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return list;
	}
	
	public int free_Insert(freeVO vo) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into sp_board(idx, name, subject, contents, id_no, board_div)" 
				+" values(sp_board_seq_idx.nextval, ?, ?, ?, ?, 'U')";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getSubject());
			pstmt.setString(3, vo.getContents());
			pstmt.setString(4, vo.getId_no());
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public freeVO free_View(String idx) {
		freeVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from sp_board where idx=? and board_div='U'";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new freeVO();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContents(rs.getString("contents"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getString("readcnt"));
				vo.setId_no(rs.getString("id_no"));
				vo.setBoard_div(rs.getString("board_div"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return vo;
	}
	
	public void free_Readcnt(String idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update sp_board set readcnt=readcnt+1 where idx=? and board_div='U'";
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
	
	public int free_Reply(freeVO vo) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into sp_board(idx, name, reply, parent_no, id_no, board_div, depth, parent_re, reply_cnt)"
				+ " values(sp_board_seq_idx.nextval, ?, ?, ?, ?, 'U', 0, sp_board_seq_idx.nextval, 0)";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getReply());
			pstmt.setString(3, vo.getParent_no());
			pstmt.setString(4, vo.getId_no());
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public List<freeVO> reply_List(String idx)	{
		List<freeVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		freeVO vo = null;
		String query = "select * from sp_board where parent_no=? and board_div='U' "
				+ "order by parent_re asc, depth asc, regdate asc";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			list = new ArrayList<freeVO>();
			while(rs.next()) {
				vo = new freeVO();
				vo.setIdx(rs.getString("idx"));
				vo.setReply(rs.getString("reply"));
				vo.setId_no(rs.getString("id_no"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setRefer_id(rs.getString("refer_id"));
				vo.setName(rs.getString("name"));
				vo.setParent_no(rs.getString("parent_no"));
				vo.setParent_re(rs.getString("parent_re"));
				vo.setDepth(rs.getInt("depth"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return list;
	}
	
	public int free_ReReply(freeVO vo, int depth) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into sp_board(idx, name, reply, parent_no, id_no, board_div, refer_id, parent_re, depth)"
				+ " values(sp_board_seq_idx.nextval, ?, ?, ?, ?, 'U', ?, ?, "
				+ (depth<1 ? depth+1 : depth )+")";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getReply());
			pstmt.setString(3, vo.getParent_no());
			pstmt.setString(4, vo.getId_no());
			pstmt.setString(5, vo.getRefer_id());
			pstmt.setString(6, vo.getParent_re());
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public freeVO reply_Search(String idx)	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		freeVO vo = null;
		String query = "select * from sp_board where board_div='U' and idx=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new freeVO();
				vo.setIdx(rs.getString("idx"));
				vo.setReply(rs.getString("reply"));
				vo.setId_no(rs.getString("id_no"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setRefer_id(rs.getString("refer_id"));
				vo.setName(rs.getString("name"));
				vo.setParent_no(rs.getString("parent_no"));
				vo.setParent_re(rs.getString("parent_re"));
				vo.setDepth(rs.getInt("depth"));
				vo.setReply_cnt(rs.getInt("reply_cnt"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return vo;
	}
	
	public int reply_cnt(String idx) {
		int cnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String query = "select count(*) cnt from sp_board where parent_no=? and board_div='U'";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("cnt");
				return cnt;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return 0;
	}
	
	public int free_parent_Delete(String idx, String id_no) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update sp_board set reply='요청에 의해 삭제된 댓글입니다', name='SYSTEM', id_no=0 where idx=? and id_no=? and board_div='U'";
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
	
	public void free_reply_cnt_up(String idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update sp_board set reply_cnt=reply_cnt+1 where idx=? and board_div='U'";
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
	
	public int free_Delete(String idx, String id_no) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete from sp_board where idx=? and id_no=? and board_div='U'";
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
	public void free_DeleteAndreply(String idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete from sp_board where parent_no=? and board_div='U'";
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
	
	public int free_Modify(freeVO vo) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update sp_board set subject=?, contents=?"
				+ " where idx=? and name=? and id_no=? and board_div='U'";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContents());
			pstmt.setString(3, vo.getIdx());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getId_no());
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
}