package com.example.proagent.byteBuddy.listener;

import com.example.proagent.byteBuddy.SharedInformation;
import com.example.proagent.byteBuddy.action.ReadFactory;
import com.example.proagent.byteBuddy.utils.FilesUtil;
import com.example.proagent.byteBuddy.utils.StrUntil;
import com.example.proagent.byteBuddy.utils.TreeRenderUntil;
import com.sun.nio.file.SensitivityWatchEventModifier;
import de.sciss.treetable.j.DefaultTreeTableNode;

import javax.swing.event.TreeModelEvent;
import javax.swing.tree.TreePath;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * @author CJJ
 * @description TODO
 * @date 2023/7/13 10:47
 */
public class FileListener implements Runnable{
    WatchService watcher = FileSystems.getDefault().newWatchService();

    List<String> strings = Arrays.asList("packageName", "time", "3");

    Map<String,Stack<String>> map = new HashMap<>();

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
            DefaultTreeTableNode root = ((DefaultTreeTableNode) ReadFactory.readUI.getTable().getAdapter().getRoot());
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                if(kind == StandardWatchEventKinds.ENTRY_DELETE){
                    continue;
                }
                if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    root.removeAllChildren();
                    TreeRenderUntil.map.clear();
                    List<String> data = FilesUtil.readFilesLines(SharedInformation.baseDir + SharedInformation.fileName);
                    String longestCommonSubstring = StrUntil.getLongestCommonSubstring(data);
                    for (String datum : data) {
                         String replaceRes = datum.replace(longestCommonSubstring, "");
                         TreeRenderUntil.render(root,replaceRes);
                    }
                }
            }
            TreeModelEvent e = new TreeModelEvent(ReadFactory.readUI.getTable().getTreeModel(), new TreePath(root));
            ReadFactory.readUI.getTable().getAdapter().treeStructureChanged(e);
            // 当所有事件都已处理，重置 watch key 以接收下一批事件
            key.reset();
        }


    }

    private void putData(String packageName,String data) {
        Stack<String> stack = new Stack();
        if (map.containsKey(packageName)) {
            map.get(packageName).add(data);
        }else {
            stack.add(data);
            map.put(packageName,stack);
        }
    }


}
