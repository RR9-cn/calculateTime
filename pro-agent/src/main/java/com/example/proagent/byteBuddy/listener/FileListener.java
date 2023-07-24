package com.example.proagent.byteBuddy.listener;

import com.example.proagent.byteBuddy.SharedInformation;
import com.example.proagent.byteBuddy.action.ReadFactory;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/7/13 10:47
 */
public class FileListener implements Runnable{
    WatchService watcher = FileSystems.getDefault().newWatchService();

    Map<String,String> map = new HashMap<>();

    public FileListener() throws IOException {
        Paths.get(System.getProperty("user.home") + "\\timeLog").register(watcher,
                new WatchEvent.Kind[]{StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_MODIFY,
                        StandardWatchEventKinds.ENTRY_DELETE},
                SensitivityWatchEventModifier.HIGH);
    }


    public void run() {
        while (true){
            WatchKey key = null;
            try {
                // 将阻塞调用直到事件发生
                key = watcher.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                if(kind == StandardWatchEventKinds.ENTRY_DELETE){
                    continue;
                }
                if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    Path path = Path.of(SharedInformation.baseDir + SharedInformation.fileName);
                    try {
                        List<String> data = Files.readAllLines(path);
                        for (String datum : data) {
                            String[] split = datum.split(":");
                            String packageName = split[0];
                            map.put(packageName,datum);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            ReadFactory.readUI.getTextPane().setText("");
            map.keySet().forEach(e -> {
                String s = map.get(e);
                appendToPane(ReadFactory.readUI.getTextPane(),
                        s + "\n", StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE));
            });
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
