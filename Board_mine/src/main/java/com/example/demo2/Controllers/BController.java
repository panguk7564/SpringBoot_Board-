package com.example.demo2.Controllers;

import com.example.demo2.Entity.Boards.BDto;
import com.example.demo2.Entity.Files.BoardFile;
import com.example.demo2.Entity.Files.FReposit;
import com.example.demo2.Services.BServices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BController {

    private final BServices services;
    private final FReposit fReposit;


    @PostMapping("/save") // -- 저장
    public String save(@ModelAttribute BDto dto, @RequestParam MultipartFile[] files) throws IOException {
        System.out.println("○ 게시글 저장 ○");
        services.save(dto, files);

        return "redirect:/board/paging";
    }

    @GetMapping("/paging") // -- 게시판 페이지
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<BDto> boards = services.paging(pageable);

        int blockLimit = 3;
        int startPage = (int)Math.ceil((double)pageable.getPageNumber()/blockLimit - 1) * blockLimit + 1;
        int endPage = ((startPage+blockLimit - 1) < boards.getTotalPages()) ? (startPage + blockLimit - 1) : boards.getTotalPages();

        model.addAttribute("boardList",boards);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "paging";
    }

    @GetMapping("/{id}") // -- 게시글 상세
    public String paginging(@PathVariable Long id, Model model, @PageableDefault(page = 1) Pageable pageable){
        BDto dto = services.findEntity(id);
        model.addAttribute("board",dto);
        model.addAttribute("page",pageable.getPageNumber());

        List<BoardFile> files = fReposit.findByBoardId(id);
        model.addAttribute("files",files);

        return "detail";
    }

    @GetMapping("/delete/{id}") // -- 게시글 삭제
    public String deleteContents(@PathVariable Long id){
        services.deletecontent(id);
        return "redirect:/board/paging";
    }

    @GetMapping("update/{id}") // -- 게시글 수정 페이지 출력
    public String updateContent(@PathVariable Long id, Model model){
        BDto dto = services.findEntity(id);
        model.addAttribute("board",dto);

        return "edit";
    }


    @PostMapping("edit/{id}") // -- 게사글 수정 완료
    public String update(@PathVariable Long id,@ModelAttribute BDto dto, @RequestParam MultipartFile[] files) throws IOException{
        services.update(id,dto,files);
        System.out.println("○ 게시글 수정 ○");

        return "redirect:/board/paging";
    }
}