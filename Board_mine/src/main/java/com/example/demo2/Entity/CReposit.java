package com.example.demo2.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CReposit extends JpaRepository<Comment,Long> {

    List<Comment> findAllByBoardOrderByIdDesc(Board board);
}
