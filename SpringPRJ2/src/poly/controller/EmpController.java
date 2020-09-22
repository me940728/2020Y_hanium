package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.EmpDTO;
import poly.service.IEmpService;

@Controller
public class EmpController {

	// 로거
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "EmpService")
	IEmpService empService;
	
	
	@RequestMapping(value="scott/empList")
	public String empList(ModelMap model) {
		log.info("empList start!!");
		
		List<EmpDTO> rList = empService.getEmpList();
		
		if(rList == null) {
			rList = new ArrayList<>();
		}
		
		for(EmpDTO eDTO : rList) {
			log.info("ename : " +eDTO.getEname());
			log.info("empno : " + eDTO.getEmpno());
		}
		
		model.addAttribute("rList", rList);
		
		return "/scott/empList";
	}
	@RequestMapping(value="scott/managerList")
	public String managerList(ModelMap model) {
		log.info("managerList start!!");
		
		List<EmpDTO> rList = empService.getManagerList();
		
		if(rList == null) {
			rList = new ArrayList<>();
		}
		
		for(EmpDTO eDTO : rList) {
			log.info("ename : " +eDTO.getEname());
			log.info("empno : " + eDTO.getEmpno());
		}
		
		model.addAttribute("rList", rList);
		
		return "/scott/empList";
	}
	

	
}
