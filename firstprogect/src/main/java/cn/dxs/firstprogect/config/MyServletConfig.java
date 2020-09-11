package cn.dxs.firstprogect.config;

import cn.dxs.firstprogect.filter.MyFilter;
import cn.dxs.firstprogect.listener.MyListerner;
import cn.dxs.firstprogect.servlet.MyServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class MyServletConfig {

    ServletRegistrationBean registrationBean;
    //注册三大组件
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        //将自己写的Serlet放进来，到容器里；
        //registrationBean = new ServletRegistrationBean(new MyServlet(), "/myServlet");
        //下面的代码和上面的效果一样
        registrationBean = new ServletRegistrationBean(new MyServlet());
        registrationBean.setUrlMappings(Arrays.asList("/myServlet"));
        return registrationBean;
    }
    //放置监听器
    @Bean
    public ServletListenerRegistrationBean slistener(){
        ServletListenerRegistrationBean listerner = new ServletListenerRegistrationBean(new MyListerner());
        return listerner;
    }

    //注册filter
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        //将自己的Filter放进来,记住默认情况下，它过滤的是所有请求
      //  FilterRegistrationBean filter = new FilterRegistrationBean(new MyFilter(),registrationBean);
        FilterRegistrationBean filter = new FilterRegistrationBean(new MyFilter());
        filter.setUrlPatterns(Arrays.asList("/myServlet"));
        return  filter;
    }
}
