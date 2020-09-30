package poly.service;

import java.util.List;

import poly.dto.MyPageDTO;


public interface IMyPageService {
	//갤러리 리스트
	List<MyPageDTO> getMyPage();
	//갤러리 게시물 업로드
	int insertFile(MyPageDTO pDTO);
	//상세내용
	MyPageDTO getimgDetail(MyPageDTO pDTO);
	//상태글
	int UpdateState(MyPageDTO pDTO);

}
