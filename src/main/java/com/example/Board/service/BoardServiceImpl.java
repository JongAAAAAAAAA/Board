package com.example.Board.service;

import com.example.Board.domain.BoardDTO;
import com.example.Board.mapper.BoardMapper;
import com.example.Board.paging.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service // 비즈니스 로직을 담당하는 서비스 클래임을 선언
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public boolean registerBoard(BoardDTO params) {
        int queryResult = 0;

        if (params.getIdx() == null){
            // 쿼리가 정상적으로 실행되면 1을 반환함. (insert 성공 시 1 반환 (mybatis 문법))
            queryResult = boardMapper.insertBoard(params);
        } else {
            queryResult = boardMapper.updateBoard(params);
        }
        // 정상적으로 실행되면(1이 반환됐으면) true, 아니면(:) false로 반환
        return (queryResult == 1) ? true : false;
    }

    @Override
    public BoardDTO getBoardDetail(Long idx) {
        return boardMapper.selectBoardDetail(idx);
    }

    @Override
    public boolean deleteBoard(Long idx) {
        int queryResult = 0;

        BoardDTO board = boardMapper.selectBoardDetail(idx);

        if (board != null && "N".equals(board.getDeleteYn())){
            queryResult = boardMapper.deleteBoard(idx);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<BoardDTO> getBoardList(Criteria criteria) {
        List<BoardDTO> boardList = Collections.emptyList();

        int boardTotalCount = boardMapper.selectBoardTotalCount(criteria);

        if (boardTotalCount > 0) {
            boardList = boardMapper.selectBoardList(criteria);
        }

        return boardList;
    }
}