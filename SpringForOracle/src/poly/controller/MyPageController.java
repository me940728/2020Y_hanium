package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import poly.dto.MyPageDTO;
import poly.dto.UserInfoDTO;
import poly.service.IMyPageService;

@Controller
public class MyPageController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "MyPageService")
	IMyPageService MyPageService;

	// 갤러리 컨트롤러(게시판 목록)
	@RequestMapping(value = "/MyPage/MyPage")
	public String MyPageList(ModelMap model, HttpSession session) {

		log.info("gal start");

		// 공지사항 리스트 가져오기
		List<MyPageDTO> rList = MyPageService.getMyPage();
		log.info("1" + rList);
		// 가져올 값이 없다면
		if (rList.isEmpty()) {
			rList = new ArrayList<MyPageDTO>();// 메모리에 올려줘라
		}
		log.info("2" + rList);
		// 조회된 리스트 결과값 넣어주기
		model.addAttribute("rList", rList);
		// 변수 초기화(메모리 효율화 시키기 위해 사용함)
		rList = null;

		log.info("gal end");
		// 함수 처리가 끝나고 보여줄 JSP 파일명(/WEB-INF/view/MyPage/galList.jsp)
		return "/MyPage/MyPage";
	}

	/**
	 * 게시판 작성 페이지 이동
	 * 
	 * 이 함수는 게시판 작성 페이지로 접근하기 위해 만듬. WEB-INF 밑에 존재하는 JSP는 직접 호출할 수 없음 따라서 WEB-INF 밑에
	 * 존재하는 JSP를 호출하기 위해서는 반드시 Controller 등록해야함
	 */
	@RequestMapping(value = "/MyPage/FileUPdate")
	public String FileUPdate() {
		log.info("update start");
		return "/MyPage/FileUPdate";
	}

	// 갤러리 컨트롤러(게시판 등록 및 등록 성공 여부 알림)
	@RequestMapping(value = "/MyPage/FilePost", method = RequestMethod.POST)
	public String FilePost(MultipartHttpServletRequest request, ModelMap model, HttpSession session) {

		log.info("FilePost start");
		// 이미지 삽입(경로) 파라미터 생성
		String file_content = request.getParameter("file_content");

		// 이미지 삽입(파일명) 파라미터 생성
		String file_name = request.getParameter("file_name");

		// 파라미터 이름이 file_content이 업로드 파일 정보를 구하기 위한 변수 생성(file_content은 null)
		MultipartFile File = request.getFile("file_content");

		// 이미지 파일이 들어갈 경로 설정
		String path = "C:\\Users\\Byeol Kyu CHOI\\git\\2020Y_hanium\\SpringForOracle\\WebContent\\fileFolder\\";
		String originFileName = File.getOriginalFilename(); // 원본 파일 명

		// 파일이 잘 들어 갔는지 원본 파일명과 사이즈 체크
		System.out.println("originFileName : " + originFileName);

		// 중복파일 업로드 방지를 위해 원본파일명 앞에 현재 시간 입력
		file_content = path + System.currentTimeMillis() + originFileName; // 경로 부분
		file_name = System.currentTimeMillis() + originFileName; // 파일이름

		// 파일 데이퍼를 safeFile 방식으로 저장
		try {
			File.transferTo(new java.io.File(file_content));
		} catch (Exception e) { // 예외 처리

			e.printStackTrace(); // 에러의 근거지 메시지 출력
		}

		String email = (String) session.getAttribute("email");
		log.info("email" + email);
		// 게시판 제목, 내용, 작성자 입력 파라미터 생성
		String gal_name = request.getParameter("gal_name");
		String gal_content = request.getParameter("gal_content");

		String gal_rep = request.getParameter("gal_rep");

		MyPageDTO pDTO = new MyPageDTO(); // MyPageDTO 생성

		pDTO.setGal_name(gal_name); // MyPageDTO 객체에 속성 설정(값 삽입)
		pDTO.setGal_content(gal_content);
		pDTO.setEmail(email);
		pDTO.setGal_rep(gal_rep);

		pDTO.setFile_content(file_content);
		pDTO.setFile_name(file_name);

		// 개인게시물번호 증가
		String my_nb = request.getParameter("my_nb");
		UserInfoDTO PDTO = new UserInfoDTO(); // UserInfoDTO 생성
		PDTO.setEmail(email);
		PDTO.setMy_nb(my_nb);
		// 개인게시물번호 증가

		// 값이 잘들어갔는지 확인하기 위한 변수 생성
		int result = MyPageService.insertFile(pDTO);

		result = MyPageService.UpDateMynb(PDTO);

		// 값이 잘들어 갔는지 로그로 확인
		log.info(gal_name);
		log.info(email);
		log.info(file_content);
		log.info(file_name);
		log.info(gal_rep);
		log.info(my_nb);

		String msg = "";
		String url = "/MyPage/MyPage.do";

		log.info("FileUPdate end");

		if (result < 1) {
			msg = "등록에 실패했습니다";
		} else {
			msg = "등록에 성공했습니다";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/redirect";
	}

	/**
	 * 상채글 작성 페이지 이동
	 * 
	 * 이 함수는 게시판 작성 페이지로 접근하기 위해 만듬. WEB-INF 밑에 존재하는 JSP는 직접 호출할 수 없음 따라서 WEB-INF 밑에
	 * 존재하는 JSP를 호출하기 위해서는 반드시 Controller 등록해야함
	 */
	@RequestMapping(value = "/MyPage/StateUPdate")
	public String StateUPdate(ModelMap model, HttpSession session) {
		log.info("StateUPdate start");
		// 공지사항 리스트 가져오기
		List<MyPageDTO> rList = MyPageService.getMyPage();
		log.info("1" + rList);
		// 가져올 값이 없다면
		if (rList.isEmpty()) {
			rList = new ArrayList<MyPageDTO>();// 메모리에 올려줘라
		}
		log.info("2" + rList);
		// 조회된 리스트 결과값 넣어주기
		model.addAttribute("rList", rList);
		// 변수 초기화(메모리 효율화 시키기 위해 사용함)
		rList = null;
		log.info("StateUPdate end");
		return "/MyPage/StateUPdate";
	}

	// 상태글 등록
	@RequestMapping(value = "/MyPage/StatePost", method = RequestMethod.POST)
	public String StatePost(HttpServletRequest request, ModelMap model) {

		String my_content = request.getParameter("my_content");

		MyPageDTO pDTO = new MyPageDTO(); // MyPageDTO 생성

		// MyPageDTO 객체에 속성 설정(값 삽입)
		pDTO.setMy_content(my_content);

		// 값이 잘들어갔는지 확인하기 위한 변수 생성
		int result = MyPageService.UpdateState(pDTO);

		// 값이 잘들어 갔는지 로그로 확인
		log.info(my_content);

		String msg = "";
		String url = "/MyPage/MyPage.do";

		log.info("StatePost end");

		if (result < 1) {
			msg = "수정에 실패했습니다";
		} else {
			msg = "수정에 성공했습니다";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/redirect";
	}

	// 게시판 상세보기
	@RequestMapping(value = "/MyPage/imgDetail")
	public String GalDetail(HttpServletRequest request, ModelMap model) {
		log.info("imgDetail start");

		// 게시판 번호 가져오기
		String gal_nb = request.getParameter("gal_nb");

		log.info("GalDetail start1");

		MyPageDTO pDTO = new MyPageDTO(); // MyPageDTO 생성

		// MyPageDTO 객체에 속성 설정(값 삽입)
		pDTO.setGal_nb(gal_nb);

		log.info("imgDetail start2");

		// 값을 가져오기 위한 변수 생성
		MyPageDTO rDTO = MyPageService.getimgDetail(pDTO);

		log.info("imgDetail start3");

		// 결과값이 없으면 주는 msg랑 이동하는 장소
		if (rDTO == null) {
			model.addAttribute("msg", "존재하지 않는 게시물");
			model.addAttribute("url", "/MyPage/galList.do");
			return "/redirect";
		}

		log.info(rDTO.getGal_name());
		log.info(rDTO.getGal_content());

		model.addAttribute("rDTO", rDTO);

		log.info("imgDetail end");

		return "/MyPage/imgDetail";
	}

}