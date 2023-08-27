package com.example.proagent.byteBuddy;

import com.example.proagent.byteBuddy.listener.ActionListener;
import com.example.proagent.byteBuddy.utils.FilesUtil;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.util.Objects;


/**
 * @author CJJ
 * @date 2023/6/19 14:07
 */
public class JavaAgentByBuddy {

    public static void premain(String agentArgs, Instrumentation inst) {
        SharedInformation.fileName = agentArgs;
        String packagePath = "";
        FilesUtil.writingFile("",SharedInformation.baseDir + SharedInformation.fileName);
        packagePath = FilesUtil.readFiles(SharedInformation.basePackageDir + SharedInformation.fileName);

        if (Objects.equals(packagePath, "")) {
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
