package com.example.javaAgent;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/6/19 9:32
 */
public class ApiTest {

    public static void main(String[] args) {
        System.out.println("hi itstack-demo-agent-01");
        ApiTest2 apiTest2 = new ApiTest2();
        apiTest2.test();
    }

    public static void sayHello(){
        System.out.println("apiTest HELLO");
    }

}
