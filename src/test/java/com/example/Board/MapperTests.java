package com.example.Board;

import com.example.Board.domain.BoardDTO;
import com.example.Board.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@SpringBootTest
class MapperTests {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testOfInsert(){
        BoardDTO params = new BoardDTO();
        params.setTitle("1번 게시글 제목");
        params.setContent("1번 게시글 내용");
        params.setWriter("테스터");

        int result = boardMapper.insertBoard(params);
        System.out.println("결과는 " + result + "입니다.");
    }

    @Test
    public void testOfDetail(){
        BoardDTO board = boardMapper.selectBoardDetail((long) 1);
        try{
            String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);//자바에서 시간을 표현이 안 돼서 이렇게 표현한 것임.
            System.out.println("===================");
            System.out.println(boardJson);
            System.out.println("===================");
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testOfUpdate(){
        BoardDTO params = new BoardDTO();
        params.setTitle("1번 게시글 제목을 수정합니다.");
        params.setContent("1번 게시글 내용을 수정합니다.");
        params.setWriter("홍길동");
        params.setIdx((long) 1);

        int result = boardMapper.updateBoard(params);
        if (result == 1){
            BoardDTO board = boardMapper.selectBoardDetail((long) 1);

            try{
                String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);//자바에서 시간을 표현이 안 돼서 이렇게 표현한 것임.

                System.out.println("===================");
                System.out.println(boardJson);
                System.out.println("===================");
            } catch (JsonProcessingException e){
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testOfDelete(){
        int result = boardMapper.deleteBoard((long) 1);

        if (result == 1){
            BoardDTO board = boardMapper.selectBoardDetail((long) 1);

            try{
                String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);//자바에서 시간을 표현이 안 돼서 이렇게 표현한 것임.

                System.out.println("===================");
                System.out.println(boardJson);
                System.out.println("===================");
            } catch (JsonProcessingException e){
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testSelectList(){
        int boardTotalCount = boardMapper.selectBoardTotalCount();
        if (boardTotalCount > 0){
            List<BoardDTO> boardList = boardMapper.selectBoardList();
            if (CollectionUtils.isEmpty(boardList) == false){
                for (BoardDTO board : boardList){
                    System.out.println("===================");
                    System.out.println(board.getTitle());
                    System.out.println(board.getContent());
                    System.out.println(board.getWriter());
                    System.out.println("===================");
                }
            }
        }
    }

}
