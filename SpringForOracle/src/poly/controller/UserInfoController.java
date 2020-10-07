package poly.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.MailDTO;
import poly.dto.UserInfoDTO;
import poly.service.IMailService;
import poly.service.IUserInfoService;
import poly.service.impl.UserInfoService;
import poly.service.impl.UserService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;
import poly.util.RamdomMail;

@Controller
public class UserInfoController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "UserInfoService")
	private IUserInfoService userInfoService;
	// 가입 알림 및 인증번호 발송용 메일 리소스
	@Resource(name = "MailService")
	private IMailService mailservice;

	// 접속처리
	@RequestMapping(value = "user/userRegForm.do")
	public String userRegFoem() {
		log.info(this.getClass().getName() + "user/userRegForm OK!!");

		return "/user/UserRegForm";
	}

	// 가입 처리
	@RequestMapping(value = "user/insertUserInfo.do")
	public String insertUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			HttpSession session) throws Exception {
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

			log.info(email + "이메일 들어옴");
			log.info("디티오에 잘 들어감");

			int res = userInfoService.insertUserInfo(pDTO);
			log.info("res는 몇?? " + res);
			
			if (res == 1) {
				msg = "회원가입이 완료되었습니다.";
				url = "/user/userLogin.do";
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
	@RequestMapping(value = "user/userSerchForm.do")
	public String userSerchFoem(HttpServletRequest request) {

		log.info(this.getClass().getName() + "user/userSearchForm OK!!");
		log.info(this.getClass().getName() + "user/userSearchForm END!!");

		return "/user/userSearchForm";
	}

	// 아이디 찾기 후 이메일로 랜덤 문자 발송
	@RequestMapping(value = "/user/userSerchEmailCheck.do")
	public String userSerchEmailCheck(HttpServletRequest request, ModelMap model, HttpSession session)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		log.info(this.getClass().getName() + "랜덤 문자 발송 OK!!");

		String email = EncryptUtil.encAES128CBC(CmmUtil.nvl(request.getParameter("email")));
		String random = "";
		String msg = "";
		String url = "";
		// 랜덤 값 담기
		random = RamdomMail.SendRamdomMail();
		log.info("랜덤값 : " + random);
		// 메일 서비스 메모리 올리기
		MailDTO mDTO = new MailDTO();
		UserInfoDTO pDTO = null;

		try {
			pDTO = new UserInfoDTO();
			// 랜덤 값 데이터베이스에 저장
			pDTO.setRandom(random);
			pDTO.setEmail(email);
			// pDTO 값을 데이터베이스 업데이트
			userInfoService.updateRan(pDTO);
			// 이메일 보내기 위해 다시 암호화 디코딩
			email = EncryptUtil.decAES128CBC(email);
			log.info("이메일 : " + email);
			log.info("DTO is Null?" + (pDTO == null));
			log.info("랜덤문자: " + pDTO.getRandom());
			// 랜덤문자 메일 발송 로직
			mDTO.setToMail(email);
			mDTO.setTitle("마이도씨 비밀번호 변경용 인증 문자 입니다.");
			mDTO.setContents("인증 문자는 :  " + random + "  입니다.");
			// 최종 전송
			mailservice.doSendMail(mDTO);
			
			msg = "이메일로 인증문자를 발송하였습니다.";
			url = "/user/findPwRes.do";
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			// 값 비교용 세션 담기
			session.setAttribute("random", random);
			// DTO 초기화 항상 해주기

		} catch (Exception e) {
			msg = "실패하였습니다. : " + e.toString();
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + "이상무!");
			//변수와 메모리 초기화
			msg = "";
			url = "";
			mDTO = null;
			pDTO = null;
		}

		log.info(this.getClass().getName() + "user/userSearchForm END!!");
		return "/user/redirect";
	}

	// 재설정 인증 페이지 접속 매퍼
	@RequestMapping(value = "/user/findPwRes.do")
	public String findPwRes() {
		return "/user/findPwRes";
	}

	// 비번 재설정 메퍼 => 비밀번호 정합성이 떨어짐 에러 해결 해야함
	@RequestMapping(value = "/user/doChangePw.do")
	public String doChangePw(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		log.info(this.getClass().getName() + "user/doChangePw start");

		try { // try문은 지역변수
			String password = CmmUtil.nvl(EncryptUtil.encHashSHA256(request.getParameter("password")));
			log.info("비밀번호: " + password);
			String random = CmmUtil.nvl(request.getParameter("random"));
			log.info("랜덤 값 : " + random);
			String email = "";
			int res = 0; // 작업 성공 여부 확인 용
			String msg = "";
			String url = "";
			// 랜덤값 매칭 시켜 유저 정보 가져오기 위한 DTO 설정
			UserInfoDTO pDTO = new UserInfoDTO();
			pDTO.setRandom(random);
			// 수정자 수정을 하려고 랜덤값으로 수정자 이메일 가져오는 서비스 만듦 uDTO에 값을 다시 담아줘야함 uDTO = 이렇게
			pDTO = userInfoService.getUserEmail(pDTO);
			email = pDTO.getEmail();
			// 이메일 잘 가져오는지 체크용
			log.info("이메일 : " + EncryptUtil.decAES128CBC(pDTO.getEmail()));
			// DTO 재사용 하려면 항상 DTO 비워주고 다시 사용해야함
			// 사용 후 충돌방지를 위한 메모리 비우기
			pDTO = null;
			// 재사용을 위한 메모리 올리기
			pDTO = new UserInfoDTO();
			log.info("password 암호화 완료 : " + password);
			log.info("DTO 메모리 할당 성공");
			// 변경 할 비밀번호 셋팅
			pDTO.setPassword(password);
			pDTO.setRandom(random);
			pDTO.setEmail(email);
			log.info("DTO에 비밀번호 내용 저장 완료 : " + pDTO.getPassword());
			// 데이터 잘들어갔는지 확인용 res
			res = userInfoService.doChangePw(pDTO);
			log.info("res 1이면 성공 0이면 에러 =?" + res);

			// 데이터 잘 들어갔으니 변경 완료 알람 창 띄운 후 링크 변경
			if (res == 1) {
				msg = "비밀번호 변경이 성공적으로 마무리 되었습니다.";
				url = "/user/userLogin.do";
				res = 0;
			} else {
				msg = "비밀번호 변경 에러 ";
				url = "./";
			}
			// 디티오 비우기 (메모리 터짐)
			pDTO = null;
			// 키 값과 벨류 값 무조건 지정
			model.addAttribute("msg", msg);
			log.info(msg);
			model.addAttribute("url", url);
			log.info(url);
			msg = "";
			url = "";

		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + "doChangePw end");
			
		}
		return "/user/redirect";
	}

	// 회원가입 이메일 인증 전송
	@RequestMapping(value = "user/createEmailCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean createEmailCheck(@RequestParam String userEmail, @RequestParam String random,
			HttpServletRequest request, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "이메일 인증 접속 ");
		String email = CmmUtil.nvl(request.getParameter("email"));
		String auth = "";
		String ran = "";
		// 인증번호 담는 작업
		auth = RamdomMail.SendRamdomMail();
		ran = RamdomMail.SendRamdomMail();
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
	@RequestMapping(value = "/emailAuth.do", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> emailAuth(@RequestParam String authCode, @RequestParam String random,
			HttpSession session) {
		String originalJoinCode = (String) session.getAttribute("authCode");
		String authCodeCheck = (String) session.getAttribute("auth");
		if (originalJoinCode.equals(authCode) && authCodeCheck.equals("ran"))
			;

		return new ResponseEntity<String>("complete", HttpStatus.OK);

		// else return new ResponseEntity<String>("false", HttpStatus.OK);
	}

	// 메인페이지 접속 후 유저가 회원정보 수정할 때 사용하는 컨트롤러
	@RequestMapping(value = "/user/cngeUserInfo.do")
	public String cngeUserInfo() {
		log.info(this.getClass().getName() + "회원정보 수정 접속 ");
		return "/user/cngeUserInfo";
	}

	@RequestMapping(value = "/user/cngeUserInfoProc.do")
	public String cngeUserInfoProc(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		log.info(this.getClass().getName() + "회원정보 수정 접속 ");
		// 디티오에 값 넣기위한 선언
		UserInfoDTO pDTO = null;
		try {
			String password = CmmUtil.nvl(EncryptUtil.encHashSHA256(request.getParameter("password")));
			String user_name = CmmUtil.nvl(request.getParameter("name"));
			String user_an = CmmUtil.nvl(request.getParameter("user_an"));
			// 로그인 성공 후 사용한 이메일 세션 값을 매칭 시켜 수정 함
			String email = (String) session.getAttribute("email");
			int res = 0;
			String msg = "";
			String url = "";

			pDTO = new UserInfoDTO();
			pDTO.setPassword(password);
			pDTO.setUser_name(user_name);
			pDTO.setUser_an(user_an);
			pDTO.setEmail(email);
			res = userInfoService.updateUserInfo(pDTO);

			if (res == 1) {
				msg = "성공적으로 변경되었습니다.";
				url = "user/mainPage.do";
				res = 0;
			} else {
				msg = "실패";
				url = "user/mainPage.do";
			}
			log.info(this.getClass().getName() + "회원정보 수정 end");
			// 디티오 비우기 (메모리 터짐)
			pDTO = null;
			model.addAttribute("msg", msg);
			log.info(msg);
			model.addAttribute("url", url);
			log.info(url);

		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
			
		} finally {
			log.info(this.getClass().getName() + "회원정보 수정 end 2");
		}
		log.info(this.getClass().getName() + "회원정보 수정 end 3");
		return "/user/redirect";
	}
	// 이메일 인증 시 중복확인
	@RequestMapping(value = "emailCheckOK.do")
	public String emailCehckOK(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String email = CmmUtil.nvl(request.getParameter("email"));
		UserInfoDTO pDTO = new UserInfoDTO();
		int result = 0;
		pDTO.setEmail(email);
		// result = userInfoService.confirmEmail(pDTO);
		//결과값 보내기
		response.getWriter().write(String.valueOf(result));
		pDTO = null;
		return "ajax";
	}
}
