package poly.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.BoardDTO;
import poly.service.IBoardService;
import poly.util.CmmUtil;

@Controller
public class BoardController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="BoardService")
	IBoardService boardService;
	
	@RequestMapping(value="board/boardList")
	public String boardList(ModelMap model) {
		log.info("boardList start!!");
		
		List<BoardDTO> rList = boardService.getBoardList();
		
		model.addAttribute("rList", rList);
		
		return "/board/boardList";
	}
	
	
	@RequestMapping(value="board/newPost")
	public String newPost() {
		log.info("newPost start!!");
		return "/board/newPost";
	}
	
	@RequestMapping(value="board/doPost.do")
	public String doPost(HttpServletRequest request, ModelMap model) {
		
		// 임시로 설정하는 아이디
		String id = "admin";
		
		// 요청으로부터 받은 파라미터를 변수에 저장
		String post_title = request.getParameter("post_title");
		String post_content = request.getParameter("post_content");
		
		//게시자, 게시글 제목, 게시글 내용을 담아 서비스에 전송할 DTO객체 생성
		BoardDTO pDTO = new BoardDTO();
		
		pDTO.setReg_id(id);
		pDTO.setPost_title(post_title);
		pDTO.setPost_content(post_content);
		
		int res = boardService.insertPost(pDTO);
		
		String msg;
		String url = "/board/boardList.do";
		
		// 게시글 등록 성공할 경우 res에 0보다 큰 수가 저장됨
		if(res>0) {
			msg = "등록에 성공했습니다.";
		
		// res가 0일 경우 등록에 실패한 것
		}else {
			msg = "등록에 실패했습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/redirect";
		
	}
	
	@RequestMapping(value="board/boardDetail")
	public String boardDetail(HttpServletRequest request, ModelMap model) {
		log.info("boardDetail start!!");
		
		// 게시글 번호 받아오기
		String post_no = request.getParameter("no");
		
		BoardDTO pDTO = new BoardDTO();
		pDTO.setPost_no(post_no);
		
		BoardDTO rDTO = boardService.getBoardDetail(pDTO);
		
		// 결과가 없을 경우 메시지와 함께 게시글 목록으로 리다이렉트
		if(rDTO==null) {
			model.addAttribute("msg", "존재하지 않는 게시물입니다.");
			model.addAttribute("url", "/board/boardList.do");
			return "/redirect";
		}
		
		model.addAttribute("rDTO", rDTO);
		
		return "/board/boardDetail";
	}
	
	@RequestMapping(value="board/editPost")
	public String editPost(HttpServletRequest request, ModelMap model) {
		log.info("boardEdit start!!");

		// 게시글 번호 받아오기
		String post_no = request.getParameter("no");
		
		BoardDTO pDTO = new BoardDTO();
		pDTO.setPost_no(post_no);
		
		BoardDTO rDTO = boardService.getBoardDetail(pDTO);
		
		// 결과가 없을 경우 메시지와 함께 게시글 목록으로 리다이렉트
		if(rDTO==null) {
			model.addAttribute("msg", "존재하지 않는 게시물입니다.");
			model.addAttribute("url", "/board/boardList.do");
			return "/redirect";
		}
		
		model.addAttribute("rDTO", rDTO);
		
		return "/board/editPost";
	}
	
	@RequestMapping(value="board/doEditPost.do")
	public String doEditPost(HttpServletRequest request, ModelMap model) {
		
		
		// 요청으로부터 받은 파라미터를 변수에 저장
		String post_title = request.getParameter("post_title");
		String post_content = request.getParameter("post_content");
		String post_no = request.getParameter("post_no");
		
		//게시자, 게시글 제목, 게시글 내용을 담아 서비스에 전송할 DTO객체 생성
		BoardDTO pDTO = new BoardDTO();
		
		pDTO.setPost_title(post_title);
		pDTO.setPost_content(post_content);
		pDTO.setPost_no(post_no);
		
		int res = boardService.updatePost(pDTO);
		
		String msg;
		String url = "/board/boardList.do";
		
		// 게시글 등록 성공할 경우 res에 0보다 큰 수가 저장됨
		if(res>0) {
			msg = "편집에 성공했습니다.";
		
		// res가 0일 경우 등록에 실패한 것
		}else {
			msg = "편집에 실패했습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/redirect";
		
	}
	
	
	@RequestMapping(value="board/deletePost")
	public String deletePost(HttpServletRequest request, ModelMap model) {
		log.info("boardDelete start!!");
		
		// 게시글 번호 받아오기
		String post_no = request.getParameter("no");
		
		BoardDTO pDTO = new BoardDTO();
		pDTO.setPost_no(post_no);
		
		int res = boardService.deletePost(pDTO);
		
		String msg;
		String url = "/board/boardList.do";
		
		// 게시글 등록 성공할 경우 res에 0보다 큰 수가 저장됨
		if(res>0) {
			msg = "삭제에 성공했습니다.";
		
		// res가 0일 경우 등록에 실패한 것
		}else {
			msg = "삭제에 실패했습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/redirect";
	}
		
		@RequestMapping(value="board/userSearchList.do")
		public @ResponseBody List<BoardDTO> userSearchList(HttpServletRequest request) throws Exception {
			log.info(this.getClass() + ".user/userSearchList start!!");
		
			String title = CmmUtil.nvl(request.getParameter("title"));
			log.info("title" + title);
			
			BoardDTO bDTO = new BoardDTO();
			bDTO.setPost_title(title);
			
			List<BoardDTO> uList = boardService.getUserList(bDTO);
					
			log.info("uList size : " + uList.size());
			
			log.info(this.getClass() + ".user/userSearchList end!!");
			
			return uList;
		
		
	}


}

