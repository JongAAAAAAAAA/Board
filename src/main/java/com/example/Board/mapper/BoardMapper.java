package com.example.Board.mapper;

import com.example.Board.domain.BoardDTO;
import com.example.Board.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    public int insertBoard(BoardDTO params);
    public BoardDTO selectBoardDetail(Long idx);
    public int updateBoard(BoardDTO params);
    public int deleteBoard(Long idx);
    public List<BoardDTO> selectBoardList(Criteria criteria); //게시글 목록 조회
    public int selectBoardTotalCount(Criteria criteria); // delete_yn이 'N'으로 지정된 게시글 개수 조회
}
