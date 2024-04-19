package com.iot.pojo;

import java.util.LinkedList;
import java.util.Queue;

public class staticQueue {
    public static int stop;
    public static Queue<String> dataQueue=new LinkedList<>();
    //使用对象锁来实现互斥访问类的静态成员！
    public static synchronized void addData(String data) {
       dataQueue.add(data);
    }

    public static synchronized String getData() {
        if(!dataQueue.isEmpty())
            return dataQueue.poll();
        else
            return "null";
    }
}
