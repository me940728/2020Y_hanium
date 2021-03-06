package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.ClosetInfoDTO;
import poly.dto.UserInfoDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.IUserService;

@Service("UserService")
public class UserService implements IUserService{
	
	@Resource(name="UserMapper")
	private IUserMapper userMapper;

	@Override
	public UserInfoDTO getLoginInfo(UserInfoDTO uDTO) {
		return userMapper.getLoginInfo(uDTO);
	}

	@Override
	public List<ClosetInfoDTO> getMyList() {
		return userMapper.getMyList();
	}

	
}
