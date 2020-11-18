package com.shin.service.Impl;

import com.shin.dao.UserMapper;
import com.shin.pojo.User;
import com.shin.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 新增用户
     * @param user 用户
     * @return int
     */
    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }
}
