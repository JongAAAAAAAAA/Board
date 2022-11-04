package com.example.Board.service;

import com.example.Board.domain.BoardDTO;
import com.example.Board.paging.Criteria;

import java.util.List;

public interface BoardService {
    public boolean registerBoard(BoardDTO params);
    public BoardDTO getBoardDetail(Long idx);
    public boolean deleteBoard(Long idx);
    public List<BoardDTO> getBoardList(BoardDTO params);


}
