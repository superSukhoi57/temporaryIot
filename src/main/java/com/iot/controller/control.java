package com.iot.controller;

import com.iot.mapper.Usermap;
import com.iot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class control {
    @Autowired
    private Usermap map;
    @RequestMapping("/test")
    @ResponseBody//即使配置了json解析器还是要以body返回，要不然json解析后还是去找视图解析器
    public List<User> find(){
        return map.findall();
    }

}
