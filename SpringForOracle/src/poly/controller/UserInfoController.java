package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.Session;

import poly.dto.MailDTO;
import poly.dto.UserInfoDTO;
import poly.service.IMailService;
import poly.service.IUserInfoService;
import poly.service.impl.MailService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;
import poly.util.RamdomMail;


@Controller
public class UserInfoController {
		
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name ="UserInfoService")
	private IUserInfoService userInfoService;
	// 가입 알림 및 인증번호 발송용 메일 리소스
	@Resource(name ="MailService")
	private IMailService mailservice;

	// 접속처리 
	@RequestMapping(value="user/userRegForm.do")
	public String userRegFoem() {
		log.info(this.getClass().getName() + "user/userRegForm OK!!");
		
		return "/user/UserRegForm";
	}
	
	// 가입 처리
	@RequestMapping(value="user/insertUserInfo.do")
	public String insertUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) throws Exception {
		log.info(this.getClass().getName() + "user/userRegForm OK!!");
		
		String msg = "";
		String url = "";
		
		UserInfoDTO pDTO = null;
		
		try {
			String user_name = CmmUtil.nvl(request.getParameter("user_name"));
			String password = CmmUtil.nvl(request.getParameter("password"));
			String email = CmmUtil.nvl(request.getParameter("email"));
			String sex = CmmUtil.nvl(request.getParameter("sex"));
			String user_an = CmmUtil.nvl(request.getParameter("user_an"));
			
			log.info("user_name : " + user_name);
			log.info("email : " + email);
			log.info("sex : " + sex);
			log.info("user_an : " + user_an);
			
			pDTO = new UserInfoDTO();
			
			log.info("디티오 올림");
			
			pDTO.setUser_name(user_name);
			pDTO.setUser_an(user_an);
		    pDTO.setSex(sex);
			pDTO.setPassword(EncryptUtil.encHashSHA256(password));
			pDTO.setEmail(EncryptUtil.encAES128CBC(email));
			
			log.info(email +"이메일 들어옴");
			log.info("디티오에 잘 들어감");
			
			int res = userInfoService.insertUserInfo(pDTO);
			
			if (res ==1) {
				msg = "회원가입이 완료되었습니다.";
				url = "/user/mainPage.do";
				log.info("가입완료" + res);
				MailDTO mDTO = new MailDTO();
				
				mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));
				mDTO.setTitle("마이도씨 회원가입을 축하드립니다.");
				mDTO.setContents(CmmUtil.nvl(pDTO.getUser_name()) + "님의 회원가입을 축하드립니다.");
				
				mailservice.doSendMail(mDTO);
				
			} else if (res == 2) { // 에러 해결해야함...
				msg = "이미 가입된 이메일 주소입니다.";
				url = "./"; // 경로지정
			} else {
				msg = "오류로 인해 회원가입이 실패하였습니다.";
				url = "./";
			}
		} catch (Exception e) {
			msg = "실패하였습니다. : " + e.toString();
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + "insertUserInfo end!!");
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			model.addAttribute("pDTO", pDTO);
			// 세션 담기
			session.setAttribute("name", pDTO.getUser_name());
			pDTO = null;
			
		}
		
		return "/user/redirect";
	}
	
	// 아이디 찾기 접속 로직
	@RequestMapping(value="user/userSerchForm.do")
	public String userSerchFoem(HttpServletRequest request) {
		
		  log.info(this.getClass().getName() + "user/userSearchForm OK!!");
	      
	      UserInfoDTO pDTO = new UserInfoDTO();
	      
	      String user_name = CmmUtil.nvl(request.getParameter("user_name"));
	      String email = CmmUtil.nvl(request.getParameter("email"));
	      
	      pDTO.setUser_name(user_name);
	      pDTO.setEmail(email);
	      
	      return "/user/userSearchForm";
	}
	// 랜덤문자 생성
	
	// 회원가입 이메일 인증 전송 
	@RequestMapping(value="/createEmailCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public boolean createEmailCheck(@RequestParam String userEmail, @RequestParam String random, HttpServletRequest request, HttpSession session) throws Exception {
		
		log.info(this.getClass().getName() + "이메일 인증 접속 ");
        String email = CmmUtil.nvl(request.getParameter("email"));
        String auth = "";
        String random = "";
        // 인증번호 담는 작업
        auth = RamdomMail.SendRamdomMail();
        random = RamdomMail.SendRamdomMail();
        // 사용자에게 가는 랜덤 문자
        session.setAttribute("authCode", auth);
        // 비교할 인증문자
        session.setAttribute("random", random);
        
        MailDTO mDTO = new MailDTO();
        mDTO.setToMail(email);
        mDTO.setTitle("마이도씨 회원가입 인증 메일입니다.");
        mDTO.setContents("인증번호는" + auth + "입니다.");
        mailservice.doSendMail(mDTO);

		return true;
	}
	// 회원가입 이메일 인증 확인
	@RequestMapping(value="/emailAuth.do", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> emailAuth(@RequestParam String authCode, @RequestParam int random, HttpSession session) {
		String originalJoinCode = (String) session.getAttribute("authCode");
		String authCodeCheck = (String) session.getAttribute("auth");
		return true;
	}


}
