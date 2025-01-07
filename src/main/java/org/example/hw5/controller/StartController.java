package org.example.hw5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StartController {

    @ResponseBody
    @GetMapping
    public String getStart(){
        return "Start Page!!!";
    }

}
