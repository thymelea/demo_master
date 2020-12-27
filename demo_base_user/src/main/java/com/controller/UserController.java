package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/getUserName/{userName}")
    public String getUserName(@PathVariable(value = "userName")String name){

        return name+"00000";
    }
}
