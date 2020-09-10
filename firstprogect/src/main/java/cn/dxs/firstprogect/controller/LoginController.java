package cn.dxs.firstprogect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

@Controller
public class LoginController {
    /*规定请求的映射和请求的方式*/
    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    //上面的注解方式是SpringMVC里面的，下面的注解方式是SpringBoot里面的
    @PostMapping("/user/login")//两个注解都是一样的,这个注解底层有上面那个注解；
    public String login(@RequestParam("username")  String username, @RequestParam("password") String password, ModelMap map, HttpServletRequest request){

        //这里没有连接数据库，照着视频来的，需要连接数据库的话可以去dao层；
        if(!StringUtils.isEmpty(username)&&"123456".equals(password)){
            //往session里面放用户名，证明该用户登录过了；
            request.getSession().setAttribute("username",username);
          //这是直接跳转，也就是转发，
            //return "dashboard";
            //使用重定向可以防止重复提交，但是登录就会失去意义，因为有了映射，可以直接通过网址进入；
            return "redirect:/main.html";//定义一个跳转的路径，但是由于是虚拟映射需要到配置文件中进行配置；
        }
        map.addAttribute("msg","密码或账号登录错误");
        return "login";
    }
}
