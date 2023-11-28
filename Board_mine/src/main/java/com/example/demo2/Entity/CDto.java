package com.example.demo2.Entity;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CDto {

    private Long id;
    private String writer;
    private String contents;
    private Long boardId; // -- 해당 게시글 id 식별

    public Comment toEntity(){
        return Comment.builder()
                .writer(writer)
                .contents(contents)
                .build();
    }

    public static CDto toCDto(Comment comment, Long boardid){
        CDto dto = new CDto();
        dto.setId(comment.getId());
        dto.setWriter(comment.getWriter());
        dto.setContents(comment.getContents());
        dto.setBoardId(boardid);
        return dto;
    }
}
