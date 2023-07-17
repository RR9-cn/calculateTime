package com.example.calculateplugin;

import com.example.proagent.byteBuddy.utils.MonitorList;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/7/13 10:59
 */
public class Test {
    static ScheduledExecutorService service = Executors
            .newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        service.scheduleAtFixedRate(new MonitorList(), 10, 5, TimeUnit.MILLISECONDS);
    }
}
