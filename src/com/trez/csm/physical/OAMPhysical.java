package com.trez.csm.physical;

import java.awt.image.BufferedImage;

public class OAMPhysical {

	BufferedImage oam;
	int x, y;
	
	public OAMPhysical(BufferedImage bi, int x, int y) {
		this.oam = bi;
		this.x = x;
		this.y = y;
	}
	
	public int getWidth() {
		return oam.getWidth();
	}
	
	public int getHeight() {
		return oam.getHeight();
	}
	
	public BufferedImage getOAM() {
		return oam;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
