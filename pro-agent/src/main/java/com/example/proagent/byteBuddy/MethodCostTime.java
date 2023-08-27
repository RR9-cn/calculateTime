package com.example.proagent.byteBuddy;

import com.example.proagent.byteBuddy.utils.FilesUtil;
import net.bytebuddy.asm.Advice;

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
        String packagePath = FilesUtil.readFiles(SharedInformation.basePackageDir + SharedInformation.fileName);
        long runTime = TimeUnit.MILLISECONDS.convert((end - start), TimeUnit.NANOSECONDS);
        if(packageName.contains(packagePath) && !method.getName().contains("CGLIB")
            && !className.contains("CGLIB") && runTime > 0){
                FilesUtil.writingAppendFile(className + "."  + method.getName() + ":" + runTime + "ms" + "\n",
                        SharedInformation.baseDir + SharedInformation.fileName);
        }
    }
}
