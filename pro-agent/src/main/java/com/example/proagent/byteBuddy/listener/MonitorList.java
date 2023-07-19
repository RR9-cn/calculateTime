package com.example.proagent.byteBuddy.listener;

import com.example.proagent.byteBuddy.Count;
import com.example.proagent.byteBuddy.action.ReadFactory;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.sun.nio.file.SensitivityWatchEventModifier;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/7/13 10:47
 */
public class MonitorList implements Runnable{
    WatchService watcher = FileSystems.getDefault().newWatchService();

    public MonitorList() throws IOException {
        Paths.get(System.getProperty("user.home") + "\\timeLog").register(watcher,
                new WatchEvent.Kind[]{StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_MODIFY,
                        StandardWatchEventKinds.ENTRY_DELETE},
                SensitivityWatchEventModifier.HIGH);
    }


    public void run() {
        while (true){
            WatchKey key = null;  // 将阻塞调用直到事件发生
            try {
                key = watcher.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    File file = new File(System.getProperty("user.home") + "\\timeLog\\fileName.txt");
                    Scanner reader = null;
                    try {
                        reader = new Scanner(file);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    ReadFactory.readUI.getTextPane().setText("");
                    while (reader.hasNextLine()) {
                        String data = reader.nextLine();
                        appendToPane(ReadFactory.readUI.getTextPane(),data + "\n", StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE));
                    }
                }
            }

            // 当所有事件都已处理，重置 watch key 以接收下一批事件
            key.reset();
        }


    }

    private static void appendToPane(JTextPane tp, String msg, Style style) {
        StyledDocument doc = tp.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), msg, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}