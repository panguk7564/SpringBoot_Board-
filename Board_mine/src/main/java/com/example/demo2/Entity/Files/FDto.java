package com.example.demo2.Entity.Files;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FDto {

    private Long id;

    private String filePath;

    private String fileName;

    private String uuid; // -- Random_Key

    private String fileType;

    private Long fileSize;


    private Long boardId;

    public BoardFile toEntity(){
        return BoardFile.builder()
                .fileName(fileName)
                .filePath(filePath)
                .fileType(fileType)
                .fileSize(fileSize)
                .uuid(uuid)
                .build();
    }

}
