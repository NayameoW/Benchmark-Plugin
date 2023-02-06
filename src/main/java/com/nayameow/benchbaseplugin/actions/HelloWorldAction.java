package com.nayameow.benchbaseplugin.actions;

import com.google.protobuf.Message;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.nayameow.benchbaseplugin.Benchbase;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class HelloWorldAction extends AnAction {
    @Override
    public void update(@NotNull AnActionEvent event) {
        Project currentProject = event.getProject();
        event.getPresentation().setEnabledAndVisible(currentProject != null);
        super.update(event);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Messages.showInfoMessage("Hello world", "Info");
        Project currentProject = event.getProject();
        Runtime rt = Runtime.getRuntime();

        try {
            String cmd = null;
            if (isWindows()) cmd = "cmd.exe /c";
            Process p = rt.exec(cmd + "dir");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            int exitValue = p.exitValue();

            Messages.showInfoMessage(System.getProperty("user.dir"), "Output");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toUpperCase().contains("WINDOWS");
    }

}