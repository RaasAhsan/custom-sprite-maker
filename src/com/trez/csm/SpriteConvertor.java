package com.trez.csm;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.trez.csm.data.AnimationData;
import com.trez.csm.data.FrameData;
import com.trez.csm.data.OAMData;
import com.trez.csm.data.SpriteData;
import com.trez.csm.physical.AnimationPhysical;
import com.trez.csm.physical.FramePhysical;
import com.trez.csm.physical.OAMPhysical;
import com.trez.csm.physical.SpritePhysical;

public class SpriteConvertor {

	// this function convert spritedata to spritephysical
	public static SpritePhysical convertToPhysical(SpriteData sprite) {
		SpritePhysical sp = new SpritePhysical();
		
		// keep for reference instead of doing sprtie.getdata()
		byte data[] = sprite.getData();
		Palette p = sprite.getPalette();
		
		// first loop through sprtiedata animations
		for(AnimationData a : sprite.getAnimations()) {
			// now loop through the frames of a
			AnimationPhysical anim = new AnimationPhysical();
			for(FrameData f : a.getFrames()) {
				// finally, loop through all the oams, and then it'll all end
				FramePhysical fp = new FramePhysical(f.getDelay(), f.getType());
				int gfxptr = f.getGFXOffset() + 4; // +4 because it actually points to the size of the sprite
				for(OAMData oam : f.getOAMData()) {
					// start drawing the oam here
					int ox = oam.getX();
					int oy = oam.getY();
					
					int start = gfxptr + (oam.getPosition() * 32); // 64 pixels in a oam, 4bbp, so 32 bytes
					BufferedImage bi = SpriteConvertor.convertTileData(data, oam, start, p);
					bi = ColorConvertor.makeColorTransparent(bi, new Color(0x00, 0x00, 0x00));
					
					if(oam.getRotation() == 0x40) { // horizontal
						bi = ImageOperations.flipHorizontal(bi);
					} else if(oam.getRotation() == 0x80) { // vertical
						bi = ImageOperations.flipVertical(bi);
					} else if(oam.getRotation() == 0xC0) { // both
						bi = ImageOperations.flipHorizontal(bi);
						bi = ImageOperations.flipVertical(bi);
					}
					
					fp.addOAM(new OAMPhysical(bi, ox, oy));
				}
				anim.addFrame(fp);
			}
			sp.addAnimation(anim);
		}
		
		System.out.println(sp.getSize());
		
		/*Palette n;
		if((n = Palette.getPaletteOf(frame)) != null) {
			System.out.println("16 palettes!");
		}*/
		
		return sp;
	}
	
	// tiles are draw left to right, up to down, 4 bits per pixel
	private static BufferedImage convertTileData(byte[] b, OAMData oam, int gfxptr, Palette p) {
		BufferedImage bi = new BufferedImage(oam.getWidth(), oam.getHeight(), 2);
		
		// gets the amount of tiles we need to go through
		int maxtile = (oam.getWidth() / 8) * (oam.getHeight() / 8);
		int dx = 0, dy = 0;
		int pos = 0;
		
		// we only use an orig
		int origx = 0, origy = 0;
		
		for(int i = 0; i < maxtile; i++, pos += 32) {
			for(int z = pos; z < pos + 32; z++, dx+=2) {
				if(dx == origx + 8) {
					dx = origx;
					dy++;
				}
				int right = ByteReader.getLeftByteValue(b, gfxptr + z);
				int left = ByteReader.getRightByteValue(b, gfxptr + z);
				
				if(ColorConvertor.hextoRGB(p.getColorAt(left)).getRGB() != 0)
					bi.setRGB(dx, dy, ColorConvertor.hextoRGB(p.getColorAt(left)).getRGB());
				if(ColorConvertor.hextoRGB(p.getColorAt(right)).getRGB() != 0)
					bi.setRGB(dx+1, dy, ColorConvertor.hextoRGB(p.getColorAt(right)).getRGB());
			}
			origx += 8;
			if(origx >= oam.getWidth()) {
				origx = 0;
				origy += 8;
			}
			dx = origx;
			dy = origy;
		}
		
		return bi;
	}
	
	public static SpriteData convertToData(SpritePhysical sp) {
		SpriteData sd = null;
		
		// loops through all animations down to individual oams
		
		return sd;
	}
	
	public static Byte[] allocateData(int size) {
		Byte[] b = new Byte[size];
		for(int i = 0; i < size; i++) {
			b[i] = 0;
		}
		return b;
	}
	
}
