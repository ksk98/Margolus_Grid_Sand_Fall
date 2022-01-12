package com.company.UI.components;

import com.company.UI.utility.IconCreator;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel implements DrawBoard {
    private final int sizeX, sizeY;

    public BoardPanel(int[][] data) {
        sizeX = data[0].length;
        sizeY = data.length;

        setLayout(new GridLayout(sizeY, sizeX));
        reset();
        drawFromData(data);
    }

    public void draw(int x, int y, ImageIcon icon) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY)
            throw new IndexOutOfBoundsException();

        JLabel selected = (JLabel) getComponentAt(x, y);
        selected.setIcon(icon);
    }

    public void drawFromData(int[][] data) {
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                if (data[i][j] == 1) {
                    JLabel selected = (JLabel) getComponent((i * sizeX) + j);
                    selected.setIcon(IconCreator.makeIconOfType(IconCreator.TypeColor.WALL));
                }
            }
        }
    }

    public void reset() {
        removeAll();

        for (int i = 0; i < sizeY; i++)
            for (int j = 0; j < sizeX; j++)
                add(new JLabel(IconCreator.makeIconOfType(IconCreator.TypeColor.EMPTY)));
    }
}
