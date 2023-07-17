package com.example.proagent.byteBuddy.listener;


import com.example.proagent.byteBuddy.Count;
import com.example.proagent.byteBuddy.action.ReadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListListener implements ObservableList.ListChangeListener<String> {
    private static List<String> list = new ArrayList<>();
    @Override
    public void onAdd(String element) {
        list.add(element);
    }
}
