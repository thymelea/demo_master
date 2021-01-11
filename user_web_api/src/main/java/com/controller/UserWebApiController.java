package com.controller;

import com.feginService.FeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

@RestController
public class UserWebApiController {

    @Autowired
    private FeginService feginService;

    @Resource(name = "threadPoolTaskExecutorHttp")
    private Executor threadPoolTaskExecutor;


    @GetMapping("/getName/{name}")
    public String getName(@PathVariable("name")String getName){
        return feginService.getFeginData(getName);
    }

    public <T> T getName(){
        return null;
    }


    public <T, E> List<T> sendAsync(List<List<E>> p,String name){


        List<CompletableFuture<T>> futures = new ArrayList<>();
        if(p.size() > 0){
            List<T> res = new ArrayList<>();
            for (List<E> integers : p) {
                Supplier<T> supplier = new Supplier<T>() {
                    @Override
                    public T get() {
                        return getName();
                    }
                };
                futures.add(CompletableFuture.supplyAsync(supplier,threadPoolTaskExecutor));
            }
            futures.forEach(item -> {
                try {
                    T tempRes = item.get();
                    if (tempRes != null) {
                        res.add(tempRes);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
            return res;
        }else {
            return new ArrayList<T>() {{
                T data = getName();
                if (data != null) {
                    add(data);
                }
            }};
        }


    }
}
