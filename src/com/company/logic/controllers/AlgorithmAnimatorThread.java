package com.company.logic.controllers;

import com.company.logic.algorithm.Algorithm;

public class AlgorithmAnimatorThread extends Thread {
    private final int maxFrameWaitInMs = 80;
    private int currentFrameWaitInMs;
    private boolean pause = true, abort = false;

    private final Algorithm algorithm;

    public AlgorithmAnimatorThread(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setTempo(int percentage) {
        currentFrameWaitInMs = (int)(maxFrameWaitInMs * (percentage * 1.0f / 100));
    }

    public void togglePause() {
        pause = !pause;
    }

    public void abort() {
        abort = false;
    }

    @Override
    public void run() {
        while (!abort) {
            try {
                while (pause) {
                    Thread.sleep(10);
                }

                algorithm.step();

                Thread.sleep(currentFrameWaitInMs);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isPaused() {
        return pause;
    }
}
