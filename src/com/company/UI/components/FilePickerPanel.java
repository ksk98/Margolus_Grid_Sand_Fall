package com.company.UI.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FilePickerPanel extends JPanel {
    private final JTextArea pathTextArea;
    private final JButton loadButton;

    public FilePickerPanel() {
        setPreferredSize(new Dimension(500, 70));
        setLayout(new FlowLayout());

        Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Map File");
        setBorder(border);

        pathTextArea = new JTextArea("");

        pathTextArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
        pathTextArea.setRows(1);
        JScrollPane scrollPane = new JScrollPane(
                pathTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );
        scrollPane.setPreferredSize(new Dimension(400, 35));
        add(scrollPane);

        loadButton = new JButton("LOAD");
        add(loadButton);

        revalidate();
    }

    public JTextArea getPathTextArea() {
        return pathTextArea;
    }

    public JButton getLoadButton() {
        return loadButton;
    }
}
