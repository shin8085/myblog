package com.shin.controller;

import com.shin.service.BlogService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Resource
    BlogService blogService;

    @RequestMapping("/getAllBlogs")
    public String getAllBlogs(Model model){
        model.addAttribute("blogs",blogService.queryAllBlogs());
        System.out.println("===============");
        return "allBlogs";
    }
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
