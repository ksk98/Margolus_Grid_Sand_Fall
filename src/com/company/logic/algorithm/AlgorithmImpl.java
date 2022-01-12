package com.company.logic.algorithm;

import com.company.logic.controllers.SandDisplay;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class AlgorithmImpl implements Algorithm {
    private enum Mode {LINE, CENTER}
    private enum Phase {DROPPING, SPAWNING}
    private static final int EMPTY = 0, WALL = 1, SAND = 2;
    private final int[][] matrix;
    private SandDisplay display;
    private LinkedList<Coordinate> gridIndexes;

    private static final int grainSpawnChance = 20;
    private Mode spawnMode;
    private Iterator<Coordinate> iterator;
    private boolean iterationIsEven = true;
    private Phase currentPhase;

    public AlgorithmImpl(int[][] matrix, SandDisplay display) {
        this.matrix = matrix;
        this.gridIndexes = new LinkedList<>();
        this.display = display;
        this.spawnMode = Mode.LINE;

        iterator = gridIndexes.descendingIterator();
        currentPhase = Phase.SPAWNING;
        setGridIndexes();
    }

    private void spawnGrain(int x, int y) {
        writeToMatrix(x, y, SAND);
    }

    private int getCellContent(int x ,int y) {
        // Cells outside our matrix are treated as walls
        if (x < 0 || y < 0 || x >= matrix[0].length || y >= matrix.length)
            return WALL;

        return matrix[y][x];
    }

    private void writeToMatrix(int x, int y, int type) {
        matrix[y][x] = type;
        if (type == SAND)
            display.drawOn(x, y);
        else if (type == EMPTY)
            display.eraseFrom(x, y);
    }

    private void updateCube(Cube cube) {
        int[][] pattern = cube.pattern;

        if (pattern[0][0] == SAND)
            display.drawOn(cube.x, cube.y);
        else if (pattern[0][0] == EMPTY)
            display.eraseFrom(cube.x, cube.y);

        for (int i = 0; i < pattern.length; i++)
            for (int j = 0; j < pattern[i].length; j++)
                if (pattern[j][i] != WALL)
                    writeToMatrix(cube.x + j, cube.y + i, pattern[j][i]);

    }

    private Cube getNextCube() {
        Coordinate currentCoordinate = iterator.next();
        // if null return?

        int[][] pattern = new int[2][2];
        // pattern[y][x]
        pattern[0][0] = getCellContent(currentCoordinate.x, currentCoordinate.y);
        pattern[0][1] = getCellContent(currentCoordinate.x + 1, currentCoordinate.y);
        pattern[1][0] = getCellContent(currentCoordinate.x, currentCoordinate.y + 1);
        pattern[1][1] = getCellContent(currentCoordinate.x + 1, currentCoordinate.y + 1);

        return new Cube(currentCoordinate.x, currentCoordinate.y, pattern);
    }

    private void setGridIndexes() {
        gridIndexes.clear();

        if (iterationIsEven) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (i % 2 == 0 && j % 2 == 0)
                        gridIndexes.add(new Coordinate(j, i));
                }
            }
        } else {
            for (int i = -1; i < matrix.length; i++) {
                for (int j = -1; j < matrix[0].length; j++) {
                    if (i % 2 == 0 && j % 2 == 0)
                        gridIndexes.add(new Coordinate(j, i));
                }
            }
        }

        iterationIsEven = !iterationIsEven;
    }

    @Override
    public void step() {
        if (currentPhase == Phase.SPAWNING) {
            if (spawnMode == Mode.LINE) {
                Random random = new Random();

                for (int i = 0; i < matrix[0].length; i++)
                    if (random.nextInt(100) < grainSpawnChance)
                        spawnGrain(i, 0);

            } else if (spawnMode == Mode.CENTER) {
                spawnGrain(matrix[0].length / 2, 0);
                spawnGrain((matrix[0].length / 2) + 1, 0);
            }

            currentPhase = Phase.DROPPING;
        } else if (currentPhase == Phase.DROPPING) {
            if (!iterator.hasNext()) {
                setGridIndexes();
                currentPhase = Phase.SPAWNING;
            } else {
                Cube currentCube = getNextCube();

                // TODO: change pattern accordingly
                // TODO: later make code that calls the steps

                updateCube(currentCube);
            }

        }
    }

    @Override
    public void toggleMode() {
        if (spawnMode == Mode.LINE)
            spawnMode = Mode.CENTER;
        else
            spawnMode = Mode.LINE;
    }
}
