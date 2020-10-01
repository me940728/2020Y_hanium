package poly.util;

import java.util.Random;

public class RamdomMail {
	
	public static StringBuffer SendRamdomMail() {
		// 랜덤 생성 메서드
		Random rand = new Random();
		// 랜덤 값 담을 변수 
		StringBuffer strBu = new StringBuffer();
		// 반복문으로 6자리 생성 대뮨자, 소문자, 숫자 케이스 3개
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
		// 값 담아 리턴  -> 차후 메일 발송 및 패스워드 변결 시 사용 예정
		return strBu;
	}
}
