package com.example.proagent.byteBuddy.utils;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/**
 * @author CJJ
 * @date 2023/8/27 14:46
 */
public class FilesUtil {

    public static List<String> readFilesLines(String path){
        return FileUtil.readLines(path, StandardCharsets.UTF_8);
    }

    public static String readFiles(String path){
        return FileUtil.readString(path, StandardCharsets.UTF_8);
    }

    public static void writingFile(String content,String path){
        File file = new File(path);
        FileUtil.writeUtf8String(content,file);
    }

    public static void writingAppendFile(String content,String path){
        File file = new File(path);
        FileUtil.appendString(content,file,StandardCharsets.UTF_8);
    }

    public static void creteFile(String path){
        File file = new File(path);
        FileUtil.mkdir(file);
    }
}
