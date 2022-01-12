package com.company.logic.controllers;

import com.company.UI.components.DrawBoard;
import com.company.UI.utility.IconCreator;

public class SandDisplay {
    private DrawBoard drawBoard;

    public SandDisplay(DrawBoard drawBoard) {
        this.drawBoard = drawBoard;
    }

    public void drawOn(int x, int y) {
        try {
            drawBoard.draw(x, y, IconCreator.makeIconOfType(IconCreator.TypeColor.SAND));
        } catch (IndexOutOfBoundsException e) {
            // Sand fell of the map, so just don't draw it.
        }
    }
    public void eraseFrom(int x, int y) {
        try {
            drawBoard.draw(x, y, IconCreator.makeIconOfType(IconCreator.TypeColor.EMPTY));
        } catch (IndexOutOfBoundsException e) {
            // Shouldn't happen, if somehow does just ignore it
        }
    }
}
