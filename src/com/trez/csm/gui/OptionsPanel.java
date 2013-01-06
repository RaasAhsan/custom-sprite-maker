package com.trez.csm.gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {

	JComboBox jcb;
	JSpinner delay;
	SpinnerNumberModel sm;
	
	public OptionsPanel() {
		JPanel options = new JPanel();
		
		Border animbor = BorderFactory.createTitledBorder("Settings");
		options.setBorder(animbor);
		
		options.setPreferredSize(new Dimension(500,110));
		
		String[] s = { "Play Animation", "End Animation", "Loop Animation" };
		
		jcb = new JComboBox(s);
		
		sm = new SpinnerNumberModel(0, 0, 255, 1);
		delay = new JSpinner(sm);
		
		options.add(jcb);
		options.add(delay);
		this.add(options);
	}
	
}
