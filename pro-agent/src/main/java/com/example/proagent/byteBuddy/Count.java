package com.example.proagent.byteBuddy;


import com.example.proagent.byteBuddy.listener.ListListener;
import com.example.proagent.byteBuddy.listener.ObservableList;

public class Count {

    public static ObservableList<String> list = new ObservableList(new ListListener());
}
