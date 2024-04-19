package com.iot.controller;

import com.iot.mapper.Usermap;
import com.iot.pojo.User;
import com.iot.pojo.staticQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@CrossOrigin
public class control {
    @Autowired
    private Usermap map;
    @RequestMapping("/test")
    @ResponseBody//即使配置了json解析器还是要以body返回，要不然json解析后还是去找视图解析器
    public List<User> find(){
        return map.findall();
    }

    @RequestMapping("/ajax")
    @ResponseBody
    public String ajax(){
        int ran=0;
        // 创建Random对象
        Random random = new Random();
        String value;

        ran= random.nextInt(100);
        value=Integer.toString(ran);
        // 准备JSON数据
        String data = value;

        return data;
    }

    @RequestMapping("/json")
    @ResponseBody
    public ResponseEntity<Map<String, String>> json() {
        Map<String, String> map = new HashMap<>();
        int ran=0;
        // 创建Random对象
        Random random = new Random();
        String value;

        ran= random.nextInt(100);
        value=Integer.toString(ran);
        // 准备JSON数据
        String data = value;
        map.put("speed", value);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //从数据队列里去除数据返回给前端：
    @RequestMapping("/get-data")
    @ResponseBody
    public String offerData(){
        return staticQueue.getData();
    }

    //按钮控制采集的行为：
    @GetMapping("/set-stop/{stop}")
    @ResponseBody
    public String stop(@PathVariable String stop){
        if(stop=="1"){
            staticQueue.stop=1;
            return "操作成功！";
        }else if(stop=="0"){
            staticQueue.stop=0;
            return "操作成功！";
        }
        return "操作失败！";
    }
}
