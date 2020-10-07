package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.ClosetInfoDTO;

@Mapper("MyPageMapper")
public interface IMyPageMapper {

   List<ClosetInfoDTO> getMyPage();

   int insertFile(ClosetInfoDTO pDTO);

   int UpdateState(ClosetInfoDTO pDTO);

   ClosetInfoDTO getimgDetail(ClosetInfoDTO pDTO);

   int insertFirst(ClosetInfoDTO pDTO);
   
}