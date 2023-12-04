package com.example.demo2.Controllers;

import com.example.demo2.Entity.Comments.CDto;
import com.example.demo2.Entity.Comments.Comment;
import com.example.demo2.Services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService service;

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CDto dto){ // -- 리스트로 반환된 댓글 엔티티를 모두 반환 (댓글 출력)
        Comment comment = service.save(dto);

        List<CDto> all = service.findAll(dto.getBoardId());

        if(comment != null){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        else {
            return new ResponseEntity("게시글 엄서요", HttpStatus.NOT_FOUND);
        }
    }
}
