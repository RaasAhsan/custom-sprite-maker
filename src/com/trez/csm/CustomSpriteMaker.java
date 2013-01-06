package com.trez.csm;

import java.util.HashMap;

import com.trez.csm.gui.BNSpriteCreator;


public class CustomSpriteMaker {
	
	public static BNSpriteCreator gui;

	public static void main(String[] args) {
		gui = new BNSpriteCreator();
		gui.setVisible(true);
		
		//int a = 0;
		//a += 0xF0 & (0xC << 4);
		//a += 0x0F & 0xB;
		
		//System.out.println(Integer.toHexString(a));
		
		//LZW algorithm
		/*HashMap<String, Integer> mapfile = new HashMap<String, Integer>();
		String raw = "AndAndAndAndAndAndAndAnd";
		
		int curcode = 256;
		
		int curpos = 0;
		String buffer = "" + raw.charAt(curpos);
		while(true) {
			curpos++;
			if(curpos == raw.length())
				break;
			char next = raw.charAt(curpos);
			if(mapfile.containsKey(buffer + next)){
				buffer += next;
			} else {
				mapfile.put(buffer + next, curcode++);
				
				if(!mapfile.containsKey(buffer))
					System.out.print(buffer);
				else
					System.out.print(mapfile.get(buffer));
				
				buffer = "" + raw.charAt(curpos);
			}
		}
		if(!mapfile.containsKey(buffer))
			System.out.print(buffer);
		else
			System.out.print(mapfile.get(buffer));*/
	}
	
}
