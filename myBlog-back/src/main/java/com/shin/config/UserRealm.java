package com.shin.config;

import com.shin.pojo.User;
import com.shin.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.Arrays;

public class UserRealm extends AuthorizingRealm {

    @Resource
    LoginService loginService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        User user = loginService.queryUserByName(token.getUsername());
        if(user==null){
            return null; //UnknownAccountException
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
