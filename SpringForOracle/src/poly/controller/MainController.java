package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    // 인덱스 접속을 위한 컨트러 
	// MVC에서 C를 담당
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "index")
	public String Index() {
		log.info(this.getClass());

		return "/index";
	}

	@RequestMapping(value = "practice")
	public String practice() {
		
		log.info("practice 시작!!");

		return "/practice";
	}
}
