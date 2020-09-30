package poly.util;

import java.util.Random;

public class RamdomMail {
	
	public static StringBuffer SendRamdomMail() {
		
		Random rand = new Random();
		StringBuffer strBu = new StringBuffer();
		
		for (int i = 0; i < 6; i++) {
			int index = rand.nextInt(3);
			switch (index) {
			case 0:
				strBu.append((char)(rand.nextInt(26)+97));
				break;
			case 1:
				strBu.append((char)(rand.nextInt(26)+65));
				break;
			case 2:
				strBu.append(rand.nextInt(10));
				break;
			}
		}
		return strBu;
	}
}
