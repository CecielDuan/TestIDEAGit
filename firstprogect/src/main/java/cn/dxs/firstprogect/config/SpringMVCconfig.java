package cn.dxs.firstprogect.config;


import cn.dxs.firstprogect.component.LoginHandlerIntercepter;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jProperties;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.net.InetAddress;
import java.util.Locale;
import java.util.Set;


@Configuration
public class SpringMVCconfig implements WebMvcConfigurer {
    //这个类才是对请求进行自定义映射；
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/dxs").setViewName("login");
    }

    //这如果没记错应该是个视图控制器
    @Bean
    public WebMvcConfigurer MvcConfigurer() {
        WebMvcConfigurer MvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
        return MvcConfigurer;
    }

    /*在另一个类中配制了更换语言的方法
     * 所以在这里需要对那个方法再次声明，
     * 以为这里是SpringMVC的一个扩展配制类，
     * 更重要的是我自定义的；
     * */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    //使自己配置的拦截器生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //在“/**”表示任意请求都会被拦截,所以需要排除一些本身不登录就可以访问的请求页面；
        //要排除哪些请求可以参考你自己定义了几个请求，否则默认“/”请求不要拦截；
        //对了
        registry.addInterceptor(new LoginHandlerIntercepter()).addPathPatterns("/**")
                .excludePathPatterns("/jump","/myServlet")
                .excludePathPatterns("/index.html", "/", "/user/login", "/**/*.css", "/**/*.js");
    }

    //配置servlet容器
    @Bean
    public TomcatConnectorCustomizer webserverFactoryCustomizer() {
        return new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
             //   connector.setPort(8082);
            }
        };
    }
}
