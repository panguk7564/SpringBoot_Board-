package com.example.demo2.Services;

import com.example.demo2.Entity.*;
import com.example.demo2.Entity.Files.BoardFile;
import com.example.demo2.Entity.Files.FReposit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BServices {

    private final BRepostit repostit;
    private final FReposit fReposit;

    private final String filePath =  "C:/Users/G/Desktop/DB_Files/";

    @Transactional
    public void save(BDto dto, MultipartFile[] files) throws IOException{ // -- DB에 게시글 & 파일 저장

        for(MultipartFile file : files) {
                Path uploadpath = Paths.get(filePath);

                //-- 경로 부재시 폴더 생성
                if(!Files.exists(uploadpath)){
                    Files.createDirectories(uploadpath);}

                if(!file.isEmpty()){
                    // -- 파일명 추출
                    String originFileName = file.getOriginalFilename();

                    // -- 확장자 추출
                    assert originFileName != null;
                    String formatType = originFileName.substring(originFileName.lastIndexOf("."));

                   // originFileName = originFileName.substring(0, originFileName.lastIndexOf("."));

                    System.out.println("파일명: " + originFileName);
                    System.out.println("확장자: " + formatType);

                    //-- UUID 생성
                    String uuid = UUID.randomUUID().toString();

                    String path = filePath + uuid + originFileName;

                    file.transferTo(new File(path)); // -- 경로 저장

                    Long id = repostit.save(dto.toE()).getId(); // -- 저장후 pk 받아오기
                    Board board = repostit.findById(id).get();

                    BoardFile boardFile = BoardFile.builder()
                            .filePath(filePath)
                            .fileName(originFileName)
                            .uuid(uuid)
                            .fileType(formatType)
                            .fileSize(file.getSize())
                            .board(board)
                            .build();

                    fReposit.save(boardFile);
                }
                else { // -- 파일첨부 X 일떄
                    dto.setCreateTime(LocalDateTime.now());
                    System.out.println("파일이 엄서요");
                    repostit.save(dto.toE());
                }
        }
        System.out.println("저장완료");
    }


    ////
    public Page<BDto> paging(Pageable pageable){
        int page = pageable.getPageNumber() - 1; // - 시작 인덱스
        int size = 5; // -- 페이지 표시 게시물 개수

        Page<Board> boards = repostit.findAll(PageRequest.of(page,size)); ///-- 전체게시물 불러오기(정렬해서)

        return boards.map(board -> new BDto( // -- 람다 人
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getCreateTime(),
                board.getUpdateTime()));
    }

    public BDto findEntity(Long id){
        // if(repostit.findById(id),isPresent ....... 예외처리 생략
        //Board board = repostit.findById(id).get();
        Optional<Board> board = repostit.findById(id);
        return BDto.boardDetail(board.get());
    }

    @Transactional
    public void deletecontent(Long id){
        repostit.deleteById(id);
    }

    @Transactional
    public Long update(Long id, BDto dto) {
        Board board = repostit.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        board.update(dto.getTitle(),dto.getContents(),board.getCreateTime());

        return repostit.save(board).getId();
    }

}