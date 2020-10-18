package com.shin.dao;

import com.shin.pojo.User;

public interface UserMapper {
    int addUser(User user);
    int queryUser(User user);
}
