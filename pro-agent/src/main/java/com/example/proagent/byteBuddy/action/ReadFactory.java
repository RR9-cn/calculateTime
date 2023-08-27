package com.example.proagent.byteBuddy.action;

import com.example.proagent.byteBuddy.SharedInformation;
import com.example.proagent.byteBuddy.listener.FileListener;
import com.example.proagent.byteBuddy.utils.FilesUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import de.sciss.treetable.j.DefaultTreeColumnModel;
import de.sciss.treetable.j.DefaultTreeTableNode;
import de.sciss.treetable.j.TreeTable;
import de.sciss.treetable.j.TreeTableNode;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReadFactory implements ToolWindowFactory {
    public static TimeWindow readUI = new TimeWindow();
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // 获取内容工厂的实例
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        JButton jButton = new JButton("清空");
        jPanel.add(new JScrollPane(ReadFactory.readUI.getTable()),BorderLayout.CENTER);
        jPanel.add(jButton,BorderLayout.NORTH);
        Content content = contentFactory.createContent(jPanel, "", false);
        toolWindow.getContentManager().addContent(content);
        try {
            new Thread(new FileListener()).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        jButton.addActionListener(e -> {
            FilesUtil.writingFile("",SharedInformation.baseDir + SharedInformation.fileName);
        });
    }


}
