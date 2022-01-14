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

    private static final int grainSpawnChance = 40;
    private Mode spawnMode;

    private Iterator<Coordinate> iterator;
    private boolean iterationIsEven = true;
    private Phase currentPhase;

    public AlgorithmImpl(int[][] matrix, SandDisplay display) {
        this.matrix = matrix;
        this.gridIndexes = new LinkedList<>();
        this.display = display;
        this.spawnMode = Mode.LINE;

        currentPhase = Phase.SPAWNING;
    }

    private void spawnGrain(int x, int y) {
        writeToMatrix(x, y, SAND);
    }

    private int getCellContent(int x ,int y) {
        // Cells outside our matrix are treated as walls
        if (x < 0 || y < 0 || x >= matrix[0].length)
            return WALL;
        else if (y >= matrix.length)
            return EMPTY;

        return matrix[y][x];
    }

    private void writeToMatrix(int x, int y, int type) {
        boolean possible = true;
        if (type == SAND)
            possible = display.drawOn(x, y);
        else if (type == EMPTY)
            possible = display.eraseFrom(x, y);

        if (possible)
            matrix[y][x] = type;
    }

    private void updateCube(Cube cube) {
        int[][] pattern = cube.pattern;

        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length; j++) {
                if (cube.pattern[i][j] == SAND)
                    writeToMatrix(cube.x + j, cube.y + i, SAND);
                else if (cube.pattern[i][j] == EMPTY)
                    writeToMatrix(cube.x + j, cube.y + i, EMPTY);
            }
        }

    }

    private Cube getNextCube() {
        Coordinate currentCoordinate = iterator.next();

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
                if (i % 2 == 1)
                    gridIndexes.add(new Coordinate(-1, i));
                for (int j = -1; j < matrix[0].length; j++) {
                    if (i % 2 == 1 && j % 2 == 1)
                        gridIndexes.add(new Coordinate(j, i));
                }
            }
        }

        iterationIsEven = !iterationIsEven;
        iterator = gridIndexes.descendingIterator();
    }

    @Override
    public void step() {
        if (currentPhase == Phase.SPAWNING) {
            if (spawnMode == Mode.LINE) {
                Random random = new Random();

                for (int i = 0; i < matrix[0].length; i++)
                    if (matrix[0][i] == EMPTY && random.nextInt(100) < grainSpawnChance)
                        spawnGrain(i, 0);

            } else if (spawnMode == Mode.CENTER) {
                spawnGrain(matrix[0].length / 2, 0);
                spawnGrain((matrix[0].length / 2) + 1, 0);
            }

            currentPhase = Phase.DROPPING;
            setGridIndexes();
        } else if (currentPhase == Phase.DROPPING) {
            if (!iterator.hasNext()) {
                currentPhase = Phase.SPAWNING;
            } else {
                Cube currentCube = null;
                while (iterator.hasNext()) {
                    Cube tempCube = getNextCube();
                    if (tempCube.hasSand()) {
                        currentCube = tempCube;
                        break;
                    }
                }

                if (currentCube == null) {
                    currentPhase = Phase.SPAWNING;
                    return;
                }

                if (currentCube.pattern[0][0] == SAND) {
                    if (currentCube.pattern[1][0] == EMPTY) {
                        // Top left is sand and can fall directly down
                        currentCube.pattern[0][0] = EMPTY;
                        currentCube.pattern[1][0] = SAND;
                    } else if (currentCube.pattern[1][1] == EMPTY && currentCube.pattern[0][1] == EMPTY) {
                        // Top left is sand and can't fall directly down but can fall sideways
                        currentCube.pattern[0][0] = EMPTY;
                        currentCube.pattern[1][1] = SAND;
                    }
                }

                if (currentCube.pattern[0][1] == SAND) {
                    if (currentCube.pattern[1][1] == EMPTY) {
                        // Top right is sand and can fall directly down
                        currentCube.pattern[0][1] = EMPTY;
                        currentCube.pattern[1][1] = SAND;
                    } else if (currentCube.pattern[1][0] == EMPTY && currentCube.pattern[0][0] == EMPTY ) {
                        // Top right is sand and can't fall directly down but can fall sideways
                        currentCube.pattern[0][1] = EMPTY;
                        currentCube.pattern[1][0] = SAND;
                    }
                }

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
