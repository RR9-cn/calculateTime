package com.example.proagent.byteBuddy.listener;

import java.util.ArrayList;
import java.util.List;

public class ListListener implements ObservableList.ListChangeListener<String> {
    private static List<String> list = new ArrayList<>();
    @Override
    public void onAdd(String element) {
        list.add(element);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
