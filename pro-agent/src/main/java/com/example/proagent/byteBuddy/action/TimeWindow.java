package com.example.proagent.byteBuddy.action;

import de.sciss.treetable.j.DefaultTreeColumnModel;
import de.sciss.treetable.j.DefaultTreeTableNode;
import de.sciss.treetable.j.TreeTable;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Arrays;
import java.util.List;

public class TimeWindow {
    private JPanel panel;
    private JScrollPane JScrollPane;
    private static DefaultTreeTableNode root = new DefaultTreeTableNode("TimeTree", "","");
    private static List<String> colName = Arrays.asList("Elemement", "RunTime,ms", "TimeRatio,%");
    static DefaultTreeColumnModel defaultTreeColumnModel = new DefaultTreeColumnModel(root, colName);
    private static TreeTable table = new TreeTable(new DefaultTreeModel(root),defaultTreeColumnModel);
    public JPanel getPanel() {
        return panel;
    }

    public JScrollPane getJScrollPane() {
        return JScrollPane;
    }

    public TreeTable getTable() {

        defaultTreeColumnModel.setAllColumnsEditable(false);
        return table;
    }

    public DefaultTreeTableNode getRoot() {
        return root;
    }
}
