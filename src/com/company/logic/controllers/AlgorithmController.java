package com.company.logic.controllers;

import com.company.UI.components.ControlPanel;
import com.company.logic.algorithm.Algorithm;
import com.company.logic.algorithm.AlgorithmImpl;

import javax.swing.*;

public class AlgorithmController {
    private Algorithm algorithmImpl;

    public AlgorithmController(int[][] data, SandDisplay sandDisplay) {
        algorithmImpl = new AlgorithmImpl(data, sandDisplay);
    }

    public void bindPanelButtons(ControlPanel controlPanel) {
        JButton startStop = controlPanel.getStartStop();
        startStop.addActionListener(actionEvent -> {
            if (startStop.getText().equals("START")) {

                startStop.setText("STOP");
            } else if (startStop.getText().equals("STOP")) {

                startStop.setText("START");
            }
        });

        JButton step = controlPanel.getStep();
        step.addActionListener(actionEvent -> {

        });

        JButton tempo = controlPanel.getTempo();
        tempo.addActionListener(actionEvent -> {
            if (tempo.getText().equals("FASTER")) {

                tempo.setText("SLOWER");
            } else if (tempo.getText().equals("SLOWER")) {

                tempo.setText("FASTER");
            }
        });

        JButton mode = controlPanel.getMode();
        tempo.addActionListener(actionEvent -> {
            if (mode.getText().equals("LINE")) {

                mode.setText("CENTER");
            } else if (mode.getText().equals("CENTER")) {

                mode.setText("LINE");
            }
        });
    }
}
