package poly.service;

import poly.dto.UserInfoDTO;

public interface IUserInfoService {
    // 회원가입 
	int insertUserInfo(UserInfoDTO pDTO) throws Exception;
    // 비밀번호 재설정 확인용
	int updateRan(UserInfoDTO pDTO);
	// 비밀번호 변경 기능
	int doChangePw(UserInfoDTO pDTO);
	// 랜덤값으로 유저 이메일 가져오는 
	UserInfoDTO getUserEmail(UserInfoDTO pDTO);
	// 회원정보 수정 
	int updateUserInfo(UserInfoDTO pDTO);

}
