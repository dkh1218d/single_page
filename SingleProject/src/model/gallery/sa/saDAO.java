package model.gallery.sa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class saDAO {
	private saDAO() {}
	private static saDAO instance = new saDAO();
	public static saDAO getinstance() {
		return instance;
	}
	
	public int sa_Listcnt(String key, String search)	{
		int cnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(*) cnt from sp_board where board_div='S' and reply is null"
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
	
	public List<saVO> sa_List(int liststart, int listend, String key, String search)	{
		List<saVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		saVO vo = null;
		String query = "select b.* from (select rownum rn, a.* "
				+ "from (select * from sp_board where board_div='S' and reply is null "
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
			list = new ArrayList<saVO>();
			while(rs.next()) {
				vo = new saVO();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContents(rs.getString("contents"));
				vo.setPhoto(rs.getString("photo"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getString("readcnt"));
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
	
	public int sa_Insert(saVO vo) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into sp_board(idx, name, subject, contents, photo, id_no, board_div)" 
				+" values(sp_gallery_seq_idx.nextval, ?, ?, ?, ?, ?, 'S')";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getSubject());
			pstmt.setString(3, vo.getContents());
			pstmt.setString(4, vo.getPhoto());
			pstmt.setString(5, vo.getId_no());
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public saVO sa_View(String idx) {
		saVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from sp_board where idx=? and board_div='S'";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new saVO();
				vo.setName(rs.getString("name"));
				vo.setIdx(rs.getString("idx"));
				vo.setSubject(rs.getString("subject"));
				vo.setContents(rs.getString("contents"));
				vo.setPhoto(rs.getString("photo"));
				vo.setReadcnt(rs.getString("readcnt"));
				vo.setRegdate(rs.getString("regdate"));
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
	
	public void sa_Readcnt(String idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update sp_board set readcnt=readcnt+1 where idx=?";
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
	
	public int sa_Delete(String idx, String id_no) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete from sp_board where idx=? and id_no=?";
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
	
	public String sa_Search_Photo(String idx) {
		String photo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select photo from sp_board where idx=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				photo = rs.getString("photo");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return photo;
	}
	
	public int sa_Modify(saVO vo, String newPhoto) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update sp_board set subject=?, contents=?"
				+ (newPhoto != null ? ", photo='" + vo.getPhoto() +"'" : "")
				+ " where idx=? and name=? and id_no=?";
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
