package com.example.demo2.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController { // -- html만 전환하는 컨트롤러만 따로 모아둠
    @GetMapping("/")
    public String home(){
        return "main";
    }

    @GetMapping("/board/create")
    public String board(){
        return "create";
    }

}
