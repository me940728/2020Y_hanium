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

import poly.dto.MyPageDTO;
import poly.dto.UserInfoDTO;
import poly.service.IUserService;

import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Controller
public class UserController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "UserService")
	private IUserService userService;

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
		
		// 비밀번호 암호 유무 확인
		log.info(pwd);
		// 변수에 담긴 데이터를 메모리에 올리는 코드
		UserInfoDTO uDTO = new UserInfoDTO();
		// 코드를 디코딩 하는 알고리즘
		// 메모리에 올라온 DTO 메서드에 데이터를 담는 코드
		uDTO.setEmail(email);
		uDTO.setPassword(pwd);
		
		log.info(EncryptUtil.decAES128CBC(email));
		log.info("DTO에 값은 들어감");
		// 로그인 메서드에 값을 담고 다시 받아오는 코드
		uDTO = userService.getLoginInfo(uDTO);
		// 값을 잘 받아왔는지 확인 하는 코드
		log.info("uDTO null?" + (uDTO == null));
		String msg = "";
		String url = "/user/userLogin.do";
		// 값이 없으면 로그인 실패
		if (uDTO == null) {
			msg = "로그인 실패";
			url = "/user/userLogin.do";
			// 값이 있으면 로그인 성공
		} else {
			log.info("uDTO ID : " + uDTO.getUser_id());
			log.info("uDTO PWD : " + uDTO.getPassword());
			log.info("uDTO NAME : " + uDTO.getUser_name());
			msg = "로그인 성공";
			
			url = "/user/mainPage.do";
			// 성공된 로그인 정보를 세션에 담아 보내는 코드
			session.setAttribute("id", uDTO.getUser_id());
			session.setAttribute("name", uDTO.getUser_name());
		}
		// 모델 객체에 메세지와 url을 담는 코드
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		log.info(this.getClass() + "/user/userLoginProc end!!");
		// user/redirect.jsp로 리턴 해주느 코드
		return "/user/redirect";
	}

	// 로그아웃 처리 매퍼
	@RequestMapping(value = "user/userLogOut.do")
	public String logOut(HttpSession session, Model model) throws Exception {
		log.info(this.getClass() + "user/logOut start!!");

		String msg = "";
		String url = "";
		msg = "로그아웃 하시겠습니까?";
		url = "/user/userLogin.do";

		session.invalidate(); // 세션 정보 초기화

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		log.info(this.getClass() + "user/loginOut end!!");

		return "user/userLogout";
	}


	// 로그인 성공 후 메인접속 메퍼
	@RequestMapping(value = "user/mainPage.do")
	public String userMainPage(ModelMap model) throws Exception {
		log.info(this.getClass() + "메인 start!!");
		
		List<MyPageDTO> rList = userService.getMyList();
		
		if (rList==null){
			rList = new ArrayList<MyPageDTO>();
			
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
