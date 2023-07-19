package com.example.proagent.byteBuddy;

import com.example.proagent.byteBuddy.listener.ActionListener;
import com.example.proagent.byteBuddy.utils.PluginUtil;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.Scanner;


/**
 * @author CJJ
 * @description TODO
 * @date 2023/6/19 14:07
 */
public class JavaAgentByBuddy {

    public static void premain(String agentArgs, Instrumentation inst) {
        try {
            File newFile = new File("D://fileName.txt");
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        new AgentBuilder.Default()
                .ignore(ElementMatchers.nameStartsWith("com.example.proagent.byteBuddy.listener"))
                .type(ElementMatchers.nameStartsWith("com"))
                .transform((builder, type, classLoader, module) ->
                        builder
                                .method(ElementMatchers.any())
                                .intercept(Advice.to(MethodCostTime.class))
                )
                .with(new ActionListener())
                .installOn(inst);
        System.out.println("start");
    }


    //如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
    public static void premain(String agentArgs) {
    }

    /**
     * 加载Agent
     *
     * @param arg  命令参数
     * @param inst Instrumentation
     */
    private static void loadAgent(String arg, final Instrumentation inst) {

        Class[] loadedClass = inst.getAllLoadedClasses();
        System.out.println(loadedClass);
        for (Class clazz : loadedClass) {
            String className = clazz.getName();
            System.out.println(className);
        }
    }
}
