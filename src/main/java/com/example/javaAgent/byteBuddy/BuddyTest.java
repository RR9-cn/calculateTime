package com.example.javaAgent.byteBuddy;

import com.example.javaAgent.ApiTest;
import com.example.javaAgent.ApiTest2;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/6/19 14:11
 */
public class BuddyTest {

    public static void main(String[] args) throws InterruptedException {
        BuddyTest apiTest = new BuddyTest();
        apiTest.echoHi();
        apiTest.echoHi1();
        ApiTest2 apiTest2 = new ApiTest2();
        apiTest2.test();
        ApiTest.sayHello();
    }

    private void echoHi() throws InterruptedException {
        System.out.println("hi agent");
        Thread.sleep((long) (Math.random() * 500));
    }

    private void echoHi1() throws InterruptedException {
        System.out.println("hi agent123");
        Thread.sleep((long) (Math.random() * 500));
    }
}
