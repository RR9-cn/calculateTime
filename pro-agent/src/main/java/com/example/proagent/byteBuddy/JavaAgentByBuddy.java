package com.example.proagent.byteBuddy;

import com.example.proagent.byteBuddy.listener.ActionListener;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * @author CJJ
 * @date 2023/6/19 14:07
 */
public class JavaAgentByBuddy {

    public static void premain(String agentArgs, Instrumentation inst) {
        SharedInformation.fileName = agentArgs;
        String packagePath = "";
        try {
            Files.writeString(Path.of(SharedInformation.baseDir + SharedInformation.fileName)
                    , "");
            packagePath = Files.readString(Path.of(SharedInformation.basePackageDir + SharedInformation.fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (packagePath.isBlank()) {
            return;
        }
        new AgentBuilder.Default()
                .ignore(ElementMatchers.nameStartsWith("com.example.proagent.byteBuddy.listener"))
                .type(ElementMatchers.nameStartsWith(packagePath))
                .transform((builder, type, classLoader, module) ->
                        builder
                                .method(ElementMatchers.any())
                                .intercept(Advice.to(MethodCostTime.class))
                )
                .with(new ActionListener())
                .installOn(inst);
    }


    /**
     * 如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
     * @param agentArgs 命令行参数
     */
    public static void premain(String agentArgs) {
    }
}
