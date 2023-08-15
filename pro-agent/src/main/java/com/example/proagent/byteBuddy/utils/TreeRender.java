package com.example.proagent.byteBuddy.utils;

import com.example.proagent.byteBuddy.SharedInformation;
import com.example.proagent.byteBuddy.action.ReadFactory;
import de.sciss.treetable.j.DefaultTreeColumnModel;
import de.sciss.treetable.j.DefaultTreeTableNode;
import de.sciss.treetable.j.TreeTable;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

/**
 * @author CJJ
 * @date 2023/8/15 14:05
 */
public class TreeRender {

    private static List<String> colName = Arrays.asList("Elemement", "RunTime,ms", "TimeRatio,%");

    public static void render(DefaultTreeTableNode root,String packagePath){
        generateTree(root,packagePath.split("\\."));
    }

    private static void generateTree(DefaultTreeTableNode root,String[] packageList){
        boolean isExist = false;
        Queue<DefaultTreeTableNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 0;
        while (!queue.isEmpty() && i < packageList.length) {
            //取出队列
            DefaultTreeTableNode poll = queue.poll();
            String pName = packageList[i];
            DefaultTreeTableNode node;
            if(pName.contains(":")){
                node = new DefaultTreeTableNode(pName.split(":")[0],pName.split(":")[1], "");
                pName = pName.split(":")[0];
            }else {
                node = new DefaultTreeTableNode(pName, "", "");
            }
            for (int j = 0; j < poll.getChildCount(); j++) {
                DefaultTreeTableNode childAt = (DefaultTreeTableNode) poll.getChildAt(j);
                if (childAt.getValueAt(0).toString().equals(pName)) {
                    isExist = true;
                    node = childAt;
                    break;
                }
            }
            if (!isExist) {
                poll.add(node);
            }
            isExist = false;
            queue.offer(node);
            i++;
        }
    }
}
