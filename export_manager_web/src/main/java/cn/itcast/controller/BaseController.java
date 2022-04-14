package cn.itcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpSession session;

    /**
     * 获取当前登录人的企业id TODO 以后完成登录代码后会修改此方法
     * @return
     */
    protected String getCompanyId(){
        return "1";
    }
    /**
     * 获取当前登录人的企业名称 TODO 以后完成登录代码后会修改此方法
     * @return
     */
    protected String getCompanyName(){
        return "大脸猫皮具外贸有限公司";
    }

}
