package com.example.proagent.byteBuddy.action;

import com.example.proagent.byteBuddy.SharedInformation;

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
        Path path = Path.of(SharedInformation.basePackageDir + SharedInformation.fileName);
        try {
            textField.setText(Files.readString(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Button.addActionListener((e) -> {
            String text = textField.getText();
            try {
                Files.writeString(path,"");
                Files.writeString(path,text);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
