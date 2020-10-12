package com.shin.service;

import com.shin.pojo.Blog;

public interface BlogService {
    int addBlog(Blog blog);
    int deleteBlogById(String id);
    Blog queryBlogById(String id);
    int updateBlog(Blog blog);
}
