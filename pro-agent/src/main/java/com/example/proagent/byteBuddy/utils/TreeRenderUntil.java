package com.example.proagent.byteBuddy.utils;

import de.sciss.treetable.j.DefaultTreeColumnModel;
import de.sciss.treetable.j.DefaultTreeTableNode;
import de.sciss.treetable.j.TreeTable;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author CJJ
 * @date 2023/8/15 14:05
 */
public class TreeRenderUntil {

    private static Map<String,DefaultTreeTableNode> map = new HashMap<>();

    public static void main(String[] args) {
        String path = "com.costumor.test.morcoservice.cache.config.redis.RedisConfig.redisTemplate:80ms";
        String path1 = "com.costumor.test.demo.redis.redisTemplate:80ms";
        DefaultTreeTableNode root = new DefaultTreeTableNode("TimeTree","","");
        render(root,path);
        render(root,path1);
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Frame");
            List<String> strings = Arrays.asList("1", "2", "3");
            DefaultTreeColumnModel defaultTreeColumnModel = new DefaultTreeColumnModel(root,strings);
            TreeTable table = new TreeTable(new DefaultTreeModel(root),defaultTreeColumnModel);
            table.setAutoCreateRowSorter(true); // bug appears when the row sorter is activated
            table.setShowsRootHandles(true);
            table.setRootVisible(false);
            frame.getContentPane().add(new JScrollPane(table));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(1000,300));
            frame.pack();

            frame.setVisible(true);
        });

    }

    public static void render(DefaultTreeTableNode root,String packagePath){
        String timeStr = packagePath.split(":")[1];
        generateTree(root,packagePath.split("\\."),timeStr);
    }

    private static void generateTree(DefaultTreeTableNode root, String[] packageList,String timeStr){
        Queue<DefaultTreeTableNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 0;
        while (!queue.isEmpty() && i < packageList.length - 1) {
            //取出队列
            DefaultTreeTableNode poll = queue.poll();
            DefaultTreeTableNode node = map.getOrDefault(poll.getValueAt(0) + "."
                    +packageList[i],new DefaultTreeTableNode(String.valueOf(packageList[i]),timeStr,""));
            poll.add(node);
            queue.add(node);
            map.put(poll.getValueAt(0) + "."
                    +packageList[i],node);
            i++;
        }
    }
}
