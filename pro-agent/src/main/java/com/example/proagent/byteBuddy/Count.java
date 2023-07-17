package com.example.proagent.byteBuddy;


import com.example.proagent.byteBuddy.action.TimeWindow;
import com.example.proagent.byteBuddy.listener.ListListener;
import com.example.proagent.byteBuddy.listener.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Count {

    public static ObservableList<String> list = new ObservableList(new ListListener());

    public static TimeWindow  timeWindow;
}
