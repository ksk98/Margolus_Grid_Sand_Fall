package com.company.UI.components;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private final JButton startStop, step, mode, exit;
    private final JSlider tempo;

    public ControlPanel() {
        startStop = new JButton("START");
        step = new JButton("STEP");
        tempo = new JSlider(SwingConstants.HORIZONTAL, 1, 100, 30);
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

    public JSlider getTempo() {
        return tempo;
    }

    public JButton getMode() {
        return mode;
    }

    public JButton getExit() {
        return exit;
    }
}
