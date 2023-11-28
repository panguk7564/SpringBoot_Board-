package com.example.demo2.Controllers;

import com.example.demo2.Entity.CDto;
import com.example.demo2.Entity.Comment;
import com.example.demo2.Services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService service;

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CDto dto){
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
