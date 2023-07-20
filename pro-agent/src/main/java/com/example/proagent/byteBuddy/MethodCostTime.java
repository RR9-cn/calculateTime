package com.example.proagent.byteBuddy;

import net.bytebuddy.asm.Advice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
            FileWriter fileWriter = new FileWriter(SharedInformation.baseDir + SharedInformation.fileName,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            System.out.println("开始写入文件");
            bufferedWriter.write(method + ":" + end);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
