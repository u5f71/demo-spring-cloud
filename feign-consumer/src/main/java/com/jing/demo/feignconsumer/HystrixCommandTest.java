package com.jing.demo.feignconsumer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.Future;

/**
 * @description: hystrix 脱离 spring cloud 的单独使用
 * @author: jcwang
 * @create: 2021-03-01 16:53
 **/
public class HystrixCommandTest extends HystrixCommand {

    protected HystrixCommandTest(HystrixCommandGroupKey group) {
        super(group);
    }

    /**
     * 异步非阻塞方式执行
     * <p>
     * queue()：以异步非阻塞方式执行run()。以demo为例，
     * 一调用queue()就直接返回一个Future对象，
     * 同时hystrix创建一个新线程运行run()，
     * 调用程序通过Future.get()拿到run()的返回结果，
     * 而Future.get()是阻塞执行的
     */
    public static void queueTest() {

        Future<String> futureResult = new HystrixCommandTest(HystrixCommandGroupKey.Factory.asKey("ext")).queue();
        String result = "";
        try {
            result = futureResult.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("程序结果：" + result);
    }

    /**
     * 同步阻塞方式执行
     * <p>
     * execute()：以同步阻塞方式执行run()。以demo为例，调用execute()后，
     * hystrix先创建一个新线程运行run()，
     * 接着调用程序要在execute()调用处一直阻塞着，直到run()运行完成
     */
    public static void executeTest() {
        HystrixCommandTest hystrixTest = new HystrixCommandTest(HystrixCommandGroupKey.Factory.asKey("ext"));
        String result = "";
        System.out.println("result:" + hystrixTest.execute());
    }

    public static void main(String[] args) {
        executeTest();
    }

    @Override
    protected Object run() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("执行逻辑");
        int i = 1 / 0;
        return "ok";
    }

    @Override
    protected Object getFallback() {
        return "getFallback";
    }

}