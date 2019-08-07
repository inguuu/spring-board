package com.example.study4.mapper;

import com.example.study4.dto.Board;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface BoardMapper {

    @Select("SELECT * FROM board")
    List<Board> findAll();

    @Select("SELECT * FROM board WHERE board_idx =#{boardIdx}")
    Board findIdx(@Param("boardIdx") int boardIdx);

    @Insert("INSERT INTO board(title,content) VALUES(#{board.title},#{board.content})")
    void insert(@Param("board") Board board);
}
