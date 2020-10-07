package poly.service;

import java.util.List;

import poly.dto.ClosetInfoDTO;
import poly.dto.UserInfoDTO;

public interface IUserService {

	UserInfoDTO getLoginInfo(UserInfoDTO uDTO);

	List<ClosetInfoDTO> getMyList();
	
}
