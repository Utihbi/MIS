package com.test.stuManager2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;

import com.test.gui.resigner;

public class StuManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myFrame mf = new myFrame();
	}

}

class myFrame extends JFrame implements ActionListener{
	// 面板组件--中间
	JTable jTable = null;
	JScrollPane jScrollPane = null;


	// 面板组件--北部
	JPanel jPanelNorth = null;
	JLabel jLabel = null;
	JTextField jTextField = null;
	JButton jButton_query = null;

	// 面板组件--南部
	JPanel jPanelSouth = null;

	JButton jButton_add = null;
	JButton jButton_update = null;
	JButton jButton_delete = null;

	

	public myFrame() {

		//初始化北部布局
		this.add(this.initNorth(),BorderLayout.NORTH);
		
		// 初始化中间布局
		this.add(this.initCenter());
		
		//初始化南部布局
		this.add(this.initSouth(),BorderLayout.SOUTH);
		
		this.resignerListener();
		//基本参数
		this.setTitle("MIS--信息管理系统");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		this.setLocation(400, 200);
	}

	//注册监听
	private void resignerListener () {
		jButton_add.addActionListener(this);
		jButton_add.setActionCommand("add");
		
		jButton_delete.addActionListener(this);
		jButton_delete.setActionCommand("delete");
		
		jButton_update.addActionListener(this);
		jButton_update.setActionCommand("update");
		
		jButton_query.addActionListener(this);
		jButton_query.setActionCommand("query");
	}
	
	private JPanel initNorth() {
		jLabel = new JLabel("请输入查询人姓名：");
		jTextField = new JTextField(10);
		jButton_query = new JButton("查询");
		
		//jButton_query.addActionListener(this);
		
		
		jPanelNorth = new JPanel();
		jPanelNorth.add(jLabel);
		jPanelNorth.add(jTextField);
		jPanelNorth.add(jButton_query);
		
		return jPanelNorth;
	}

	private JPanel initSouth() {
		
		jButton_add = new JButton("添加");
		jButton_update = new JButton("修改");
		jButton_delete = new JButton("删除");
		
		
		jPanelSouth = new JPanel();
		jPanelSouth.add(jButton_add);
		jPanelSouth.add(jButton_update);
		jPanelSouth.add(jButton_delete);
		
		return jPanelSouth;
	}

	private JScrollPane initCenter() {
		
		myTableModel mt = new myTableModel();
		jTable = new JTable(mt);
		jScrollPane = new JScrollPane(jTable);

		return jScrollPane;
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("query".equals(e.getActionCommand())) {
			String sql = "select * from dept where dName like '"+this.jTextField.getText().trim()+"%'";
			//this.query(sql);
			myTableModel mt = new myTableModel(sql);
			jTable.setModel(mt);
			//System.err.println("ko");
		}
		else if ("add".equals(e.getActionCommand())) {
			//String sql = "select * from dept where dName like '"+this.jTextField.getText().trim()+"%'";
			//this.query(sql);
			AddDialog ad = new AddDialog(this,"添加记录",true); 
			myTableModel mt = new myTableModel();
			jTable.setModel(mt);
			//System.err.println("ko");
		} 
	}
}
