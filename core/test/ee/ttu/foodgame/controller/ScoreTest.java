package ee.ttu.foodgame.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import ee.ttu.foodgame.model.Scores;

public class ScoreTest {

    @Test
    public void HighScoreTest() throws Exception {
        Scores test = new Scores();
        test.setScore(5);
        test.setScore(4);
        boolean isHighestScore = test.isHighestScore(1000);
        assertTrue(isHighestScore);
    }

    @Test
    public void averageScoreTest() {
    	Scores test = new Scores();
    	test.setScore(6);
    	test.setScore(4);
    	assertEquals(5, test.getAverageScore());
    }
}