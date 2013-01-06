package com.trez.csm.gui;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.trez.csm.CustomSpriteMaker;
import com.trez.csm.physical.AnimationPhysical;

@SuppressWarnings("serial")
public class AnimationPanel extends JPanel implements ListSelectionListener {

	JList animations;
	DefaultListModel dlm;
	
	public AnimationPanel() {
		dlm = new DefaultListModel();
		animations = new JList(dlm);
		animations.setCellRenderer(new AnimationRenderer());
		animations.setVisibleRowCount(3);
		animations.setFixedCellHeight(110);
		animations.setFixedCellWidth(110);
		animations.addListSelectionListener(this);
		
		JPanel animpanel = new JPanel();
		
		JScrollPane animscroll = new JScrollPane(animations,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		Border animbor = BorderFactory.createTitledBorder("Animations");
		animpanel.setBorder(animbor);
		animpanel.add(animscroll);
		
		this.add(animpanel);
	}

	@Override
	public void valueChanged(ListSelectionEvent lse) {
		JList src = (JList) lse.getSource();
		if(src == animations) {
			if(src.getSelectedValue() != null) {
				CustomSpriteMaker.gui.fram.loadAnimation((AnimationPhysical)src.getSelectedValue());
				CustomSpriteMaker.gui.canvas.canvas.setAnimation(((AnimationPhysical)src.getSelectedValue()));	
			}
		}
	}
}
