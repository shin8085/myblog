package com.shin.controller;

import com.shin.pojo.Blog;
import com.shin.pojo.User;
import com.shin.service.BlogService;
import com.shin.utils.Result;
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

    /**
     * 新建博客
     * @param blog 博客
     * @return Result
     */
    @RequestMapping("/addBlog")
    public Result addBlog(@RequestBody Blog blog){
        System.out.println(blog);
        int i = blogService.addBlog(blog);
        if(i!=0){
            return Result.success("增加成功");
        }
        return Result.error("增加失败");
    }

    /**
     * 获取数据库中所有博客
     * @return Result
     */
    @RequestMapping("/getAllBlogs")
    public Result getAllBlogs(){
        List<Blog> blogs = blogService.queryAllBlogs();
        return Result.success("true",blogs);
    }

    /**
     * 根据用户名获取当前用户的所有博客
     * @param user 用户
     * @return Result
     */
    @RequestMapping("/getBlogsByUserName")
    public Result getBlogsByUserName(@RequestBody User user){
        List<Blog> blogs = blogService.queryBlogsByUserName(user.getName());
        return Result.success("true",blogs);
    }

    /**
     * 根据博客id获取博客
     * @param id 博客id
     * @return Result
     */
    @RequestMapping("/getBlogById")
    public Result getBlogById(@RequestParam("id") String id){
        Blog blog = blogService.queryBlogById(id);
        return Result.success("true",blog);
    }

    /**
     * 根据博客id删除博客
     * @param id 博客id
     * @return Result
     */
    @RequestMapping("/deleteBlogById")
    public Result deleteBlogById(@RequestParam("id") String id){
        blogService.deleteBlogById(id);
        return Result.success("删除成功！",null);
    }

    /**
     * 更新博客
     * @param blog 博客
     * @return Result
     */
    @RequestMapping("/updateBlog")
    public Result updateBlog(@RequestBody Blog blog){
        blogService.updateBlog(blog);
        return Result.success("更新成功");
    }
}
