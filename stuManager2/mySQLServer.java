package com.test.stuManager2;

import java.sql.*;
import java.util.Vector;

public class mySQLServer {
	String driver ="com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String url = "jdbc:microsoft:sqlserver://localhost:1434;databaseName = LiangShanHeros";
	String user ="sa";
	String pwd = "xiaoling";
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	public mySQLServer () {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean myInsert(Vector<String> data) {
		try {
			String sql = "insert into dept values(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(data.get(0)));
			ps.setString(2, data.get(1));
			ps.setString(3, data.get(2));
			
			int i= ps.executeUpdate();
			if (1 == i) {
				//System.err.println("kooo");
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeResource();
			return false;
		}
	}
	
	public ResultSet myQuery(String sql) {
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void closeResource () {
		try {
			if (null !=  rs) {
				rs.close();
			}
			if (null !=  ps) {
				ps.close();
			}
			if (null !=  con) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
