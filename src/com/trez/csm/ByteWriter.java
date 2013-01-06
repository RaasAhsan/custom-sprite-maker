package com.trez.csm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteWriter {

	public static void write32BitInt(ByteArrayOutputStream baos, int i) {
		ByteBuffer bb = ByteBuffer.allocate(4);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.putInt(i);
		try {
			baos.write(bb.array());
			baos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void allocate(ByteArrayOutputStream baos, int size) {
		try {
			baos.write(new byte[size]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
