package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dto.MailDTO;
import poly.service.IMailService;
import poly.util.CmmUtil;


@Controller
public class MailController {
	// 로그 작성 인스턴스 생성
	private Logger log = Logger.getLogger(this.getClass());
	
	// 메일 서비스 리소스로 메로리 올리기
	@Resource(name = "MailService")
	private IMailService mailService;
	
	// 메일 보내는 페이지 접속 컨트롤러
	@RequestMapping(name="mail/sendMailAct")
	public String SendMailAct() {
		log.info(this.getClass().getName() + "메일 전송 페이지 접속 성공");
		return "/mail/sendMailPage";
	}
	
	// 메일 보내기 로직 컨트롤러
	@RequestMapping(value="mail/goMail.do", method = RequestMethod.POST)
	public String SendMail(HttpServletRequest requset, HttpServletResponse response, ModelMap model) throws Exception{
		
		log.info(this.getClass().getName() + "mail.sendMail start!!!");
		
		String toMail = CmmUtil.nvl(requset.getParameter("toMail"));
		String title = CmmUtil.nvl(requset.getParameter("title"));
		String contents = CmmUtil.nvl(requset.getParameter("contents"));
		
		// DTO 메모리에 올리기
		MailDTO pDTO = new MailDTO();
		pDTO.setToMail(toMail);
		pDTO.setTitle(title);
		pDTO.setContents(contents);
		
		// 메일 발송하기
		int res = mailService.doSendMail(pDTO);
		
		if(res == 1) {
			log.info(this.getClass().getName() + "메일 보내기 성공");
		} else {
			log.info(this.getClass().getName() + "메일 보내기 실패 ㅜㅜ");
		}
		
		model.addAttribute("res", String.valueOf(res));
		log.info(this.getClass().getName() + "메일 보내기 종료");
		// 메모리 비우기
		pDTO = null;
		
		return "/mail/sendMailResult";
	}
			
}
