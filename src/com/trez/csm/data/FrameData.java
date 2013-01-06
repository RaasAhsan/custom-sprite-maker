package com.trez.csm.data;

import java.util.Vector;

public class FrameData {

	Vector<OAMData> oams;
	int gfxoffset;
	int animtype;
	int delay;
	
	public FrameData(byte[] b, int gfxptr, int oamptr, int delay, int animtype) {
		this.gfxoffset = gfxptr;
		this.delay = delay;
		this.animtype = animtype;
		
		// build oam data here
		oams = new Vector<OAMData>();
		for(int i = oamptr + 4;;i+=5) {
			//ffffffffff denotes end of oam data set
			if((b[i] & 0xFF) == 0xFF && (b[i+1] & 0xFF) == 0xFF & (b[i+2] & 0xFF) == 0xFF && (b[i+3] & 0xFF) == 0xFF && (b[i+4] & 0xFF) == 0xFF) {
				break;
			}
			// separate size ahead of time
			oams.add(new OAMData(b[i] & 0xFF, b[i+1] & 0xFF, b[i+2] & 0xFF, b[i+3] & 0xFF, b[i+4] & 0xFF));
		}
	}
	
	public int getDelay() {
		return delay;
	}
	
	public int getType() {
		return animtype;
	}
	
	public Vector<OAMData> getOAMData() {
		return oams;
	}
	
	public int getGFXOffset() {
		return gfxoffset;
	}
	
}
