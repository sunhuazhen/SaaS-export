package cn.itcast.controller;


import cn.itcast.domain.system.Module;
import cn.itcast.domain.system.User;
import cn.itcast.service.system.ModuleService;
import cn.itcast.service.system.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;

    @RequestMapping("/login")
    public String login(String email, String password) {
        //email和password不能为空
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            request.setAttribute("error", "邮箱或密码不能为空");
            //重定向,浏览器重新发出请求,request和respon丢失
            return "forward:/login.jsp";
        }
        //使用shiro的认证方式:1.创建令牌 2.获取主题 3.开始认证
        password = new Md5Hash(password,email,2).toString();//页面传进来的密码进行加密
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        }catch (AuthenticationException exception){
            request.setAttribute("error","邮箱或者密码错误");
            return "forward:login.jsp";
        }
        User user = (User) subject.getPrincipal();
        session.setAttribute("loginUser",user);
        List<Module> moduleList = moduleService.findModuleListByUser(user);
        session.setAttribute("modules",moduleList);
        return "home/main";


        //根据email查找用户
//        User user = userService.findByEmail(email);
//        password = new Md5Hash(password, email, 2).toString();
//        if (user == null || !user.getPassword().equals(password)) {
//            request.setAttribute("error", "用户名或者密码名错误");
//            return "forward:/login.jsp";  //请求转发
//        } else {
//            session.setAttribute("loginUser", user);
//            //根据登录用户查询模块信息
//            List<Module> moduleList = moduleService.findModuleListByUser(user);
//            session.setAttribute("modules", moduleList);
//            return "home/main";
//        }
    }

    //退出
    @RequestMapping(value = "/logout", name = "用户登出")
    public String logout() {
        SecurityUtils.getSubject().logout();   //登出
        return "forward:login.jsp";
    }

    @RequestMapping("/home")
    public String home() {
        return "home/home";
    }
}
