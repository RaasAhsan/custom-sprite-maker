package com.trez.csm.physical;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Vector;

import com.trez.csm.ColorConvertor;
import com.trez.csm.ImageOperations;

public class ImageReader {

	// this function converts an image to its oam, uses the square algorithm to merge together interlaying tiles
	public static Vector<OAMPhysical> getOAMsOfImage(BufferedImage bi) {
		Vector<OAMPhysical> oams = new Vector<OAMPhysical>();
		
		for(int y = 0; y < bi.getHeight(); y += 8) {
			for(int x = 0; x < bi.getWidth(); x += 8) {
				BufferedImage sub = bi.getSubimage(x, y, 8, 8);
				sub = ColorConvertor.makeColorTransparent(sub, new Color(0xFF, 0xFF, 0xFF));
				if(!ImageOperations.isEmpty(sub, new Color(0xFF, 0xFF, 0xFF))) {
					oams.add(new OAMPhysical(sub, x, y));
				}
			}
		}
		
		return oams;
	}
	
}
