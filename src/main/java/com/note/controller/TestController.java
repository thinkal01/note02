package com.note.controller;

import com.note.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/test")
public class TestController {
    @Resource
    private TestService testService;

    public Object testTransaction() {
        return null;
    }
}

