package com.trez.csm.data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import com.trez.csm.ByteReader;
import com.trez.csm.Palette;



public class SpriteReader {
	
	public SpriteData createSprite(File f) {
		byte[] data = new byte[(int) f.length()];
		Palette p = new Palette();
		
		SpriteData sprite = new SpriteData(data);
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			fis.read(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		// data is now loaded with sprite data from file, so get all pointers etc
		int region = ByteReader.get32bitValueAt(data, 0x00);
		// creates animations from all the pointers
		for(int i = 0; i < region; i += 4) { // read first palette somewhere here //
			int anim = ByteReader.get32bitValueAt(data, i); // animation
			if(i == 0) {
				p.loadPalette(data, ByteReader.get32bitValueAt(data, anim+4), 0);
				sprite.setPalette(p);
			}
			sprite.addAnimation(readAnimationData(data, anim));
		}
		
		return sprite;
	}
	
	// animations are read until a animation type of end is reached
	public AnimationData readAnimationData(byte[] data, int offset) {
		// do it until animtype end is reached
		AnimationData animation = new AnimationData();
		for(int i = offset;;i+=20) { // reads each frame
			int gfxptr = ByteReader.get32bitValueAt(data, i);
			//int palptr = ByteReader.get32bitValueAt(data, i + 0x04);
			// 0x08 is junk data, dont need
			int oamptr = ByteReader.get32bitValueAt(data, i + 0x0C);
			int framedelay = ByteReader.get16bitValueAt(data, 0x10 + i);
			int animtype = ByteReader.get16bitValueAt(data, 0x12 + i);
			
			//System.out.println(Integer.toHexString(gfxptr) + " " + Integer.toHexString(palptr) + " " + Integer.toHexString(oamptr) + " " + Integer.toHexString(animtype));
			
			// add the animation here //
			animation.addFrame(new FrameData(data, gfxptr, oamptr, framedelay, animtype));
			
			if(animtype == 0x80 || animtype == 0xC0) {
				break;
			}
		}
		//System.out.println(animation.getSize());
		
		return animation;
	}

}
