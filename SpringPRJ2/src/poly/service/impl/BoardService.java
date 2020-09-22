package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.BoardDTO;
import poly.persistance.mapper.IBoardMapper;
import poly.service.IBoardService;

@Service("BoardService")
public class BoardService implements IBoardService{

	@Resource(name="BoardMapper")
	IBoardMapper boardMapper;
	
	@Override
	public int insertPost(BoardDTO pDTO) {
		return boardMapper.insertPost(pDTO);
	}

	@Override
	public List<BoardDTO> getBoardList() {
		
		return boardMapper.getBoardList();
	}

	@Override
	public BoardDTO getBoardDetail(BoardDTO pDTO) {
		return boardMapper.getBoardDetail(pDTO);
	}

	@Override
	public int updatePost(BoardDTO pDTO) {
		return boardMapper.updatePost(pDTO);
	}

	@Override
	public int deletePost(BoardDTO pDTO) {
		return boardMapper.deletePost(pDTO);
	}

	@Override
	public List<BoardDTO> getUserList(BoardDTO bDTO) {
		return boardMapper.getUserList(bDTO);
	}

}
