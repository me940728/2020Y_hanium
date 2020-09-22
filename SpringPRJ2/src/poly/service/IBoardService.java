package poly.service;

import java.util.List;

import poly.dto.BoardDTO;

public interface IBoardService {

	int insertPost(BoardDTO pDTO);

	List<BoardDTO> getBoardList();

	BoardDTO getBoardDetail(BoardDTO pDTO);

	int updatePost(BoardDTO pDTO);

	int deletePost(BoardDTO pDTO);

	List<BoardDTO> getUserList(BoardDTO bDTO);

}
