package poly.service;

import poly.dto.UserInfoDTO;

public interface IUserInfoService {

	int insertUserInfo(UserInfoDTO pDTO) throws Exception;
    // 비밀번호 재설정
	int updateRan(UserInfoDTO pDTO);

	

}
