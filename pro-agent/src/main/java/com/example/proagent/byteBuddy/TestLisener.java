package com.example.proagent.byteBuddy;

import com.example.proagent.byteBuddy.listener.ListListener;
import com.example.proagent.byteBuddy.listener.ObservableList;

public class TestLisener {

    public static void main(String[] args) {
        ObservableList<String> observableList = new ObservableList<>(new ListListener());
        observableList.add("asdasd");
    }
}
