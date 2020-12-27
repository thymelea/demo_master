package com.feginService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("baseuser")
public interface FeginService {

    @GetMapping("/getUserName/{userName}")
    String getFeginData(@PathVariable("userName")String name);
}
