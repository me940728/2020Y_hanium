package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.UserInfoDTO;
import poly.service.IUserInfoService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;


@Controller
public class UserInfoController {
		
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name ="UserInfoService")
	private IUserInfoService userInfoService;

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
	
	   //이메일 찾기 로직
	   @RequestMapping(value="user/doFindId")
	   public String doFindId(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
	      log.info(this.getClass().getName() + "user/doFindId start");
	      
	      UserInfoDTO pDTO = new UserInfoDTO();
	      UserInfoDTO uDTO = new UserInfoDTO();
	      try {
	         String user_name = CmmUtil.nvl(request.getParameter("user_name"));
	         String email = CmmUtil.nvl(EncryptUtil.encAES128CBC(request.getParameter("email")));

	         
	         
	         log.info("user_name : " + user_name);
	         log.info("email : " + email);
	         
	         
	         //pDTO = new UserInfoDTO();
	         log.info("DTO 메모리 할당");
	         
	         pDTO.setUser_name(user_name);
	         pDTO.setEmail(email);
	         log.info("DTO에 내용 삽입 성공");
	         
	         
	         
	        // uDTO = userInfoService.doFindId(pDTO);
	            
	      } catch (Exception e) {
	         log.info(e.toString());
	         e.printStackTrace();
	      } finally {
	         log.info(this.getClass().getName() + "doFindId end");
	         
	         model.addAttribute("uDTO", uDTO);
	         log.info("uDTO null?" + (uDTO == null));
	         log.info("getUser" + uDTO.getUser_id());
	         pDTO = null;
	         
	      }
	      return "/user/findIdRes";
	      
	      
	   }

}
