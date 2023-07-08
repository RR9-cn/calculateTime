package com.example.proagent.byteBuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;


/**
 * @author CJJ
 * @description TODO
 * @date 2023/6/19 14:07
 */
public class JavaAgentByBuddy {

    public static void premain(String agentArgs, Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("com.example"))
                .transform((builder, type, classLoader, module) ->
                        builder
                                .method(ElementMatchers.any())
                                .intercept(Advice.to(MethodCostTime.class))
                )
                .installOn(inst);
    }


    //如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
    public static void premain(String agentArgs) {
    }
}
