package com.tison.basic.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tison
 * @date 2020/03/25
 * @description OOM触发类
 */
@RestController
@RequestMapping("/oom")
public class OOMTestController {

    /**
     * 创建线程池，通过线程池，保证创建的线程存活
     */
    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            100, 100, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());

    /**
     * 声明本地变量,循环请求引发OOM
     */
    final static ThreadLocal<Byte[]> localVariable = new ThreadLocal<Byte[]>();

    @GetMapping(value = "/test0")
    public String test0() {
        poolExecutor.execute(()->{
            Byte[] c = new Byte[4096*1024];
            localVariable.set(c);// 为线程添加变量
        });
        return "success";
    }

    /**
     * 循环请求引发FullGC
     * @param request
     * @return
     */
    @GetMapping(value = "/test1")
    public String test1(HttpServletRequest request) {
        List<Byte[]> temp1 = new ArrayList<Byte[]>();
        Byte[] b = new Byte[1024*20];
        //添加局部变量
        temp1.add(b);
        return "success";
    }
}
