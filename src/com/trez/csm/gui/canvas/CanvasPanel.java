package com.trez.csm.gui.canvas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.trez.csm.CustomSpriteMaker;

@SuppressWarnings("serial")
public class CanvasPanel extends JPanel implements ActionListener {

	public Canvas canvas;
	
	JButton next, prev;
	JButton in, out;
	
	JCheckBox preview;
	
	public CanvasPanel() {
		canvas = new Canvas();
		this.setPreferredSize(new Dimension(320,320));
		
		preview = new JCheckBox("Preview");
		preview.setActionCommand("preview");
		preview.addActionListener(this);
		
		next = new JButton(new ImageIcon("arrow_right.png"));
		next.setActionCommand("fnext");
		next.addActionListener(this);
		
		prev = new JButton(new ImageIcon("arrow_left.png"));
		next.setActionCommand("fprev");
		prev.addActionListener(this);
		
		in = new JButton(new ImageIcon("zoom_in.png"));
		in.setActionCommand("zin");
		in.addActionListener(this);
		
		out = new JButton(new ImageIcon("zoom_out.png"));
		out.setActionCommand("zout");
		out.addActionListener(this);
		
		this.add(prev);
		this.add(out);
		this.add(in);
		this.add(next);
		
		this.add(canvas);
		this.add(preview);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("preview")) {
			if(preview.isSelected()) {
				canvas.setAnimating(true);
			} else {
				canvas.setAnimating(false);
			}
		} else if(ae.getSource() == next) {
			CustomSpriteMaker.gui.fram.frames.setSelectedIndex(CustomSpriteMaker.gui.fram.frames.getSelectedIndex()+1);
		} else if(ae.getSource() == prev) {
			CustomSpriteMaker.gui.fram.frames.setSelectedIndex(CustomSpriteMaker.gui.fram.frames.getSelectedIndex()-1);
		} else if(ae.getActionCommand().equals("zin")) {
			canvas.addZoomScale();
		} else if(ae.getActionCommand().equals("zout")) {
			canvas.subZoomScale();
		}
	}
	
}
