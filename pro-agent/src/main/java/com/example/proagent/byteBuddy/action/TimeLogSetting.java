package com.example.proagent.byteBuddy.action;

import com.example.proagent.byteBuddy.SharedInformation;
import com.example.proagent.byteBuddy.utils.FilesUtil;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author CJJ
 * @date 2023/8/23 19:42
 */
public class TimeLogSetting {
    private JPanel JPanel;
    private JLabel JLable;
    private JTextField textField;
    private JButton Button;

    public JPanel getJPanel() {
        return JPanel;
    }

    public JLabel getJLable() {
        return JLable;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JButton getButton() {
        return Button;
    }

    public TimeLogSetting() {
        if (SharedInformation.fileName != null) {
            textField.setText(FilesUtil.readFiles(SharedInformation.basePackageDir + SharedInformation.fileName));
            Button.addActionListener((e) -> {
                String text = textField.getText();
                FilesUtil.writingFile("",SharedInformation.basePackageDir + SharedInformation.fileName);
                FilesUtil.writingFile(text,SharedInformation.basePackageDir + SharedInformation.fileName);
            });
        }

    }
}
