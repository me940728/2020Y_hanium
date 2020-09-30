package poly.service;

import java.util.List;

import poly.dto.MyPageDTO;
import poly.dto.UserInfoDTO;

public interface IUserService {

	UserInfoDTO getLoginInfo(UserInfoDTO uDTO);

	List<MyPageDTO> getMyList();
	
}
