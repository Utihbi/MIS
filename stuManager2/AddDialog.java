package com.test.stuManager2;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

class AddDialog extends JDialog implements ActionListener{

	JLabel jLDeptNo = null,jLDName = null,jLLoc = null;
	JTextField jTDeptNo = null,jTDName = null,jTLoc = null;
	JButton jbAdd = null,jbCancel = null;
	
	
	public AddDialog (Frame fatherFrame,String title,boolean pattern)  {
		super(fatherFrame, title, pattern);
		
		jLDeptNo = new JLabel("部门号");
		jLDName = new JLabel("部门名");
		jLLoc = new JLabel("坐标");
		
		jTDeptNo = new JTextField(10);
		jTDName = new JTextField(10);
		jTLoc = new JTextField(10);
		
		jbAdd = new JButton("添加");
		jbAdd.addActionListener(this);
		jbCancel = new JButton("取消");
		jbCancel.addActionListener(this);
		
		this.setLayout(new GridLayout(4,2,10,10));
		this.add(jLDeptNo); this.add(jTDeptNo);
		this.add(jLDName);  this.add(jTDName);
		this.add(jLLoc);    this.add(jTLoc);
		this.add(jbAdd);    this.add(jbCancel);
		
		this.setSize(300,200);
		this.setLocation(400, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (jbAdd == e.getSource()) {
			//System.err.println("ko");
			Vector<String> data = new Vector<String>();
			//+" "+this.jTDName.getText().trim()+" "+this.jTLoc.getText().trim();
			data.add(this.jTDeptNo.getText().trim());
			data.add(this.jTDName.getText().trim());
			data.add(this.jTLoc.getText().trim());
			mySQLServer ms = new mySQLServer();
			if (ms.myInsert(data)) {
				System.err.println("success");
			}
			else {
				System.err.println("fail");
			}
		}
		if (jbCancel == e.getSource()) {
			//System.err.println("ko");
			//System.exit(0);
		}
	}

}
