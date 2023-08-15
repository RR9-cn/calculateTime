package com.example.proagent.byteBuddy.action;

import de.sciss.treetable.j.DefaultTreeColumnModel;
import de.sciss.treetable.j.DefaultTreeTableNode;
import de.sciss.treetable.j.TreeTable;
import de.sciss.treetable.j.TreeTableNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Arrays;
import java.util.List;

public class TimeWindow {
    private JPanel panel;
    private JScrollPane JScrollPane;
    private TreeTable table;
    private DefaultTreeTableNode root = new DefaultTreeTableNode("timeTree", "","");

    public JPanel getPanel() {
        return panel;
    }

    public JScrollPane getJScrollPane() {
        return JScrollPane;
    }

    public TreeTable getTable() {
        List<String> strings = Arrays.asList("packageName", "time", "3");
        DefaultTreeColumnModel defaultTreeColumnModel = new DefaultTreeColumnModel(root,strings);
        return new TreeTable(new DefaultTreeModel(root),defaultTreeColumnModel);
    }

    public DefaultTreeTableNode getRoot() {
        return root;
    }
}
