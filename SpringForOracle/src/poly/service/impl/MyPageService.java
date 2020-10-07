package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.ClosetInfoDTO;
import poly.persistance.mapper.IMyPageMapper;
import poly.service.IMyPageService;

@Service("MyPageService")
public class MyPageService implements IMyPageService {

   
   @Resource(name="MyPageMapper")
   IMyPageMapper MyPageMapper;
   
   //갤러리 리스트
   @Override
   public List<ClosetInfoDTO> getMyPage() {
      return MyPageMapper.getMyPage();
   }

   //갤러리 게시물 업로드
   @Override
   public int insertFile(ClosetInfoDTO pDTO) {
      return MyPageMapper.insertFile(pDTO);
   }

   @Override
   public ClosetInfoDTO getimgDetail(ClosetInfoDTO pDTO) {
      return  MyPageMapper.getimgDetail(pDTO);
   }

   @Override
   public int UpdateState(ClosetInfoDTO pDTO) {
      return  MyPageMapper.UpdateState(pDTO);
   }

}