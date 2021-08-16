package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBManager;

public class memberDAO {
	private memberDAO() {}
	private static memberDAO instance = new memberDAO();
	public static memberDAO getinstance() {
		return instance;
	}
	
	public int id_overlap(String userid) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "select * from sp_member where userid=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userid);
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public int SocialSecSurityNumber(String securitynumber) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(securitynumber) cnt from sp_member where securitynumber=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, securitynumber);
			rs = pstmt.executeQuery();
			if(rs.next())
				row = rs.getInt("cnt");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return row;
	}
	
	public int member_Insert(memberVO vo) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into sp_member(id_no, userid, passwd, name, nick_name, email, securitynumber) "
				+ "values(sp_member_seq_id_no.nextval, ?, ?, ?, ?, ?, ?)";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getNick_name());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getSecuritynumber());
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public int member_Login(String userid, String passwd) {
		int row = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select passwd from sp_member where userid=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("passwd").equals(passwd))
					row = 1;
					// 로그인 성공
				else
					row = 0;
					// 비밀번호 오류 실패
			}
			// 아이디 오류
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	
	public memberVO member_Search(String userid, String passwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		memberVO vo = null;
		String query = "select * from sp_member where userid=? and passwd=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new memberVO();
				vo.setIdx(rs.getString("id_no"));
				vo.setEmail(rs.getString("email"));
				vo.setName(rs.getString("name"));
				vo.setNick_name(rs.getString("nick_name"));
				vo.setAdmin(rs.getString("admin"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return vo;
	}
	
	public String security_Search(String securitynumber) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userid = null;
		String query = "select userid from sp_member where securitynumber=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, securitynumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				userid = rs.getString("userid");
				return userid;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return userid="Empty Id";
	}
	
	public int member_Modify(memberVO vo) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update sp_member set passwd=?, name=?, nick_name=?, email=? where id_no=? and userid=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getPasswd());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getNick_name());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getIdx());
			pstmt.setString(6, vo.getUserid());
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
	
	public String Email_Search(String securitynumber, String userid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String email = null;
		String query = "select email from sp_member where securitynumber=? and userid=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, securitynumber);
			pstmt.setString(2, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				email = rs.getString("email");
				return email;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt, rs);
		}
		return email="Empty email";
	}
	
	public int member_Reset_Passwd(memberVO vo) {
		int row = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update sp_member set passwd=? where userid=? and securitynumber=?";
		try {
			con = DBManager.getconnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getPasswd());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getSecuritynumber());
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.CloseCSP(con, pstmt);
		}
		return row;
	}
}
