package com.trez.csm.data;

import java.util.Vector;

public class AnimationData {

	Vector<FrameData> frames;
	
	public AnimationData() {
		frames = new Vector<FrameData>();
	}
	
	public void addFrame(FrameData fd) {
		frames.add(fd);
	}
	
	public FrameData getFrameAt(int i) {
		return frames.get(i);
	}
	
	public int getSize() {
		return frames.size();
	}
	
	public Vector<FrameData> getFrames() {
		return frames;
	}
	
}
