package com.example.study4.service;


import com.example.study4.dto.Board;
import com.example.study4.model.DefaultRes;

import java.util.List;

public interface BoardService {

    DefaultRes<List<Board>> findAll();

    DefaultRes<Board> findIdx(int boardIdx);

    DefaultRes insert(Board board);

}
