package com.example.proagent.byteBuddy;

import cn.hutool.core.util.StrUtil;
import com.example.proagent.byteBuddy.utils.PluginUtil;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.execution.configurations.ParametersList;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.runners.JavaProgramPatcher;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.JavaSdkVersion;
import com.intellij.openapi.projectRoots.Sdk;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author User
 */
public class PerRun extends JavaProgramPatcher {
    @Override
    public void patchJavaParameters(Executor executor, RunProfile configuration, JavaParameters javaParameters) {

        if (!(configuration instanceof RunConfiguration)) {
            return;
        }

        Sdk jdk = javaParameters.getJdk();

        if (Objects.isNull(jdk)) {
            return;
        }

        JavaSdkVersion version = JavaSdk.getInstance().getVersion(jdk);

        if (Objects.isNull(version)) {
            return;
        }

        if (version.compareTo(JavaSdkVersion.JDK_1_8) < 0) {
            return;
        }

        String agentCoreJarPath = PluginUtil.getAgentCoreJarPath();
        if (StrUtil.isBlank(agentCoreJarPath)) {
            return;
        }
        System.out.println("asdasdasd");
        RunConfiguration runConfiguration = (RunConfiguration) configuration;
        String fileName = runConfiguration.getProject().getName() + ".txt";
        SharedInformation.fileName = fileName;
        ParametersList vmParametersList = javaParameters.getVMParametersList();
        vmParametersList.addParametersString("-javaagent:" + agentCoreJarPath+"=" + fileName);
        vmParametersList.addNotEmptyProperty("guide-idea-plugin-probe.projectId", runConfiguration.getProject().getLocationHash());
    }
}
