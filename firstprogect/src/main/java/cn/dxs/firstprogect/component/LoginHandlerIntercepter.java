package cn.dxs.firstprogect.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//检查是否登录
public class LoginHandlerIntercepter implements HandlerInterceptor {
    //在目标方法执行前执行的操作
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从session里面获取用户的登录值，得到了，证明登录了，否则就是没有登录；
        Object user = request.getSession().getAttribute("username");
        //如果username是null
        if(!(user==null)) {
            return true;
        }
        //在登录界面显示消息
        request.setAttribute("msg","没有权限，请先登录");
        //跳转回登录界面
        request.getRequestDispatcher("/index.html").forward(request,response);
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
