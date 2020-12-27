package com.controller;

import com.feginService.FeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserWebApiController {

    @Autowired
    private FeginService feginService;

    @GetMapping("/getName/{name}")
    public String getName(@PathVariable("name")String name){
        return feginService.getFeginData(name);
    }
}
