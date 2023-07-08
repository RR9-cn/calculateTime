package com.example.proagent.byteBuddy;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/6/19 14:05
 */
public class MethodCostTime {

    @Advice.OnMethodEnter
    static long enter() {
        return System.nanoTime();
    }

    @Advice.OnMethodExit
    static void exit(@Advice.Enter long start, @Advice.Origin String method) {
        long end = System.nanoTime();
        System.out.println(method + " took " + (end - start) + " nanoseconds");
    }
}
