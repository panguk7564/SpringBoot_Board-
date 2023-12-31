package com.example.demo2.Services;

import com.example.demo2.Entity.Boards.BRepostit;
import com.example.demo2.Entity.Boards.Board;
import com.example.demo2.Entity.Comments.CDto;
import com.example.demo2.Entity.Comments.CReposit;
import com.example.demo2.Entity.Comments.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CReposit reposit;
    private final BRepostit bRepostit;

    @Transactional
    public Comment save(CDto dto) { // -- DTO에 저장된 해당 엔티티의 정보를 가져와서 비교후 저장
        System.out.println(dto.getBoardId());
        Optional<Board> optionalBoard = bRepostit.findById(dto.getBoardId());

        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();

            Comment entity = dto.toEntity();
            entity.toUpdate(board);

            return reposit.save(entity);
        } else {
            return null;
        }
    }
    public List<CDto> findAll(Long boardid) { //-- 해당 보드에 입력된 모든 댓글을 리스트로 반환
        Board boardEntity = bRepostit.findById(boardid).get();
        List<Comment> commentList = reposit.findAllByBoardOrderByIdDesc(boardEntity);

        List<CDto> dtolist = new ArrayList<>();
        for(Comment commententity: commentList){
            CDto cDto = CDto.toCDto(commententity, boardid);
            dtolist.add(cDto);
        }
        return dtolist;
    }
}