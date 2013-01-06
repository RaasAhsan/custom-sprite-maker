package com.trez.csm.physical;

import java.util.Vector;

public class AnimationPhysical {

	Vector<FramePhysical> frames;
	
	public AnimationPhysical() {
		frames = new Vector<FramePhysical>();
	}
	
	public void removeFrame(int i) {
		frames.remove(i);
	}
	
	public void removeFrame(FramePhysical fp) {
		frames.remove(fp);
	}
	
	public void addFrame(FramePhysical f) {
		frames.add(f);
	}
	
	public FramePhysical getFrameAt(int i) {
		return frames.get(i);
	}
	
	public int getSize() {
		return frames.size();
	}
	
	public Vector<FramePhysical> getFrames() {
		return frames;
	}
	
}
