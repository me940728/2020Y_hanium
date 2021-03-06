package poly.service.impl;

import javax.annotation.Resource;

import org.apache.catalina.mbeans.UserMBean;
import org.springframework.stereotype.Service;

import poly.dto.MailDTO;
import poly.dto.UserInfoDTO;
import poly.persistance.mapper.IUserInfoMapper;
import poly.service.IMailService;
import poly.service.IUserInfoService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Service("UserInfoService")
public class UserInfoService implements IUserInfoService{
	

	@Resource(name="UserInfoMapper")
	private IUserInfoMapper userInfoMapper;
		
	@Resource(name="MailService")
	private IMailService mailService;
	
	
	@Override
	public int insertUserInfo(UserInfoDTO pDTO) throws Exception{
		
		int res = 0;
		
		if(pDTO == null) {
			pDTO = new UserInfoDTO();
		}
		
		UserInfoDTO rDTO = userInfoMapper.getUserExists(pDTO);
		
		if(rDTO == null) {
			rDTO = new UserInfoDTO();
		}
		
		if(CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
			res = 2;
		} else {
			
			int success = userInfoMapper.insertUserInfo(pDTO);
	
			
			if(success > 0 ) {
				res = 1;
				
				MailDTO mDTO = new MailDTO();
				
				mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));
				mDTO.setTitle("회원가입을 축하드립니다.");
				mDTO.setContents(CmmUtil.nvl(pDTO.getUser_name()) + "님의 회원가입을 진심으로 축하드립니다.");
				
				mailService.doSendMail(mDTO);
			} else {
				res = 0;
			}
		}
		return res;
	}

    // 비밀번호 재설정 서비스 
	@Override
	public int updateRan(UserInfoDTO pDTO) {
		return userInfoMapper.updateRan(pDTO);
	}
    // 비밀번호 재설정 서비스
	@Override
	public int doChangePw(UserInfoDTO pDTO) {
		return userInfoMapper.doChangePw(pDTO);
	}
    // 랜덤속성으로 이메일 가져오는 메서드
	@Override
	public UserInfoDTO getUserEmail(UserInfoDTO pDTO) {
		return userInfoMapper.getUserEmail(pDTO);
	}
    //회원종보 수정 
	@Override
	public int updateUserInfo(UserInfoDTO pDTO) {
		return userInfoMapper.updateUserInfo(pDTO);
	}
}
