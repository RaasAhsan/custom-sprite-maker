package com.trez.csm.gui;

import javax.swing.table.DefaultTableModel;

public class OAMTableRenderer extends DefaultTableModel {
	
	

	Class getColumnClass() {
		return Boolean.class;
	}
	
}
