package cn.dxs.firstprogect.component;


import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class MyerrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(webRequest,options);
        map.put("name","dxs");
        //异常处理器的携带数据；
        Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        return map;
    }
}
