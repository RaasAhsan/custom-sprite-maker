package com.trez.csm;

public class ByteReader {
	
	public static int getLeftByteValue(byte[] data, int index) {
		return (data[index] >> 4) & 0xF;
	}
	
	public static int getRightByteValue(byte[] data, int index) {
		return data[index] & 0xF;
	}
	
	public static int get16bitValueAt(byte data[], int index) {
		int value = 0;
		value += data[index+1] & 0xFF;
		value <<= 8;
		value += data[index] & 0xFF;
		return value;
	}

	public static int get32bitValueAt(byte data[], int index) {
		int value = 0;
		value += data[index+3] & 0xFF;
		value <<= 8;
		value += data[index+2] & 0xFF;
		value <<= 8;
		value += data[index+1] & 0xFF;
		value <<= 8;
		value += data[index] & 0xFF;
		return value & 0xFFFFFFFF;
	}
	
}
