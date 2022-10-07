package com.example.Board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 사용자의 요청/응답을 처리하는 컨트롤러 클래스임을 선언
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 작성 화면 GET. idx이 없으면 등록화면, idx가 있으면 수정화면
    @GetMapping(value = "/board/write.do") // get방식으로 value속성에 URI값 매핑
    public String openBoardWrite(@RequestParam(value = "idx", required = false) Long idx, Model model) { // 메서드의 파라미터로 지정된 Model 인터페이스는 데이터를 뷰로 전달
        if (idx == null){
            model.addAttribute("board", new BoardDTO());
        } else {
            BoardDTO board = boardService.getBoardDetail(idx);
            if (board == null){
                return "redirect:/board/list.do";
            }
            model.addAttribute("board", board);
        }

        return "board/write"; //접미사는 .html로, 자동 연결되기 때문에 생략
    }
    // 게시글 작성 처리 POST
    @PostMapping(value = "/board/register.do")
    public String registerBoard(final BoardDTO params) {
        try {
            boolean isRegistered = boardService.registerBoard(params);
            if (isRegistered == false) {
                // TODO => 게시글 등록에 실패하였다는 메시지를 전달
            }
        } catch (DataAccessException e) {
            // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

        } catch (Exception e) {
            // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
        }

        return "redirect:/board/list.do";
    }



}
