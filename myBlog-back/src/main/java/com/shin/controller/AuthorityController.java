package com.shin.controller;

import com.shin.pojo.User;
import com.shin.service.LoginService;
import com.shin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.UnknownUserException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shin.utils.Result;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class AuthorityController {

    @Resource
    LoginService loginService;

    @Resource
    UserService userService;

    /**
     * 登录
     * @param user 用户信息
     * @return Result
     */
    @RequestMapping("/login")
    public Result Login(@RequestBody User user){

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),user.getPassword());
        try {
            subject.login(token); //登录
            Session session= subject.getSession();
            return Result.success("登录成功",session);
        }catch (UnknownAccountException e){
            //用户名不存在
            return Result.error("用户不存在");
        }catch (IncorrectCredentialsException e){
            //密码错误
            return Result.error("密码错误");
        }
    }

    /**
     * 注册
     * @param user 用户信息
     * @return Result
     */
    @RequestMapping("/register")
    public Result register(@RequestBody User user){
        User r_user = loginService.queryUserByName(user.getName());
        if(r_user==null){
            //用户名不存在，可以注册
            int i = userService.addUser(user);
            return  Result.success("注册成功");
        }
        return Result.error("用户名已存在");
    }

    /**
     * 检查session是否正确
     * @param session 会话
     * @return Result
     */
    @RequestMapping("/checkLoginSession")
    public Result checkLoginSession(@RequestParam("loginSession") String session){
        Subject subject = SecurityUtils.getSubject();
        if(session.equals(subject.getSession().getId())){
            return Result.success();
        }
        return Result.error();
    }

    /**
     * 退出登录
     * 检查session是否正确
     * @return Result
     */
    @RequestMapping("/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success();
    }
}
