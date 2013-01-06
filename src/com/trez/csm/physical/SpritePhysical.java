package com.trez.csm.physical;

import java.util.Vector;

// represents an image sprite
public class SpritePhysical {

	Vector<AnimationPhysical> animations;
	
	public SpritePhysical() {
		animations = new Vector<AnimationPhysical>();
	}
	
	public void addAnimation(AnimationPhysical ap) {
		animations.add(ap);
	}
	
	public void removeAnimation(AnimationPhysical ap) {
		animations.remove(ap);
	}
	
	public void removeAnimation(int i) {
		animations.remove(i);
	}
	
	public Vector<AnimationPhysical> getAnimations() {
		return animations;
	}
	
	public int getSize() {
		return animations.size();
	}

	public int getLargestAnimationSize() {
		int largest = 0;
		
		for(AnimationPhysical ap : animations) {
			if(ap.getSize() > largest) {
				largest = ap.getSize();
			}
		}
		
		return largest;
	}
	
}
