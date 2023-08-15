package com.example.proagent.byteBuddy;

import net.bytebuddy.asm.Advice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/6/19 14:05
 */
public class MethodCostTime {

    @Advice.OnMethodEnter
    public static long enter() {
        return System.nanoTime();
    }

    @Advice.OnMethodExit
    public static void exit(@Advice.Enter long start, @Advice.Origin Method method) {
        long end = System.nanoTime();
        String className = method.getDeclaringClass().getName();
        String packageName = method.getDeclaringClass().getPackage().getName();
        try {
            long runTime = TimeUnit.MILLISECONDS.convert((end - start), TimeUnit.NANOSECONDS);
            if(packageName.contains("com.costumor.test.morcoservice") && !method.getName().contains("CGLIB")
            && !className.contains("CGLIB") && runTime > 0){
                Path path = Path.of(SharedInformation.baseDir + SharedInformation.fileName);
                Files.writeString(path,className + "."  + method.getName() + ":" + runTime + "ms" + "\n", StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
