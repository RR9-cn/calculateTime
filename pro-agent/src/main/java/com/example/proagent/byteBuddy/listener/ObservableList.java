package com.example.proagent.byteBuddy.listener;

import java.util.ArrayList;
import java.util.List;

public class ObservableList<E> extends ArrayList<E> {

    private List<ListChangeListener<E>> listeners = new ArrayList<>();

    public ObservableList(ListChangeListener<E> listener) {
        listeners.add(listener);
    }

    public interface ListChangeListener<E> {
        void onAdd(E element);
        // Add more methods if you need to listen other changes
    }

    public void addChangeListener(ListChangeListener<E> listener) {
        listeners.add(listener);
    }

    @Override
    public boolean add(E e) {
        boolean added = super.add(e);
        if (added) {
            for (ListChangeListener<E> listener : listeners) {
                listener.onAdd(e);
            }
        }
        return added;
    }

}
