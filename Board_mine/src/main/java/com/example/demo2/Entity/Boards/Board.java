package com.example.demo2.Entity.Boards;

import com.example.demo2.Entity.Comments.Comment;
import com.example.demo2.Entity.Files.BoardFile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Board {

    @Id // ** pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column // = 작성자
    private String user_Name;
    @Column
    private String email;
    @Column // = 게시글 제목
    private String title;
    @Column // -- 내용
    private String content;
    @Column // - 최초 작성 시간
    private LocalDateTime createTime;
    @Column// -- 최근 수정 시간
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.LAZY) // -- 댓글
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.LAZY) // -- 첨부파일 /사진
    private List<BoardFile> boardFiles = new ArrayList<>();



    @Builder
    public Board(Long id, String user_Name, String email, String title, String content, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.user_Name = user_Name;
        this.email = email;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public void update(String title, String contents, LocalDateTime time) {
        this.content = contents;
        this.title = title;
        this.createTime = time;
        this.updateTime = LocalDateTime.now();
    }
}
