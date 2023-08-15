package com.example.proagent.byteBuddy.action;

import com.example.proagent.byteBuddy.SharedInformation;
import com.example.proagent.byteBuddy.listener.FileListener;
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
        Content content = contentFactory.createContent(new JScrollPane(ReadFactory.readUI.getTable()), "", false);
        toolWindow.getContentManager().addContent(content);
        System.out.println("start thread");
        try {
            new Thread(new FileListener()).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
