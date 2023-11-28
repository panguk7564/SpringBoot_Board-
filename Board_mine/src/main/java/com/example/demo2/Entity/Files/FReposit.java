package com.example.demo2.Entity.Files;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FReposit extends JpaRepository<BoardFile,Long>{
    List<BoardFile> findByBoardId(Long boardId); // -- 저장된 게시글 id 조회
}
