package com.company.logic.controllers;

import com.company.UI.views.ViewBase;
import com.company.UI.views.ViewMenu;
import com.company.UI.views.ViewSimulation;
import com.company.logic.utility.DataConverter;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ViewManager {
    public enum Views {MENU, SIMULATION}
    private final HashMap<Views, ViewBase> views;
    private Views current = null;

    public ViewManager() {
        views = new HashMap<>();
        createMenu();
        switchTo(Views.MENU);
    }

    private void createMenu() {
        ViewMenu view = new ViewMenu();

        view.getFilePickerPanel().getLoadButton().addActionListener(actionEvent -> {
            String path = view.getFilePickerPanel().getPathTextArea().getText();
            File file = new File(path);
            if (file.exists()) {
                if (path.endsWith(".txt")) {
                    view.getSimulate().setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(view, "File has improper extension, only .txt accepted.");
                    view.getFilePickerPanel().getPathTextArea().setText("");
                    view.getSimulate().setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(view, "File doesn't exist.");
                view.getFilePickerPanel().getPathTextArea().setText("");
                view.getSimulate().setEnabled(false);
            }
        });

        view.getSimulate().addActionListener(actionEvent -> {
            String path = view.getFilePickerPanel().getPathTextArea().getText();

            try {
                int[][] data = DataConverter.getMapDataFromFilePath(path);
                createSimulation(data);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            switchTo(Views.SIMULATION);
        });

        views.put(Views.MENU, view);
    }

    private void createSimulation(int[][] data) {
        ViewSimulation view = new ViewSimulation(data);
        view.getControlPanel().getExit().addActionListener(actionEvent -> {
            view.haltSimulation();
            switchTo(Views.MENU);
            views.remove(Views.SIMULATION);
        });

        views.put(Views.SIMULATION, view);
    }

    private void switchTo(Views view) {
        if (views.get(current) != null)
            views.get(current).setVisible(false);
        current = view;
        views.get(current).setVisible(true);
    }
}
