package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.MyPageDTO;
import poly.dto.UserInfoDTO;
import poly.persistance.mapper.IMyPageMapper;
import poly.service.IMyPageService;

@Service("MyPageService")
public class MyPageService implements IMyPageService {

   
   @Resource(name="MyPageMapper")
   IMyPageMapper MyPageMapper;
   
   //갤러리 리스트
   @Override
   public List<MyPageDTO> getMyPage() {
      return MyPageMapper.getgalList();
   }

   //갤러리 게시물 업로드
   @Override
   public int insertFile(MyPageDTO pDTO) {
      return MyPageMapper.insertFile(pDTO);
   }

   @Override
   public MyPageDTO getimgDetail(MyPageDTO pDTO) {
      return  MyPageMapper.getGalDetail(pDTO);
   }

   @Override
   public int UpdateState(MyPageDTO pDTO) {
      return  MyPageMapper.UpdateState(pDTO);
   }

   @Override
   public int UpDateMynb(UserInfoDTO PDTO) {
      return MyPageMapper.UpDateMynb(PDTO);
   }


}