package cn.dxs.firstprogect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获得请求参数中键l的值(由于在a标签中设置的是l='en')；
        String l = request.getParameter("l");
        /*由于方法一定
        要返回一个locale的值，
        所以先创建一个loacle对象,
        这里也需要给他一个默认的，
        否则说不定会导致出现空指针；
        而且它本身就有一个静态的方法；*/
        Locale locale = Locale.getDefault();
       //表示获得的l值并不是空，这时就需要判断值到底是多少；
        if(!StringUtils.isEmpty(l)){
            //在传送过来的值，是这种类型zh_CN，前面是语言，后面是国家；所以需要将它分开；
            String[] values = l.split("_");
            //恰巧locale有一个构造器含有这两个参数，传进去就可以了；
           locale = new Locale(values[0],values[1]);
        }
        return locale;
    }
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
    }
}
