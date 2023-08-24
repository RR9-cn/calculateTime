package com.example.proagent.byteBuddy;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author CJJ
 * @date 2023/8/23 22:15
 */
public class MyStartupActivity implements StartupActivity {
    @Override
    public void runActivity(@NotNull Project project) {
        String fileName = project.getName() + ".txt";
        SharedInformation.fileName = fileName;
        Path path = Path.of(SharedInformation.basePackageDir + SharedInformation.fileName);
        try {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
