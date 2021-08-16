package model.about.introduce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class introduceDAO {
	private introduceDAO() {}
	private static introduceDAO instance = new introduceDAO();
	public static introduceDAO getinstance() {
		return instance;
	}
	
	public List<introduceVO> About_introduce() {
		List<introduceVO> list = new ArrayList<introduceVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		introduceVO vo = null;
		String query = "select * from sp_about where about_div='I' order by idx";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new introduceVO();
				vo.setIdx(rs.getString("idx"));
				vo.setAnnouncement(rs.getString("announcement"));
				vo.setJob_skill(rs.getString("job_skill"));
				vo.setEducation(rs.getString("education"));
				vo.setPhoto(rs.getString("photo"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return list;
	}
	
	public int About_annnouncement(String announcement) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update sp_about set announcement=? where idx=110000 and about_div='I'";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, announcement);
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public int About_education(String education) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into sp_about(idx, education, about_div)"
				+ " values(sp_about_seq_idx.nextval, ?, 'I')";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, education);
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public int About_job(String job_skill) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into sp_about(idx, job_skill, about_div)" 
				+" values(sp_about_seq_idx.nextval, ?, 'I')";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, job_skill);
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public int About_photo(String photo) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into sp_about(idx, photo, about_div)" 
				+" values(sp_about_seq_idx.nextval, ?, 'I')";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, photo);
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public introduceVO About_photo_idx() {
		introduceVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select idx, photo from sp_about where photo is not null and about_div='I'";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new introduceVO();
				vo.setIdx(rs.getString("idx"));
				vo.setPhoto(rs.getString("photo"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return vo;
	}
	
	public int About_del(String idx) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete from sp_about where idx=? and about_div='I'";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, idx);
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
}
