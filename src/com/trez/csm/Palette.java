package com.trez.csm;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class Palette {
	
	int colors[];
	
	public Palette() {
		colors = new int[16];
		for(int i = 0; i < 15; i++) {
			colors[i] = 0;
		}
	}
	
	public void loadPalette(byte[] b, int offset, int index) {
		// palette starts with 4 unneeded bytes, ignore those, its always 16
		int start = offset + 4;
		for(int i = start, c=0; i < start + 32; i+=2, c++) {
			colors[c] = ByteReader.get16bitValueAt(b, i);
			//System.out.println(Integer.toHexString(colors[c]));
		}
	}
	
	public int getColorAt(int i) {
		return colors[i];
	}
	
	public void setValue(int index, int value) {
		colors[index] = value;
	}
	
	public static Palette getPaletteOf(BufferedImage bi) {
		Palette p = new Palette();
		
		Vector<Integer> rgbs = new Vector<Integer>();
		
		for(int i = 0; i < bi.getHeight(); i++) {
			for(int j = 0; j < bi.getWidth(); j++) {
				int rgb = bi.getRGB(j, i);
				if(!rgbs.contains(rgb)) {
					rgbs.add(rgb);
				}
			}
		}
		
		System.out.println(rgbs.size());
		
		for(int i = 0; i < rgbs.size(); i++) {
			p.setValue(i, rgbs.get(i));
		}
		
		return p;
	}
	
	public int getIndexOf(int rgb) {
		for(int i = 0; i < 15; i++) {
			if(colors[i] == rgb) {
				return i;
			}
		}
		return -1;
	}
	
	public BufferedImage getPaletteImage() {
		BufferedImage bi = new BufferedImage(16, 1, BufferedImage.TYPE_INT_ARGB);
		
		for(int i = 0; i < 15; i++) {
			bi.setRGB(i, 0, colors[i]);
		}
		
		return bi;
	}

}
