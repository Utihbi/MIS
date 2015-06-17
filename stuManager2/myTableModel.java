package com.test.stuManager2;

import java.sql.*;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

class myTableModel extends AbstractTableModel{

	Vector rowDatas = null, columnName = null;
	// 数据库实例
    mySQLServer ms = null;
    
    public myTableModel (String sql) {
		this.initRowAndCol();
		this.query(sql);
	}
    
	public myTableModel () {
		
		this.initRowAndCol();	
		this.query();
	}
	
	private void initRowAndCol () {
		columnName = new Vector<String>();
		rowDatas = new Vector();

		columnName.add("部门号");
		columnName.add("部门名");
		columnName.add("坐标");
	}
	
	public void query(String ... sql) {

		ms = new mySQLServer();
		String sqllocal = "select * from dept";// 待改
		
		if (0 != sql.length) {
			sqllocal  = sql[0];
		}
		
		
		ResultSet rs = ms.myQuery(sqllocal);

		rowDatas.clear();
		
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
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnName.size();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		//return super.getColumnName(column);
		return (String)columnName.get(column);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowDatas.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowDatas.get(row)).get(col);
	}

}
