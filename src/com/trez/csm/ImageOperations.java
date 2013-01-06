package com.trez.csm;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageOperations {

	public static BufferedImage flipHorizontal(BufferedImage bi) {
		int w = bi.getWidth();  
        int h = bi.getHeight();  
        BufferedImage dimg = new BufferedImage(w, h, bi.getType());  
        Graphics2D g = dimg.createGraphics();  
        g.drawImage(bi, 0, 0, w, h, w, 0, 0, h, null);  
        g.dispose();  
        return dimg;
	}
	
	public static BufferedImage flipVertical(BufferedImage bi) {
		int w = bi.getWidth();  
        int h = bi.getHeight();  
        BufferedImage dimg = new BufferedImage(w, h, bi.getType());  
        Graphics2D g = dimg.createGraphics();  
        g.drawImage(bi, 0, 0, w, h, 0, h, w, 0, null);  
        g.dispose();  
        return bi;
	}
	
	public static boolean isEmpty(BufferedImage bi, Color c) {
		for(int y = 0; y < bi.getHeight(); y ++) {
			for(int x = 0; x < bi.getWidth(); x ++) {
				if(bi.getRGB(x, y) != c.getRGB()) {
					return false;
				}
			}
		}
		return true;
	}
	
}
