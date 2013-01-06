package com.trez.csm.physical;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

import com.trez.csm.ColorConvertor;

// holds a cropped frame, you can later recenter it in a bigger image
public class FramePhysical {

	BufferedImage bi;
	Vector<OAMPhysical> oams;
	
	public int delay, type;
	
	public FramePhysical(int delay, int type) {
		this.delay = delay;
		this.type = type;
		bi = new BufferedImage(256,256,BufferedImage.TYPE_INT_ARGB);
		oams = new Vector<OAMPhysical>();
	}
	
	public BufferedImage getImage() {
		return bi;
	}
	
	public Vector<OAMPhysical> getOAMs() {
		return oams;
	}
	
	public void addOAM(OAMPhysical oa) {
		oams.add(oa);
		Graphics2D g2d = (Graphics2D) bi.getGraphics();
		int nx, ny;
		if(oa.getX() <= 127) {
			nx = oa.getX();
		} else {
			nx = oa.getX() - 256;
		}
		
		if(oa.getY() <= 127) {
			ny = oa.getY();
		} else {
			ny = oa.getY() - 256;
		}
		
		g2d.drawImage(oa.getOAM(), 128 + nx, 128 + ny, null);
	}
	
	public void reload() {
		for(int i = 0; i < oams.size(); i++) {
			OAMPhysical oa = oams.get(i);
			Graphics2D g2d = (Graphics2D) bi.getGraphics();
			int nx, ny;
			if(oa.getX() <= 127) {
				nx = oa.getX();
			} else {
				nx = oa.getX() - 256;
			}
			
			if(oa.getY() <= 127) {
				ny = oa.getY();
			} else {
				ny = oa.getY() - 256;
			}
			
			g2d.drawImage(oa.getOAM(), 128 + nx, 128 + ny, null);
		}
	}
	
}
