package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.BoardDTO;

@Mapper("BoardMapper")
public interface IBoardMapper {

	int insertPost(BoardDTO pDTO);

	List<BoardDTO> getBoardList();

	BoardDTO getBoardDetail(BoardDTO pDTO);

	int updatePost(BoardDTO pDTO);

	int deletePost(BoardDTO pDTO);

	List<BoardDTO> getUserList(BoardDTO bDTO);
	
}

