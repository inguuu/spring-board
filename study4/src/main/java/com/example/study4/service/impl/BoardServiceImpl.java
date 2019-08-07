package com.example.study4.service.impl;

import com.example.study4.dto.Board;
import com.example.study4.mapper.BoardMapper;
import com.example.study4.model.DefaultRes;
import com.example.study4.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
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
    public DefaultRes insert(Board board) {
         boardMapper.insert(board);
        return DefaultRes.res(HttpStatus.OK.value(), "등록 성공");
    }
}
