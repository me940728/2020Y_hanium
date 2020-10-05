package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.MyPageDTO;
import poly.dto.UserInfoDTO;

@Mapper("UserMapper")
public interface IUserMapper {
	// 유저로그인 
	UserInfoDTO getLoginInfo(UserInfoDTO uDTO);
    // 유저정보 리스트로 받아오기
	List<UserInfoDTO> getUserList(UserInfoDTO uDTO);
    // 마이 페이지 연결
	List<MyPageDTO> getMyList();
	
}
