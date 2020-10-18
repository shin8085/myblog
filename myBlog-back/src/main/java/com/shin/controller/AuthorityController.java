package com.shin.controller;

import com.shin.pojo.User;
import com.shin.service.LoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shin.utils.Result;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class AuthorityController {

    @Resource
    LoginService loginService;

    @RequestMapping("/login")
    public Result Login(@RequestBody User user){
        int n=loginService.checkLogin(user);
        if(n!=0){
            return Result.success("登入成功",null);
        }
        return Result.error("密码错误或用户不存在",null);
    }
}
