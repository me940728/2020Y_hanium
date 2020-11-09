package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dto.ClosetInfoDTO;
import poly.dto.MailDTO;
import poly.dto.UserInfoDTO;
import poly.service.IUserService;
import poly.service.impl.MailService;
import poly.util.CmmUtil;
import poly.util.DateUtill;
import poly.util.EncryptUtil;

@Controller
public class UserController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "UserService")
	private IUserService userService;
	
	@Resource(name = "MailService")
	private MailService mailService;

	// 유저 로그인 접속
	@RequestMapping(value = "user/userLogin")
	public String userLogin(HttpServletRequest request, ModelMap model) {
		log.info(this.getClass() + "user/userLogin start!!");
		log.info(this.getClass() + "user/userLogin end!!");

		return "/user/userLogin";
	}

	// 로그인 처리
	@RequestMapping(value = "user/userLoginProc.do", method = RequestMethod.POST)
	public String userLoginProc(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		// 접속 확인 로그
		log.info(this.getClass() + "user/userLoginProc start!!");
		// user/userLogin.jsp에서 온 파라미터 정보를 변수에 담는 코드
		String email = EncryptUtil.encAES128CBC(CmmUtil.nvl(request.getParameter("email")));
		String pwd = EncryptUtil.encHashSHA256(CmmUtil.nvl(request.getParameter("password")));

		// 변수에 담긴 데이터를 메모리에 올리는 코드
		UserInfoDTO uDTO = new UserInfoDTO();
		// 메모리에 올라온 DTO 메서드에 데이터를 담는 코드
		uDTO.setEmail(email);
		uDTO.setPassword(pwd);
		log.info("암호 : " + uDTO.getPassword());
		log.info("이메일 : " + uDTO.getEmail());
		log.info("DTO에 데이터 set 완료");
		// DTO에 set된 데이터를 매퍼와 연결하는 서비스
		uDTO = userService.getLoginInfo(uDTO);
		
		// 값이 잘 저장되었는디 확인 하는 로그
		log.info("uDTO null? " + (uDTO == null));
		String msg = "";
		String url = "/user/userLogin.do";
		// 값이 없으면 로그인 실패
		if (uDTO == null) {
			msg = "로그인 실패";
			url = "/user/userLogin.do";
			// 값이 있으면 로그인 성공
		} else {
			log.info("uDTO EMAIL : " + uDTO.getEmail()); 
			log.info("uDTO PWD : " + uDTO.getPassword());
			log.info("uDTO NAME : " + uDTO.getUser_name());
			msg = "로그인 성공";
			// 로그인 알림 메일 발송
			MailDTO mDTO = new MailDTO();
			mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(uDTO.getEmail())));
			mDTO.setTitle("로그인 알림");
			mDTO.setContents(DateUtill.getDateTime("yyyy.MM.dd:24h:mm:ss") + "에" + CmmUtil.nvl(uDTO.getUser_name() + "님이 로그인 하였습니다."));
			mailService.doSendMail(mDTO);
			//-------------------------
			url = "/user/mainPage.do";
			// 성공된 로그인 정보를 세션에 담아 보내는 코드
			session.setAttribute("email", uDTO.getEmail()); 
			session.setAttribute("name_name", uDTO.getUser_name());
			session.setAttribute("user_no", uDTO.getUser_no());
			mDTO = null;
		}
		// 모델 객체에 메세지와 url을 담는 코드
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		log.info(this.getClass() + "/user/userLoginProc end!!");
		// user/redirect.jsp로 리턴 해주느 코드
		uDTO = null;
		
		return "/user/redirect";
	}


	// 로그인 성공 후 메인접속 메퍼
	@RequestMapping(value = "user/mainPage.do")
	public String userMainPage(ModelMap model) throws Exception {
		log.info(this.getClass() + "메인 start!!");
		
		List<ClosetInfoDTO> rList = userService.getMyList();
		
		if (rList==null){
			
			log.info("rList Null입니다.");
			rList = new ArrayList<ClosetInfoDTO>();
		}
		log.info(rList);
		//조회된 리스트 결과값 넣어주기
		model.addAttribute("rList",rList);
		//변수 초기화(메모리 효율화 시키기 위해 사용함)
		rList = null;
		
		log.info(this.getClass() + "메인 end!!");
		return "user/mainPage";
	}

}
