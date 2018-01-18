package com.ekstkorn.test;

import com.ekstkorn.BowlGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Bowling score calculator
 *  Ekstkorn 19/01/2018
 */

public class BowlGameTest {

    @Test
    public void all_strike_test() {
        BowlGame bowlGame = new BowlGame();
        assertEquals(300, bowlGame.calculateScore("XXXXXXXXXXXX"));
    }

    @Test
    public void nine_and_miss_test() {
        BowlGame bowlGame = new BowlGame();
        assertEquals(90, bowlGame.calculateScore("9-9-9-9-9-9-9-9-9-9-"));
    }

    @Test
    public void five_and_spare_test() {
        BowlGame bowlGame = new BowlGame();
        assertEquals(150, bowlGame.calculateScore("5/5/5/5/5/5/5/5/5/5/5"));
    }

    @Test
    public void random_test() {
        BowlGame bowlGame = new BowlGame();
        assertEquals(119, bowlGame.calculateScore("7/9-xx9-12812---7/x"));
    }

}
