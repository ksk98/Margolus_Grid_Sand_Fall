package com.company.logic.controllers;

import com.company.UI.components.DrawBoard;
import com.company.UI.utility.IconCreator;

public class SandDisplay {
    private DrawBoard drawBoard;

    public SandDisplay(DrawBoard drawBoard) {
        this.drawBoard = drawBoard;
    }

    public boolean drawOn(int x, int y) {
        try {
            drawBoard.draw(x, y, IconCreator.makeIconOfType(IconCreator.TypeColor.SAND));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        return true;
    }
    public void eraseFrom(int x, int y) {
        try {
            drawBoard.draw(x, y, IconCreator.makeIconOfType(IconCreator.TypeColor.EMPTY));
        } catch (IndexOutOfBoundsException e) {
            // Shouldn't happen, if somehow does just ignore it
        }
    }
}
