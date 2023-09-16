package com.example.calculateplugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/7/13 10:59
 */
public class Test {


    public static void main(String[] args) {
    }

    private void load(String dir,String cname) {
        String jarName = "file:" + dir;
        System.out.println(jarName);
        try {
            File file = new File(jarName);
            URL url = file.toURL();
            URLClassLoader loader = new URLClassLoader(new URL[]{url});
            Class aClass = loader.loadClass(cname);
            //利用Java反射机制创建实例测试方法

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
