package com.shin.dao;

import com.shin.pojo.Blog;

import java.util.List;

public interface BlogMapper {
    int addBlog(Blog blog);
    int deleteBlogById(String id);
    Blog queryBlogById(String id);
    int updateBlog(Blog blog);
    List<Blog> queryBlogsByUserName(String name);
    List<Blog> queryAllBlogs();
}
