package com.shin.service.Impl;

import com.shin.dao.BlogMapper;
import com.shin.pojo.Blog;
import com.shin.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Override
    public int addBlog(Blog blog) {
        int result=0;
        try {
            result=blogMapper.addBlog(blog);
        }catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public int deleteBlogById(String id) {
        int result=0;
        try {
            result=blogMapper.deleteBlogById(id);
        }catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public Blog queryBlogById(String id) {
        Blog blog=null;
        try {
            blog=blogMapper.queryBlogById(id);
        }catch (Exception e){
            System.out.println(e);
        }
        return blog;
    }

    @Override
    public int updateBlog(Blog blog) {
        int result=0;
        try {
            result=blogMapper.updateBlog(blog);
        }catch (Exception e){
            System.out.println(e);
        }
        return result;
    }
}
