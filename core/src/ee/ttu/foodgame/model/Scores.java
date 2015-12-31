package ee.ttu.foodgame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Scores implements Serializable {

    private static final long serialVersionUID = 1;

    private final int MAX_SCORES = 200;
    private ArrayList<Integer> highScores;
    private int averageScore;

    public Scores() {
        highScores = new ArrayList<Integer>();
        averageScore = 0;
    }

    public ArrayList<Integer> getScores() {
        return highScores;
    }

    public int getHighestScore() {
        return highScores.get(0);
    }

    private void setAverageScore() {
        averageScore = 0;
        if (highScores.isEmpty())
            return;
        for (int score : highScores) {
            averageScore += score;
        }
        averageScore /= highScores.size();
    }

    public int getAverageScore() {
        setAverageScore();
        return averageScore;
    }

    public boolean isHighestScore(int score) {
        return score > highScores.get(0);
    }

    public void setScore(int score) {
        highScores.add(score);
        Collections.sort(highScores, Collections.reverseOrder());
        if (highScores.size() > MAX_SCORES) {
            highScores.remove(MAX_SCORES);
        }
    }
}
