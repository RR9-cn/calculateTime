package com.example.proAgent.byteBuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/6/19 14:07
 */
public class JavaAgentByBuddy {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("this is my agent：" + agentArgs);

        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule, protectionDomain) -> {
            return builder
                    .method(ElementMatchers.any()) // 拦截任意方法
                    .intercept(MethodDelegation.to(MethodCostTime.class)); // 委托
        };
        new AgentBuilder
                .Default()
                 // 指定需要拦截的类
                .type(ElementMatchers.nameStartsWith("com.example.proAgent.byteBuddy.javaAgent.ApiTest"))
                .transform(transformer)
                .with(new ActionListener())
                .installOn(inst);
    }

    //如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
    public static void premain(String agentArgs) {
    }
}
