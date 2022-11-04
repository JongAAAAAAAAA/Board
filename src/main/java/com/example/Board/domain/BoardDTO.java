package com.example.Board.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDTO extends CommonDTO {

    private Long idx; //글 번호(PK)
    private String title; //글 제목
    private String content; //내용
    private String writer; //작성자
    private int viewCnt; //조회수
    private String noticeYn; //공지여부
    private String secretYn; // 비밀여부
    private String deleteYn; // 삭제여부
    private LocalDateTime insertTime; //등록일
    private LocalDateTime updateTime; //수정일
    private LocalDateTime deleteTime; //삭일
}
