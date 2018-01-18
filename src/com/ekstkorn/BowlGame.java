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
    private int roll;
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

        for (int rolling = 0; rolling < seq.length && currentFrame < ALL_FRAME; rolling++) {

            int rollingPoint = 0;
            int calBonusFrame = currentFrame;
            Frame frame = frameList.get(currentFrame);

            //Last frame
            if (currentFrame == ALL_FRAME - 1) {
                frame.setLastFrame(true);
            }

            if (seq[rolling] == 'X' || seq[rolling] == 'x') { //Strike
                rollingPoint = ALL_PIN;
                frame.setBonusRoll(2);

                if (frame.isLastFrame()) {
                    frame.setMaxPlayBall(3);
                    if (frame.addScore(rollingPoint)) {
                        currentFrame++;
                    }
                } else {

                    frame.setScore(rollingPoint);
                    bonusList.add(currentFrame);
                    currentFrame++;

                }
            } else if (seq[rolling] == '/') { //Spare
                rollingPoint = ALL_PIN - frameList.get(currentFrame).getScore();
                frame.setBonusRoll(1);

                if (frame.isLastFrame()) {
                    frame.setMaxPlayBall(4);
                    if (frame.addScore(rollingPoint)) {
                        currentFrame++;
                    }
                } else {

                    frame.setScore(ALL_PIN);
                    bonusList.add(currentFrame);
                    currentFrame++;

                }
            } else if (seq[rolling] == '-') { //Miss ball
                rollingPoint = 0;

                if (frame.addScore(rollingPoint)) {
                    currentFrame++;
                }
            } else {
                //Normal case
                rollingPoint = Character.getNumericValue(seq[rolling]);
                if (frame.addScore(rollingPoint)) {
                    currentFrame++;
                }
            }


            totalScore += rollingPoint; //score by roll
            totalScore += addBonus(rollingPoint, calBonusFrame); //score by bonus

        }

        displayScorebyFrame();
        return totalScore;

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

    private void displayScorebyFrame() {
        System.out.print("FRAME >\t");
        for (int i = 1; i <= ALL_FRAME; i++) {
            System.out.print(i + "\t");
        }

        System.out.print("\n\t\t");

        int sumScore = 0;
        for (int j = 0; j < frameList.size(); j++) {
            sumScore += frameList.get(j).getScore();
            System.out.print(sumScore + "\t");
        }
    }


}
