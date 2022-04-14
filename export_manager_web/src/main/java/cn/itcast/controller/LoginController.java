package cn.itcast.controller;


import cn.itcast.domain.system.User;
import cn.itcast.service.system.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController extends BaseController{
@Autowired
private UserService userService;
	@RequestMapping("/login")
	public String login(String email,String password) {
	    //email和password不能为空
        if (StringUtils.isEmpty(email)||StringUtils.isEmpty(password)){
            request.setAttribute("error","邮箱或密码不能为空");
            //重定向,浏览器重新发出请求,request和respon丢失
            return "forward:/login.jsp";
        }
        //根据email查找用户
        User user = userService.findByEmail(email);
        if (user==null){
            request.setAttribute("error","邮箱错误");
            return "forward:/login.jsp";  //请求转发
        }else {
            String passwordMD = user.getPassword();
            String passwordPage = new Md5Hash(password,email,2).toString();
            if (!passwordMD.equals(passwordPage)){
                request.setAttribute("error","密码错误");
                return "forward:/login.jsp";  //请求转发
            }
        }
        session.setAttribute("loginUser",user);
		return "home/main";
	}

    //退出
    @RequestMapping(value = "/logout",name="用户登出")
    public String logout(){
        //SecurityUtils.getSubject().logout();   //登出
        return "forward:login.jsp";
    }

    @RequestMapping("/home")
    public String home(){
	    return "home/home";
    }
}
