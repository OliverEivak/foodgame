package ee.ttu.foodgame.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ee.ttu.foodgame.model.Scores;

public class SaveTest {

    @Test
    public void saveSerialize() throws Exception {
        Scores test = new Scores();
        test.setScore(5);
        byte[] saved = Save.serialize(test);
        Scores savedScores = (Scores) Save.deserialize(saved);
        assertEquals(test.getScores().get(0), savedScores.getScores().get(0));
    }

}
