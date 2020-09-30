package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.MyPageDTO;
import poly.dto.UserInfoDTO;

@Mapper("UserMapper")
public interface IUserMapper {

	// 유저로그인 인포 
	UserInfoDTO getLoginInfo(UserInfoDTO uDTO);

	List<UserInfoDTO> getUserList(UserInfoDTO uDTO);

	List<MyPageDTO> getMyList();
	
}
