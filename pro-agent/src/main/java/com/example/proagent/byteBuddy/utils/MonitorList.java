package com.example.proagent.byteBuddy.utils;

import com.example.proagent.byteBuddy.Count;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/7/13 10:47
 */
public class MonitorList implements Runnable {
    @Override
    public void run() {
        System.out.println(Count.list.size());
    }
}
