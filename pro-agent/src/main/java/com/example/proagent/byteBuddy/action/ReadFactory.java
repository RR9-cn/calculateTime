package com.example.proagent.byteBuddy.action;

import com.example.proagent.byteBuddy.Count;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ReadFactory implements ToolWindowFactory {
    public static TimeWindow readUI = new TimeWindow();
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // 获取内容工厂的实例
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        Content content = contentFactory.createContent(readUI.getPanel(), "", false);

        toolWindow.getContentManager().addContent(content);

        Count.timeWindow = readUI;

        System.out.println(Count.list.size());

    }


}
