package com.example.byteBuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/6/19 10:18
 */
public class TestHelloWorld {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        String helloWorld = new ByteBuddy()
                .subclass(Object.class)
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(TestHelloWorld.class.getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();

        System.out.println(helloWorld);  // Hello World!
    }

}
