package com.example.proagent.byteBuddy;

import net.bytebuddy.asm.Advice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

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
    public static void exit(@Advice.Enter long start, @Advice.Origin String method) {
        long end = System.nanoTime();
        try {
            Path path = Path.of(SharedInformation.baseDir + SharedInformation.fileName);
            System.out.println("开始写入文件");
            Files.writeString(path,method + ":" + end + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
