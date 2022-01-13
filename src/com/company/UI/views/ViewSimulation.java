package com.company.UI.views;

import com.company.UI.components.BoardPanel;
import com.company.UI.components.ControlPanel;
import com.company.logic.controllers.AlgorithmController;
import com.company.logic.controllers.SandDisplay;

import javax.swing.*;

public class ViewSimulation extends ViewBase {
    private final BoardPanel boardPanel;
    private final ControlPanel controlPanel;
    private final AlgorithmController controller;

    public ViewSimulation(int[][] data) {
        boardPanel = new BoardPanel(data);
        controlPanel = new ControlPanel();
        controller = new AlgorithmController(data, new SandDisplay(boardPanel));
        controller.bindPanelButtons(controlPanel);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(boardPanel);
        add(controlPanel);
        pack();
        centerScreen();
    }

    public void haltSimulation() {
        controller.abort();
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }
}
