package cn.itcast.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Copyright 2022 juzishu.com Inc. All Rights Reserved.
 *
 * @author sunzhen
 * @date 2022/4/18 15:48
 */
public class MyPermsFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = this.getSubject(request, response);
        String[] perms = (String[])((String[])mappedValue);
        //    perms = ["部门管理","删除部门"]
        if (perms!=null&&perms.length > 0){
            for (String perm : perms) {
                if (subject.isPermitted(perm)){
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
