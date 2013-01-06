package com.trez.csm.gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

import com.trez.csm.physical.AnimationPhysical;

@SuppressWarnings("serial")
public class AnimationRenderer extends DefaultListCellRenderer {
	
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		if(value instanceof AnimationPhysical) {
			((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
			AnimationPhysical ae = (AnimationPhysical) value;
			((JLabel) c).setIcon(new ImageIcon(ae.getFrameAt(0).getImage()));
			((JLabel) c).setText("");
			((JLabel) c).setSize(80,80);
		}
		return c;
	}
	
}
