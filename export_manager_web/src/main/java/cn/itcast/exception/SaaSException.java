package cn.itcast.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理器  捕获Controller层抛出的异常，跳转到一个公共的页面error.jsp
 */
public class SaaSException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //如果捕获的是UnauthorizedException.跳转到unauthorized.jsp页面
        ModelAndView mv = new ModelAndView();
        if (ex instanceof UnauthorizedException){
            mv.setViewName("forward:/unauthorized.jsp");
        }else {
            mv.setViewName("error");
        }
        mv.addObject("errorMsg",ex.getMessage());
        return mv;
    }
}
