package com.shin.service.Impl;

import com.shin.dao.UserMapper;
import com.shin.pojo.User;
import com.shin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    UserMapper userMapper;

    @Override
    public int checkLogin(User user) {
        return userMapper.queryUser(user);
    }
}
