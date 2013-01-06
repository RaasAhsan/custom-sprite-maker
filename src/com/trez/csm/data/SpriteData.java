package com.trez.csm.data;

import java.util.Vector;

import com.trez.csm.Palette;

// represents a sprite in data
public class SpriteData {
	
	byte data[];
	Palette palette;
	
	Vector<AnimationData> animations;
	
	public SpriteData(byte b[]) {
		this.data = b;
		this.animations = new Vector<AnimationData>();
	}
	
	public byte[] getData() {
		return data;
	}
	
	public void setPalette(Palette p) {
		this.palette = p;
	}
	
	public Palette getPalette() {
		return palette;
	}
	
	public void addAnimation(AnimationData a) {
		animations.add(a);
	}
	
	public AnimationData getAnimationAt(int i) {
		return animations.get(i);
	}
	
	public int getSize() {
		return animations.size();
	}
	
	public Vector<AnimationData> getAnimations() {
		return animations;
	}

}
