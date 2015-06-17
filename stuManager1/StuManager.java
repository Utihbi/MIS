/*操纵数据库进行查询
 * 
 */

package com.test.stuManager1;

import java.sql.*;
import java.util.Vector;

import javax.swing.*;

public class StuManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myFrame mf = new myFrame();
	}

}

class myFrame extends JFrame{
	JTable jTable = null;
	JScrollPane jScrollPane= null;
	Vector rowDatas = null,columnName = null;
	mySQLServer ms = null;
	public myFrame() {
		columnName = new Vector<String>();
		rowDatas = new Vector();
		
		columnName.add("部门号");
		columnName.add("部门名");
		columnName.add("坐标");
		
		this.query();
		
		jTable = new JTable(rowDatas,columnName);
		jScrollPane = new JScrollPane(jTable);
		
		this.add(jScrollPane);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		this.setLocation(400, 200);
	}
	
	public void query() {
		
		
		ms = new mySQLServer();
		String sql ="select * from dept";//待改
		ResultSet rs = ms.myQuery(sql);
		
		try {
			while (rs.next()) {
				Vector record = new Vector();
				record.add(rs.getInt(1));
				record.add(rs.getString(2));
				record.add(rs.getString(3));
				rowDatas.addElement(record);
			}
			ms.closeResource();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class mySQLServer{
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
