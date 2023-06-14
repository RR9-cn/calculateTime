package com.example.calculatetime;

import javax.swing.*;
import java.io.File;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/6/14 15:46
 */
public class ShowUi {

    private JPanel mainPanel;
    private JLabel testLabel;
    private JTextField urltest;
    private JButton ChoseButton;

    public ShowUi() {
        ChoseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showOpenDialog(testLabel);
            File file = fileChooser.getSelectedFile();
            urltest.setText(file.getPath());
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JLabel getTestLabel() {
        return testLabel;
    }

    public JTextField getUrltest() {
        return urltest;
    }
}
