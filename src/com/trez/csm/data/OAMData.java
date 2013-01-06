package com.trez.csm.data;

public class OAMData {
	
	// oam start position
	int pos;
	
	// x and y are out of 256, 0,0 is the center
	int x;
	int y;

	int width;
	int height;
	
	int rotation;
	
	public OAMData(int pos, int ox, int oy, int rs, int pm) {
		this.pos = pos;
		this.x = ox;
		this.y = oy;
		
		int mod = pm & 0xF;
		this.rotation = rs & 0xF0;
		
		int size = rs & 0xF;
		width = height = (int) Math.pow(2, 3 + size);
		
		if(size == 0) {
			if(mod == 1) {
				width *= 2;
			} else if(mod == 2) {
				height *= 2;
			}
		}
		if(size == 1) {
			if(mod == 1) {
				width *= 2;
				height /= 2;
			} else if(mod == 2) {
				width /= 2;
				height *= 2;
			}
		}
		if(size == 2) {
			if(mod == 1) {
				height /= 2;
			} else if(mod == 2) {
				width /= 2;
			}
		}
		if(size == 3) {
			if(mod == 1) {
				height /= 2;
			} else if(mod == 2) {
				width /= 2;
			}
		}
		
		//System.out.println(width + " " + height);
	}
	
	public int getPosition() {
		return pos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getRotation() {
		return this.rotation;
	}
	
}
