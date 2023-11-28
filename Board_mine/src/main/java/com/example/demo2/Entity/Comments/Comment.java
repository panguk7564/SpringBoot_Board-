package com.example.demo2.Entity.Comments;

import com.example.demo2.Entity.Boards.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String writer;
    @Column
    private String contents;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(Long id, String writer, String contents, Board board) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
        this.board = board;
    }

    public Comment toUpdate(Board board){
        Comment comment = new Comment();
        this.board = board;
        return comment;
    }
}
