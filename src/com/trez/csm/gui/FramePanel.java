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
import com.trez.csm.physical.FramePhysical;

@SuppressWarnings("serial")
public class FramePanel extends JPanel implements ListSelectionListener {

	public JList frames;
	public DefaultListModel dlm;
	
	public FramePanel() {
		dlm = new DefaultListModel();
		frames = new JList(dlm);
		frames.setCellRenderer(new FrameRenderer());
		frames.setVisibleRowCount(3);
		frames.setFixedCellHeight(110);
		frames.setFixedCellWidth(110);
		frames.addListSelectionListener(this);
		
		JPanel frampanel = new JPanel();
		
		JScrollPane framscroll = new JScrollPane(frames,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		Border animbor = BorderFactory.createTitledBorder("Frames");
		frampanel.setBorder(animbor);
		frampanel.add(framscroll);
		
		this.add(frampanel);
	}
	
	public void loadAnimation(AnimationPhysical ap) {
		this.dlm.clear();
		for(FramePhysical fp : ap.getFrames()) {
			dlm.addElement(fp);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent lse) {
		JList src = (JList) lse.getSource();
		if(src == frames) {
			// load it on the canvas here
			CustomSpriteMaker.gui.canvas.canvas.setFrameIndex(src.getSelectedIndex());
			
			FramePhysical fp = (FramePhysical)src.getSelectedValue();
			if(fp != null) {
				CustomSpriteMaker.gui.oamcon.loadList(fp);
				if(fp.type == 0x00) {
					CustomSpriteMaker.gui.opts.jcb.setSelectedIndex(0);
				} else if(fp.type == 0x80) {
					CustomSpriteMaker.gui.opts.jcb.setSelectedIndex(1);
				} else if(fp.type == 0xC0) {
					CustomSpriteMaker.gui.opts.jcb.setSelectedIndex(2);
				}
				CustomSpriteMaker.gui.opts.delay.setValue(fp.delay);
			}
		}
	}
	
}
