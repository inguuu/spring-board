package com.example.study5.api;


import com.example.study5.dto.Board;
import com.example.study5.model.BoardReq;
import com.example.study5.model.DefaultRes;
import com.example.study5.model.Test;
import com.example.study5.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/board")
    public ResponseEntity findAllBoards() {
        try {
            return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            DefaultRes<Board> ISR = new DefaultRes<>(HttpStatus.INTERNAL_SERVER_ERROR,"서버 내부 오류" );
            return new ResponseEntity<>(ISR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/board/{boardIdx}")
    public ResponseEntity findIdxBoard(@PathVariable int boardIdx) {
        try {
            log.info(boardIdx+"");
            return new ResponseEntity<>(boardService.findIdx(boardIdx), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            DefaultRes<Board> ISR = new DefaultRes<>(HttpStatus.INTERNAL_SERVER_ERROR,"서버 내부 오류" );
            return new ResponseEntity<>(ISR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/board")
    public ResponseEntity insertBoard(BoardReq boardreq, @RequestPart(value ="profile",required = false) MultipartFile profile) {
        try {
            //log.info(profile+"=======");
            if(profile != null) boardreq.setProfile(profile);
            return new ResponseEntity<>(boardService.insert(boardreq), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            DefaultRes<Board> ISR = new DefaultRes<>(HttpStatus.INTERNAL_SERVER_ERROR,"서버 내부 오류" );
            return new ResponseEntity<>(ISR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/test")
    public ResponseEntity test(Test test, @RequestPart(value ="profile",required = false) MultipartFile profile) {
        if(profile != null) test.setProfile(profile);
        return new ResponseEntity<>(boardService.test(test), HttpStatus.OK);

    }

}
