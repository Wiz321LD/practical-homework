package org.example.hw6.controller;

import org.custom.service.CustomLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom")
public class CustomLibController {

    private final CustomLibService CUSTOM_LIB_SERVICE;

    @Autowired
    public CustomLibController(CustomLibService customLibService) {
        CUSTOM_LIB_SERVICE = customLibService;
    }


    @ResponseBody
    @GetMapping("/prop")
    public String getStringPage(){
        return CUSTOM_LIB_SERVICE.generateConfigPropertiesString();
    }

}
