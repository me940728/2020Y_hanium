package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.MyPageDTO;

@Mapper("MyPageMapper")
public interface IMyPageMapper {

	List<MyPageDTO> getgalList();

	int insertFile(MyPageDTO pDTO);

	MyPageDTO getGalDetail(MyPageDTO pDTO);

	int UpdateState(MyPageDTO pDTO);

	
}
