package cn.dxs.firstprogect.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("应该执行了吧");
    }
    @Override
    public void destroy() {
    }
}
