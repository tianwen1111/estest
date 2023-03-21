package com.an.init;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PostConstructInit {

    @PostConstruct
    public void init() {
        System.out.println("gateway route init...");
        //log.info("gateway route init...");
    }

}
