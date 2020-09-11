package cn.dxs.firstprogect.component;

import cn.dxs.firstprogect.exception.UserNotExistException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@ControllerAdvice
public class MyexceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e, Model map, HttpServletRequest request){
        //没办法，状态码被封装到了这个接口里面，成了接口的常量；
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE,500);
        map.addAttribute("code","user.notexits");
        map.addAttribute("message",e.getMessage());
        request.setAttribute("ext",map);
        return "forward:/error";
    }
}
