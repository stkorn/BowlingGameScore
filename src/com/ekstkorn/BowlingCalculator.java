package com.ekstkorn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Bowling score calculator
 *  Ekstkorn 19/01/2018
 */

public class BowlingCalculator {

    public static void main(String[] args) {
        BowlGame bowlGame = new BowlGame();
        System.out.println("#### Bowling Score  ####\n");
        System.out.print("Enter sequence : ");
        String seq = "";
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            seq = buffer.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        int score = bowlGame.calculateScore(seq);
        bowlGame.displayScorebyFrame();
        System.out.println("\n\nTotal Score = " + score);
    }

}
