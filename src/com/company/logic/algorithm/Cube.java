package com.company.logic.algorithm;

import java.util.Arrays;

public class Cube {
    // Position in matrix
    public final int x, y;
    public final int[][] pattern;

    public Cube(int x, int y, int[][] pattern) {
        this.x = x;
        this.y = y;
        this.pattern = pattern;
    }

    public Cube(Cube other) {
        this.x = other.x;
        this.y = other.y;
        this.pattern = new int[other.pattern.length][];
        for (int i = 0; i < pattern.length; i++)
            this.pattern[i] = Arrays.copyOf(other.pattern[i], other.pattern.length);
    }

    public boolean hasSand() {
        for (int i = 0; i < pattern.length; i++)
            for (int j = 0; j < pattern[i].length; j++)
                if (pattern[i][j] == 2)
                    return true;

        return false;
    }
}
