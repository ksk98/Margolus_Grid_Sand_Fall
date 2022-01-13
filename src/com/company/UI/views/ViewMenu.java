package com.company.UI.views;

import com.company.UI.components.FilePickerPanel;

import javax.swing.*;
import java.awt.*;

public class ViewMenu extends ViewBase {
    private final FilePickerPanel filePickerPanel;
    private final JButton simulate;

    public ViewMenu() {
        setLayout(new GridLayout(2, 0));

        filePickerPanel = new FilePickerPanel();
        add(filePickerPanel);

        simulate = new JButton("SIMULATE");
        simulate.setEnabled(false);
        add(simulate);

        pack();
        centerScreen();
    }

    public FilePickerPanel getFilePickerPanel() {
        return filePickerPanel;
    }

    public JButton getSimulate() {
        return simulate;
    }
}
