package com.example.demo2.Entity.Boards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BDto {

    private Long id;
    @Column
    private String title;
    @Column
    private String Contents;
    @Column
    private LocalDateTime createTime;
    @Column
    private LocalDateTime updatetime;

    public Board toE(){
        return Board.builder()
                .content(Contents)
                .title(title)
                .createTime(createTime)
                .updateTime(LocalDateTime.now())
                .build();
    }

    public static BDto boardDetail(Board board){
        return new BDto(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getCreateTime(),
                board.getUpdateTime()
        );
    }
}