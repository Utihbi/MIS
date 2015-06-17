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
	// ������--�м�
	JTable jTable = null;
	JScrollPane jScrollPane = null;


	// ������--����
	JPanel jPanelNorth = null;
	JLabel jLabel = null;
	JTextField jTextField = null;
	JButton jButton_query = null;

	// ������--�ϲ�
	JPanel jPanelSouth = null;

	JButton jButton_add = null;
	JButton jButton_update = null;
	JButton jButton_delete = null;

	

	public myFrame() {

		//��ʼ����������
		this.add(this.initNorth(),BorderLayout.NORTH);
		
		// ��ʼ���м䲼��
		this.add(this.initCenter());
		
		//��ʼ���ϲ�����
		this.add(this.initSouth(),BorderLayout.SOUTH);
		
		this.resignerListener();
		//��������
		this.setTitle("MIS--��Ϣ����ϵͳ");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		this.setLocation(400, 200);
	}

	//ע�����
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
		jLabel = new JLabel("�������ѯ��������");
		jTextField = new JTextField(10);
		jButton_query = new JButton("��ѯ");
		
		//jButton_query.addActionListener(this);
		
		
		jPanelNorth = new JPanel();
		jPanelNorth.add(jLabel);
		jPanelNorth.add(jTextField);
		jPanelNorth.add(jButton_query);
		
		return jPanelNorth;
	}

	private JPanel initSouth() {
		
		jButton_add = new JButton("���");
		jButton_update = new JButton("�޸�");
		jButton_delete = new JButton("ɾ��");
		
		
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
			AddDialog ad = new AddDialog(this,"��Ӽ�¼",true); 
			myTableModel mt = new myTableModel();
			jTable.setModel(mt);
			//System.err.println("ko");
		} 
	}
}
