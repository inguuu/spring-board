package com.example.study5.mapper;

import com.example.study5.dto.Board;
import com.example.study5.model.BoardReq;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("SELECT * FROM board")
    List<Board> findAll();

    @Select("SELECT * FROM board WHERE board_idx =#{boardIdx}")
    Board findIdx(@Param("boardIdx") int boardIdx);

    @Insert("INSERT INTO board(title,content) VALUES(#{board.title},#{board.content})")
    void insert(@Param("board") BoardReq board);
}
