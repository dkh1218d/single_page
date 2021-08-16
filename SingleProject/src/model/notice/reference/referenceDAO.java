package model.notice.reference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class referenceDAO {
	private referenceDAO(){}
	private static referenceDAO instance = new referenceDAO();
	public static referenceDAO getinstance() {
		return instance;
	}
	
	public int reference_Listcnt(String key, String search)	{
		int cnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(*) cnt from sp_board where board_div='R' and reply is null"
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
	
	public List<referenceVO> reference_List(int liststart, int listend, String key, String search)	{
		List<referenceVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		referenceVO vo = null;
		String query = "select b.* from (select rownum rn, a.* "
				+ "from (select * from sp_board where board_div='R' and reply is null "
				+ (key != null ? " and " + search + " like '%" + key + "%' " : "")
				+ "order by important desc, regdate desc) a "
				+ "where rownum<=?) b "
				+ "where b.rn>?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, listend);
			pstmt.setInt(2, liststart);
			rs = pstmt.executeQuery();
			list = new ArrayList<referenceVO>();
			while(rs.next()) {
				vo = new referenceVO();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContents(rs.getString("contents"));
				vo.setFilename(rs.getString("filename"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getString("readcnt"));
				vo.setId_no(rs.getString("id_no"));
				vo.setBoard_div(rs.getString("board_div"));
				vo.setImportant(rs.getString("important"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return list;
	}
	
	public int reference_Insert(referenceVO vo) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into sp_board(idx, name, subject, contents, id_no, board_div, important)" 
				+" values(sp_notice_seq_idx.nextval, ?, ?, ?, ?, 'R', ?)";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getSubject());
			pstmt.setString(3, vo.getContents());
			pstmt.setString(4, vo.getId_no());
			pstmt.setString(5, vo.getImportant());
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public String reference_Search_IDX(referenceVO vo) {
		String idx = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select idx from sp_board where id_no=? and subject=? and contents=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getId_no());
			pstmt.setString(2, vo.getSubject());
			pstmt.setString(3, vo.getContents());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				idx = rs.getString("idx");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return idx;
	}
	
	public void reference_File_Insert(referenceVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into sp_File(idx, parent_board, file_name, file_size)" 
				+" values(sp_file_seq_idx.nextval, ?, ?, ?)";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getIdx());
			pstmt.setString(2, vo.getFile_name());
			pstmt.setString(3, vo.getFile_size());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
	}
	
	
	public referenceVO reference_View(String idx) {
		referenceVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from sp_board where idx=? and board_div='R'";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new referenceVO();
				vo.setName(rs.getString("name"));
				vo.setIdx(rs.getString("idx"));
				vo.setSubject(rs.getString("subject"));
				vo.setContents(rs.getString("contents"));
				vo.setFilename(rs.getString("filename"));
				vo.setReadcnt(rs.getString("readcnt"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setId_no(rs.getString("id_no"));
				vo.setBoard_div(rs.getString("board_div"));
				vo.setImportant(rs.getString("important"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return vo;
	}
	
	public List<referenceVO> reference_File_List(String idx)	{
		List<referenceVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		referenceVO vo = null;
		String query = "select * from sp_file where parent_board=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			list = new ArrayList<referenceVO>();
			while(rs.next()) {
				vo = new referenceVO();
				vo.setIdx(rs.getString("idx"));
				vo.setFile_name(rs.getString("file_name"));
				vo.setFile_size(rs.getString("file_size"));
				vo.setRegdate(rs.getString("regdate"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return list;
	}
	
	public void reference_Readcnt(String idx) {
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
	
	public int reference_Delete(String idx, String id_no) {
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
	
	public String reference_Search_Filename(String idx) {
		String file_name = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select file_name from sp_file where idx=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				file_name = rs.getString("file_name");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return file_name;
	}
	
	public List<referenceVO> reference_Delete_Filename(String idx) {
		List<referenceVO> list = null;
		referenceVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select idx, file_name from sp_file where parent_board=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			list = new ArrayList<referenceVO>();
			while(rs.next()) {
				vo = new referenceVO();
				vo.setFile_name(rs.getString("file_name"));
				vo.setIdx(rs.getString("idx"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return list;
	}
	
	public void reference_Delete_File(String idx, String file_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete from sp_file where idx=? and file_name=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			pstmt.setString(2, file_name);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
	}
	
	public int reference_Modify(referenceVO vo) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update sp_board set subject=?, contents=?, important=? where idx=? and name=? and id_no=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContents());
			pstmt.setString(3, vo.getImportant());
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
