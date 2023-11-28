package com.example.demo2.Entity.Files;

import com.example.demo2.Entity.Boards.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class BoardFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;

    private String fileName;

    private String fileType;

    private String uuid; // -- 중복되지 않는 식별 요소

    private Long fileSize;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public BoardFile(Long id, String filePath, String fileName, String fileType, Long fileSize, Board board, String uuid) {
        this.id = id;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.uuid = uuid;
        this.board = board;
    }

}
