package com.example.proagent.byteBuddy.action;

import com.example.proagent.byteBuddy.SharedInformation;
import com.example.proagent.byteBuddy.listener.FileListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

public class ReadFactory implements ToolWindowFactory {
    public static TimeWindow readUI = new TimeWindow();
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // 获取内容工厂的实例
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        Content content = contentFactory.createContent(readUI.getPanel(), "", false);

        toolWindow.getContentManager().addContent(content);
        System.out.println("start thread");
        try {
            new Thread(new FileListener()).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        readUI.getClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Path path = Path.of(SharedInformation.baseDir + SharedInformation.fileName);
                try {
                    Files.writeString(path,"");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


}
