package com.company.UI.utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class IconCreator {
    public enum TypeColor {EMPTY, WALL, SAND}

    private static final int borderSize = 24;

    public static ImageIcon makeIconOfColor(Color color) {
        BufferedImage image = new BufferedImage(borderSize, borderSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setPaint(color);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        return new ImageIcon(image);
    }

    public static ImageIcon makeIconOfType(TypeColor typeColor) {
        switch (typeColor) {
            case WALL:
                return makeIconOfColor(new Color(21, 21, 21));
            case SAND:
                return makeIconOfColor(new Color(212, 193, 132));
            case EMPTY:
            default:
                return makeIconOfColor(new Color(204, 204, 204));
        }
    }
}
