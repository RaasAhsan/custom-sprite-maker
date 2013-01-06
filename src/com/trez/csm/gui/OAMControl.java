package com.trez.csm.gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.trez.csm.CustomSpriteMaker;
import com.trez.csm.physical.FramePhysical;
import com.trez.csm.physical.OAMPhysical;

@SuppressWarnings("serial")
public class OAMControl extends JPanel implements ListSelectionListener {

	public JList oams;
	public DefaultListModel dlm;
	public JLabel oamview;
	
	public FramePhysical cfp;
	
	JLabel xlab;
	JSpinner xnum;
	JLabel ylab;
	JSpinner ynum;
	SpinnerNumberModel jnm;
	
	JComboBox width;
	JComboBox height;

	public OAMControl() {
		JPanel oampanel = new JPanel();

		oamview = new JLabel();
		oamview.setPreferredSize(new Dimension(64,64));
		
		Border animbor = BorderFactory.createTitledBorder("OAM Management");
		oampanel.setBorder(animbor);

		oampanel.setPreferredSize(new Dimension(200, 360));
		
		dlm = new DefaultListModel();
		oams = new JList(dlm);
		oams.setFixedCellWidth(75);
		oams.setFixedCellHeight(15);
		oams.setVisibleRowCount(10);
		oams.addListSelectionListener(this);
		
		JScrollPane scrollPane = new JScrollPane(oams);
		
		String[] s = new String[] { "8", "16", "32","64" };
		
		width = new JComboBox(s);
		height = new JComboBox(s);

		jnm = new SpinnerNumberModel(0, -128, 127, 1);
		JPanel xpan = new JPanel();
		xlab = new JLabel("X: ");
		xnum = new JSpinner(jnm);
		xpan.add(xlab);
		xpan.add(xnum);
		
		JPanel ypan = new JPanel();
		ylab = new JLabel("Y: ");
		ynum = new JSpinner(jnm);
		ypan.add(ylab);
		ypan.add(ynum);
		
		oampanel.add(oamview);
		oampanel.add(scrollPane);
		oampanel.add(xpan);
		oampanel.add(ypan);
		
		oampanel.add(new JLabel("Width: "));
		oampanel.add(width);
		oampanel.add(new JLabel("Height: "));
		oampanel.add(height);
		this.add(oampanel);
		
		cfp = null;
	}

	@Override
	public void valueChanged(ListSelectionEvent lse) {
		JList src = (JList) lse.getSource();
		if(src.getSelectedValue() != null) {
			OAMPhysical oamp = cfp.getOAMs().get(src.getSelectedIndex());
			this.oamview.setIcon(new ImageIcon(oamp.getOAM()));
			this.xnum.setValue(oamp.getX());
			this.ynum.setValue(oamp.getY());
			
			this.width.setSelectedItem(String.valueOf(oamp.getWidth()));
			this.height.setSelectedItem(String.valueOf(oamp.getHeight()));
		}
	}
	
	public void loadList(FramePhysical fp) {
		if(fp != null) {
			cfp = fp;
			dlm.clear();
			for(int i = 0; i < fp.getOAMs().size(); i++) {
				dlm.addElement(new String("OAM") + String.valueOf(i));
			}
		}
	}

}
