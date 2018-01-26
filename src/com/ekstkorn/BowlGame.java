package com.ekstkorn;

import java.util.ArrayList;
import java.util.List;

/**
 *  Bowling score calculator
 *  Ekstkorn 19/01/2018
 */

public class BowlGame {

    private static final int ALL_FRAME = 10;
    private static final int ALL_PIN = 10;

    private List<Frame> frameList;
    private List<Integer> bonusList;
    private int currentFrame;


    public BowlGame() {
        currentFrame = 0;
        frameList = new ArrayList<>();
        bonusList = new ArrayList<>();

        for (int i = 0; i < ALL_FRAME; i++) {
            frameList.add(new Frame());
        }
    }

    public int calculateScore(String sequence) {
        char[] seq = sequence.toCharArray();
        int totalScore = 0;
        boolean isLastFrame = false;

        for (int rolling = 0; rolling < seq.length && currentFrame < ALL_FRAME; rolling++) {

            int rollingPoint = 0;
            int calBonusFrame = currentFrame;
            Frame frame = frameList.get(currentFrame);

            //Last frame
            if (currentFrame == ALL_FRAME - 1) {
                isLastFrame = true;
            }

            if (seq[rolling] == 'X' || seq[rolling] == 'x') {
                rollingPoint = rollStrikeScore(frame, isLastFrame);
            } else if (seq[rolling] == '/') {
                rollingPoint = rollSpareScore(frame, isLastFrame);
            } else if (seq[rolling] == '-') {
                rollingPoint = rollMiss(frame);
            } else {
                rollingPoint = rollNormalScore(frame, seq[rolling]);
            }

            totalScore = sumScore(totalScore, rollingPoint, calBonusFrame);

        }

        return totalScore;

    }

    private int rollMiss(Frame frame) {
        int rollingPoint;
        rollingPoint = 0;

        if (frame.addScore(rollingPoint)) {
            currentFrame++;
        }
        return rollingPoint;
    }

    private int rollNormalScore(Frame frame, char roll) {
        int rollingPoint;
        rollingPoint = Character.getNumericValue(roll);
        if (frame.addScore(rollingPoint)) {
            currentFrame++;
        }
        return rollingPoint;
    }

    private int rollSpareScore(Frame frame, boolean isLastFrame) {
        int rollingPoint;
        rollingPoint = ALL_PIN - frameList.get(currentFrame).getFrameScore();
        frame.setBonusRoll(1);

        if (isLastFrame) {
            frame.setMaxPlayBall(4);
            if (frame.addScore(rollingPoint)) {
                currentFrame++;
            }
        } else {

            frame.setFrameScore(ALL_PIN);
            bonusList.add(currentFrame);
            currentFrame++;

        }
        return rollingPoint;
    }

    private int rollStrikeScore(Frame frame, boolean isLastFrame) {
        int rollingPoint;
        rollingPoint = ALL_PIN;
        frame.setBonusRoll(2);

        if (isLastFrame) {
            frame.setMaxPlayBall(3);
            if (frame.addScore(rollingPoint)) {
                currentFrame++;
            }
        } else {

            frame.setFrameScore(rollingPoint);
            bonusList.add(currentFrame);
            currentFrame++;

        }
        return rollingPoint;
    }

    private int addBonus(int point, int currentFrame) {
        int totalBonusPoint = 0;
        int i = 0;
        while (i < bonusList.size()) {
            int bonusFrame = bonusList.get(i);
            if (currentFrame != bonusFrame) {
                Frame frame = frameList.get(bonusFrame);
                if (frame.getBonusRoll() > 0) {
                    frame.addBonusInFrame(point);
                    totalBonusPoint += point;
                } else {
                    bonusList.remove(i);
                    continue;
                }
            }
            i++;
        }

        return totalBonusPoint;
    }

    private int sumScore(int totalScore, int rollingPoint, int calBonusFrame) {
        totalScore += rollingPoint; //score by roll
        totalScore += addBonus(rollingPoint, calBonusFrame); //score by bonus
        return totalScore;
    }

    public void displayScorebyFrame() {
        System.out.print("FRAME >\t");
        for (int i = 1; i <= ALL_FRAME; i++) {
            System.out.print(i + "\t");
        }

        System.out.print("\n\t\t");

        int sumScore = 0;
        for (int j = 0; j < frameList.size(); j++) {
            sumScore += frameList.get(j).getFrameScore();
            System.out.print(sumScore + "\t");
        }
    }


}
