package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserInfoDTO;

@Mapper("UserInfoMapper")
public interface IUserInfoMapper {

	// 회원 가입하기(회원정보 등록하기)
	int InsertUserInfo(UserInfoDTO pDTO) throws Exception;
	
	// 회원가입 전 중복체크 이놈 문제 많음
	UserInfoDTO getUserExists(UserInfoDTO pDTO) throws Exception;

	// random 값 업데이트 매퍼  업데이트는 int로 
	int updateRan(UserInfoDTO pDTO);
    // 비밀번호 재설정
	int doChangePw(UserInfoDTO pDTO);
    // 랜덤 값으로 유저 이메일 가져오는 메서드
	UserInfoDTO getUserEmail(UserInfoDTO pDTO);
    // 회원정보 수정 
	int updateUserInfo(UserInfoDTO pDTO);
	
}