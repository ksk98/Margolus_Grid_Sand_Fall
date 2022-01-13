package com.company.logic.controllers;

import com.company.UI.components.ControlPanel;
import com.company.logic.algorithm.Algorithm;
import com.company.logic.algorithm.AlgorithmImpl;

import javax.swing.*;

public class AlgorithmController {
    private Algorithm algorithmImpl;
    private AlgorithmAnimatorThread thread;

    public AlgorithmController(int[][] data, SandDisplay sandDisplay) {
        algorithmImpl = new AlgorithmImpl(data, sandDisplay);
        thread = new AlgorithmAnimatorThread(algorithmImpl);
        thread.start();
    }

    public void bindPanelButtons(ControlPanel controlPanel) {
        JButton startStop = controlPanel.getStartStop();
        JButton step = controlPanel.getStep();
        startStop.addActionListener(actionEvent -> {
            if (startStop.getText().equals("START")) {
                step.setEnabled(false);
                startStop.setText("STOP");
            } else if (startStop.getText().equals("STOP")) {
                step.setEnabled(true);
                startStop.setText("START");
            }

            thread.togglePause();
        });

        step.addActionListener(actionEvent -> {
            if (thread.isPaused())
                algorithmImpl.step();
        });

        JSlider tempo = controlPanel.getTempo();
        tempo.addChangeListener(actionEvent -> {
            int value = tempo.getValue();
            int invertedValue = (value - 100) * -1;

            thread.setTempo(invertedValue);
        });
        thread.setTempo(tempo.getValue());

        JButton mode = controlPanel.getMode();
        mode.addActionListener(actionEvent -> {
            if (mode.getText().equals("LINE")) {
                mode.setText("CENTER");
            } else if (mode.getText().equals("CENTER")) {
                mode.setText("LINE");
            }

            algorithmImpl.toggleMode();
        });
    }

    public void abort() {
        thread.abort();
    }
}
