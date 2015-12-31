package ee.ttu.foodgame.model;

import java.util.ArrayList;
import java.util.List;

public class World {

    private List<Food> unusedFoods;
    private List<Food> foods;

    private int correctCount;
    private int score;

    private boolean gameOver = false;

    public World() {
        unusedFoods = new ArrayList<Food>();
        foods = new ArrayList<Food>();

        correctCount = 0;
        score = 0;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<Food> getUnusedFoods() {
        return unusedFoods;
    }

    public void setUnusedFoods(List<Food> unusedFoods) {
        this.unusedFoods = unusedFoods;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

}
