package com.shin.service;

import com.shin.pojo.Blog;

import java.util.List;

public interface BlogService {
    int addBlog(Blog blog);
    int deleteBlogById(String id);
    Blog queryBlogById(String id);
    int updateBlog(Blog blog);
    List<Blog> queryBlogsByUserName(String name);
    List<Blog> queryAllBlogs();
}
