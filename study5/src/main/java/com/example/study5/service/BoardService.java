package com.example.study5.service;


import com.example.study5.dto.Board;
import com.example.study5.model.BoardReq;
import com.example.study5.model.DefaultRes;

import java.util.List;

public interface BoardService {

    DefaultRes<List<Board>> findAll();

    DefaultRes<Board> findIdx(int boardIdx);

    DefaultRes insert(BoardReq board);

}
