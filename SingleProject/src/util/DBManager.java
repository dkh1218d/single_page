package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	public static Connection getconnection() {
		Connection con = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			con = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void CloseCSP(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(con!=null) con.close();
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void CloseCSP(Connection con, PreparedStatement pstmt) {
		try {
			if(con!=null) con.close();
			if(pstmt!=null) pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
