package com.example.javaAgent;

import java.lang.instrument.Instrumentation;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/6/19 9:31
 */
public class MyAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("嗨！JavaAgent " + agentArgs);
    }

    //如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
    public static void premain(String agentArgs) {
    }
}
