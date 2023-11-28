package com.example.demo2.Entity.Comments;

import com.example.demo2.Entity.Boards.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CReposit extends JpaRepository<Comment,Long> {

    List<Comment> findAllByBoardOrderByIdDesc(Board board); // -- 찾은후 아이디 기준으로 정렬
}
