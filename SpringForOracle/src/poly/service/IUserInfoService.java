package poly.service;

import poly.dto.UserInfoDTO;

public interface IUserInfoService {
    // 회원가입 
	int insertUserInfo(UserInfoDTO pDTO) throws Exception;
    // 비밀번호 재설정 확인용
	int updateRan(UserInfoDTO pDTO);
	// 비밀번호 변경 기능
	int doChangePw(UserInfoDTO pDTO);

}
