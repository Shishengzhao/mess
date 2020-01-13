package com.ssz.mess.component;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查
 */
@Component
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("登录检查。。。");
        Object user = request.getSession().getAttribute("user");
        if(user == null){
            System.out.println("未登录。。。");
            //未登录
            request.setAttribute("msg","未登录，请先登录！");
            request.getRequestDispatcher("/index.html").forward(request,response);
//            response.sendRedirect("/user");
            return false;
        }else {
            //已登录，放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
