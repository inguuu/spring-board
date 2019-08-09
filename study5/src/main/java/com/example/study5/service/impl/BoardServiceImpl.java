package com.example.study5.service.impl;

import com.example.study5.dto.Board;
import com.example.study5.mapper.BoardMapper;
import com.example.study5.model.BoardReq;
import com.example.study5.model.DefaultRes;
import com.example.study5.model.Test;
import com.example.study5.service.BoardService;
import com.example.study5.service.S3FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final S3FileUploadService s3FileUploadService;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper, S3FileUploadService s3FileUploadService) {
        this.boardMapper = boardMapper;
        this.s3FileUploadService = s3FileUploadService;
    }


    @Override
    public DefaultRes<List<Board>> findAll() {
        List<Board> boardList = boardMapper.findAll();
        if(boardList.isEmpty()){
            return  DefaultRes.res(HttpStatus.NO_CONTENT.value(),"유저 정보가 없습니다.");
        }else{
            return DefaultRes.res(HttpStatus.OK.value(), "조회 성공", boardList);
        }
    }

    @Override
    public DefaultRes<Board> findIdx(int boardIdx) {
        log.info("=============="+boardIdx);
        Board board = boardMapper.findIdx(boardIdx);
        if(board==null){
            return DefaultRes.res(HttpStatus.NO_CONTENT.value(), "인덱스와 맞는 게시글이 없습니다.");
        }else{
            return DefaultRes.res(HttpStatus.OK.value(), "조회 성공",board);
        }

    }


    @Override
    public DefaultRes insert(BoardReq boardreq) {
        try {
            boardreq.setUrl(s3FileUploadService.upload(boardreq.getProfile()));
            log.info(boardreq.getUrl());
            boardMapper.insert(boardreq);
            return DefaultRes.res(HttpStatus.OK.value(), "등록 성공");
        } catch (IOException e) {
            e.printStackTrace();
            return DefaultRes.res(500, "DB 에러");
        }

    }

    @Override
    public DefaultRes test(Test test) {
        try {
            test.setUrl(s3FileUploadService.upload(test.getProfile()));
            log.info(test.getUrl());
            boardMapper.test(test);
            return DefaultRes.res(HttpStatus.OK.value(), "등록 성공");
        } catch (IOException e) {
            e.printStackTrace();
            return DefaultRes.res(HttpStatus.OK.value(), "DB 에러");
        }
    }
}
