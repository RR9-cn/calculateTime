package com.example.proagent.byteBuddy;

import com.example.proagent.byteBuddy.action.TimeLogSetting;
import com.example.proagent.byteBuddy.action.TimeWindow;
import com.google.common.collect.Maps;
import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.execution.application.ApplicationConfigurationType;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.wm.ToolWindow;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author CJJ
 * @date 2023/8/23 9:43
 */
public class ToolsConfiguration implements Configurable {
    public static TimeLogSetting timeLogSetting = new TimeLogSetting();

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "EasyDoc";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return timeLogSetting.getJPanel();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        System.out.println("click ======");
    }


}
