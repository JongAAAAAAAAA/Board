package com.example.Board.controller;

import com.example.Board.constant.Method;
import com.example.Board.domain.BoardDTO;
import com.example.Board.paging.Criteria;
import com.example.Board.service.BoardService;
import com.example.Board.util.UiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller // 사용자의 요청/응답을 처리하는 컨트롤러 클래스임을 선언
public class BoardController extends UiUtils {
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
    public String registerBoard(final BoardDTO params, Model model) {
        try {
            boolean isRegistered = boardService.registerBoard(params);
            if( isRegistered == false ) {
                return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list.do", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            // TODO: handle exception
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, null, model);
        } catch (Exception e) {
            // TODO: handle exception
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, null, model);
        }

        return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/board/list.do", Method.GET, null, model);
    }

    // 게시글 목록
    @GetMapping(value = "/board/list.do")
    public String openBoardList(@ModelAttribute("params") BoardDTO params, Model model) {
        List<BoardDTO> boardList = boardService.getBoardList(params);
        model.addAttribute("boardList", boardList);

        return "board/list";
    }

    // 게시글 조회
    @GetMapping(value = "/board/view.do")
    public String openBoardDetail(@RequestParam(value = "idx", required = false) Long idx, Model model) {

        // 올바르지 않은 접근 시
        if(idx == null) {
            // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/board/list.do";
        }

        BoardDTO board = boardService.getBoardDetail(idx);

        // 없는 게시글이거나, 이미 삭제된 게시글일 경우
        if(board == null || "Y".equals(board.getDeleteYn())) {
            // TODO => 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/board/list.do";
        }

        model.addAttribute("board", board);

        return "board/view";
    }

    // 게시글 삭제
    @PostMapping(value = "/board/delete.do")
    public String deleteBoard(@RequestParam(value = "idx", required = false) Long idx, Model model) {
        if( idx == null ) {
            return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do", Method.GET, null, model);
        }

        try {
            boolean isDeleted = boardService.deleteBoard(idx);
            if( isDeleted == false ) {
                return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/board/list.do", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            // TODO: handle exception
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, null, model);

        } catch (Exception e) {
            // TODO: handle exception
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, null, model);
        }

        return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/board/list.do", Method.GET, null, model);
    }


}
