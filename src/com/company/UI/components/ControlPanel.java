package com.company.UI.components;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private final JButton startStop, step, tempo, mode, exit;

    public ControlPanel() {
        startStop = new JButton("START");
        step = new JButton("STEP");
        tempo = new JButton("FASTER");
        mode = new JButton("LINE");
        exit = new JButton("EXIT");

        setLayout(new FlowLayout());

        add(startStop);
        add(step);
        add(tempo);
        add(mode);
        add(exit);
    }

    public JButton getStartStop() {
        return startStop;
    }

    public JButton getStep() {
        return step;
    }

    public JButton getTempo() {
        return tempo;
    }

    public JButton getMode() {
        return mode;
    }

    public JButton getExit() {
        return exit;
    }
}
