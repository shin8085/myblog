package com.shin.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.shin.pojo.Blog;
import com.shin.pojo.User;
import com.shin.service.BlogService;
import com.shin.utils.Result;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Resource
    BlogService blogService;

    @RequestMapping("/addBlog")
    public Result addBlog(@RequestBody Blog blog){
        System.out.println(blog);
        int i = blogService.addBlog(blog);
        if(i!=0){
            return Result.success("增加成功",null);
        }
        return Result.error("增加失败",null);
    }

    @RequestMapping("/getAllBlogs")
    public Result getAllBlogs(){
        List<Blog> blogs = blogService.queryAllBlogs();
        return Result.success("true",blogs);
    }
    @RequestMapping("/getBlogsByUserName")
    public Result getBlogsByUserName(@RequestBody User user){
        List<Blog> blogs = blogService.queryBlogsByUserName(user.getName());
        return Result.success("true",blogs);
    }
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
