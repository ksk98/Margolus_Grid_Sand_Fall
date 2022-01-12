package com.company.UI.views;

import com.company.UI.components.BoardPanel;
import com.company.UI.components.ControlPanel;

import javax.swing.*;

public class ViewSimulation extends ViewBase {
    private final BoardPanel boardPanel;
    private final ControlPanel controlPanel;

    public ViewSimulation(int[][] data) {
        boardPanel = new BoardPanel(data);
        controlPanel = new ControlPanel();

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(boardPanel);
        add(controlPanel);
        pack();
        centerScreen();
    }

    public void haltSimulation() {
        // TODO: do dat
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }
}
