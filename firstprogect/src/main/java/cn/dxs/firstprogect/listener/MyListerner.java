package cn.dxs.firstprogect.listener;

import javax.servlet.ServletContextAttributeEvent;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListerner implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("应用启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("当前项目销毁");
    }
}
