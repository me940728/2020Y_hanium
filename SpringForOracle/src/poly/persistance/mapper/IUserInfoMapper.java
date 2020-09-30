package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserInfoDTO;

@Mapper("UserInfoMapper")
public interface IUserInfoMapper {
	// 회원 가입하기(회원정보 등록하기)
	int InsertUserInfo(UserInfoDTO pDTO) throws Exception;
	
	// 회원가입 전 중복체크 이놈 문제 많음
	UserInfoDTO getUserExists(UserInfoDTO pDTO) throws Exception;
	
}