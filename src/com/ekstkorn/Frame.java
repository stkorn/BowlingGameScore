package com.ekstkorn;

import java.util.ArrayList;
import java.util.List;

/**
 *  Bowling score calculator
 *  Ekstkorn 19/01/2018
 */

public class Frame {

    private int score;
    private int bonusRoll;
    private int playedBall;
    private int maxPlayBall;
    private boolean isLastFrame;

    public Frame() {
        this.score = 0;
        this.bonusRoll = 0;
        this.playedBall = 0;
        this.maxPlayBall = 2;
        this.isLastFrame = false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBonusRoll() {
        return bonusRoll;
    }

    public void addBonusInFrame(int score) {
        this.score += score;
        bonusRoll -= 1;
    }

    public void setBonusRoll(int bonusRoll) {
        this.bonusRoll = bonusRoll;
    }

    public boolean addScore(int score) {
        playedBall++;
        this.score += score;

        if (playedBall == maxPlayBall) {
            return true;
        }

        return false;
    }

    public boolean isLastFrame() {
        return isLastFrame;
    }

    public void setLastFrame(boolean lastFrame) {
        isLastFrame = lastFrame;
    }

    public int getMaxPlayBall() {
        return maxPlayBall;
    }

    public void setMaxPlayBall(int maxPlayBall) {
        this.maxPlayBall = maxPlayBall;
    }
}
