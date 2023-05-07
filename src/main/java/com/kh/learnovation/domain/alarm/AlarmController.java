package com.kh.learnovation.domain.alarm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlarmController {

    @GetMapping("/socket")
    public String TestView(){
        return "socketTest";
    }
}
