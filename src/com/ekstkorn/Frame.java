package com.ekstkorn;

/**
 *  Bowling frameScore calculator
 *  Ekstkorn 19/01/2018
 */

public class Frame {

    private int frameScore;
    private int bonusRoll;
    private int playedBall;
    private int maxPlayBall;
    private boolean isLastFrame;

    public Frame() {
        this.frameScore = 0;
        this.bonusRoll = 0;
        this.playedBall = 0;
        this.maxPlayBall = 2;
        this.isLastFrame = false;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public void setFrameScore(int frameScore) {
        this.frameScore = frameScore;
    }

    public int getBonusRoll() {
        return bonusRoll;
    }

    public void addBonusInFrame(int score) {
        this.frameScore += score;
        bonusRoll -= 1;
    }

    public void setBonusRoll(int bonusRoll) {
        this.bonusRoll = bonusRoll;
    }

    /**
     * When each roll get score, will count by playedBall.
     * @param score
     * @return isLastBall : boolean , return true if that roll is end of frame.
     */
    public boolean addScore(int score) {
        this.frameScore += score;
        playedBall++;

        return isLastBall();
    }

    /**
     *
     * @return Will return true, if current roll is the last of frame.
     */
    private boolean isLastBall() {
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
