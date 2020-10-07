package poly.service;

import java.util.List;

import poly.dto.ClosetInfoDTO;

public interface IMyPageService {
   //갤러리 리스트
   List<ClosetInfoDTO> getMyPage();
   //갤러리 게시물 업로드
   int insertFile(ClosetInfoDTO pDTO);
   //상세내용
   ClosetInfoDTO getimgDetail(ClosetInfoDTO pDTO);
   //상태글
   int UpdateState(ClosetInfoDTO pDTO);

}