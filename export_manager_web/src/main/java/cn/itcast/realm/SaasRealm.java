package cn.itcast.realm;

import cn.itcast.domain.system.Module;
import cn.itcast.domain.system.User;
import cn.itcast.service.system.ModuleService;
import cn.itcast.service.system.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Copyright 2022 juzishu.com Inc. All Rights Reserved.
 *
 * @author sunzhen
 * @date 2022/4/18 13:45
 */
public class SaasRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入授权方法AuthorizationInfo+++++++");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Module> moduleList = moduleService.findModuleListByUser(user);
        for (Module module : moduleList) {
            authorizationInfo.addStringPermission(module.getName());
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进入了验证方法++++++");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String email = token.getUsername();
        String password_page = new String(token.getPassword());
        User user = userService.findByEmail(email);
        if (user!=null){
            String password_db = user.getPassword();
            if (password_db.equals(password_page)){
                return new SimpleAuthenticationInfo(user,password_db,getName());
            }
        }
            return null;//用户不存在


    }
}
