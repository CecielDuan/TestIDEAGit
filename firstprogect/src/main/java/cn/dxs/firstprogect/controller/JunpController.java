package cn.dxs.firstprogect.controller;

import cn.dxs.firstprogect.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
/*
用来测试跳转是否顺利；
 */
@Controller
public class JunpController {
    @RequestMapping("/jump")
    public String JumpTest(String user){
        if(user.equals("aaa")) {
            throw new UserNotExistException();
    }
        return "success";
    }
}
