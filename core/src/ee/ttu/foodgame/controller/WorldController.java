package ee.ttu.foodgame.controller;

import static ee.ttu.foodgame.App.FOODS;
import static ee.ttu.foodgame.App.GAME_HEIGHT;
import static ee.ttu.foodgame.App.UNIT;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import ee.ttu.foodgame.App;
import ee.ttu.foodgame.model.Food;
import ee.ttu.foodgame.model.World;
import ee.ttu.foodgame.screen.GameLostScreen;
import ee.ttu.foodgame.screen.GameMainMenuScreen;
import ee.ttu.foodgame.screen.GameWonScreen;
import ee.ttu.foodgame.utils.SFX;
import ee.ttu.foodgame.view.WorldRenderer;

public class WorldController {

    private final App game;

    private static final int MAX_ROWS = 8;
    private static final int MAX_COLUMNS = 4;

    private World world;

    private Random random = new Random();

    private WorldRenderer view;

    private double lastClick;

    private boolean gameOver = false;

    public WorldController(World world, WorldRenderer worldRenderer, App game) {
        this.game = game;
        this.world = world;
        this.view = worldRenderer;

        Save.load();

        setInputProcessor();
        createFoods();

        addFood();
        lastClick = System.currentTimeMillis();

        Gdx.input.setCatchBackKey(true);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Keys.BACK)) {
            System.out.println("Pressed back");
            game.setScreen(new GameMainMenuScreen(game));
        }
    }

    private void setInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                Vector3 worldCoordinates = view.getCamera().unproject(new Vector3(x, y, 0));
                processTouchDown((int) worldCoordinates.x, (int) worldCoordinates.y);
                return true;
            }
        });
    }

    protected void processTouchDown(int x, int y) {
        Food touchedFood = null;
        for (Food food : findVisibleFoods()) {
            if (food.getBounds().contains(x, y)) {
                touchedFood = food;
                break;
            }
        }

        if (touchedFood != null) {
            processFoodTouch(touchedFood);
        }
    }

    private void processFoodTouch(Food food) {
        if (gameOver) {
            return;
        }

        if (food.isPicked()) {
            gameOver = true;
            gameLost();
        } else {
            System.out.println("Touched a NEW fruit");
            SFX.playRightAnswer();
            view.showGoodJobMessage();
            food.setPicked(true);
            increaseScore();

            List<Food> visibleFoods = findVisibleFoods();
            List<Food> visiblePickedFoods = findVisiblePickedFoods();
            int freeSpots = MAX_ROWS * MAX_COLUMNS - visibleFoods.size();
            int notPickedFoods = visibleFoods.size() - visiblePickedFoods.size();

            if (freeSpots > 0) {
                // No more not picked foods -> add new food
                // Else -> 75% chance to add a new food
                if (notPickedFoods == 0 || random.nextInt(4) > 0) {
                    addFood();
                }

                // Occasionally hide a picked food
                if (world.getFoods().size() % 4 == 0 && world.getFoods().size() >= 8) {
                    hideFood();
                    System.out.println("Hiding one food.");
                }

                // Occasionally add another food
                if (freeSpots > 1 && notPickedFoods == 0 && random.nextInt(8) == 0) {
                    addFood();
                    System.out.println("Adding extra food.");
                }

                shuffleFoods();
            } else {
                gameWon();
            }
        }

        lastClick = System.currentTimeMillis();
    }

    protected void gameLost() {
        saveScore();
        SFX.playWrongAnswer();
        System.out.println("Touched an already picked fruit");
        world.setGameOver(true);

        Timer.schedule(new Task() {
            @Override
            public void run() {
                game.setScreen(new GameLostScreen(game, world.getScore()));
            }
        }, 4);
    }

    private void gameWon() {
        saveScore();
        game.setScreen(new GameWonScreen(game, world.getScore()));
    }

    /*
     * Update score and the number of correct answers
     */
    private void increaseScore() {
        int correctCount = world.getCorrectCount() + 1;
        int score = world.getScore() + calculatePoints();
        world.setCorrectCount(correctCount);
        world.setScore(score);
    }

    private void saveScore() {
        Save.s.setScore(world.getScore());
        Save.save();
    }

    private int calculatePoints() {
        int points = 0;
        if (lastClick != 0) {
            double elapsedTime = (System.currentTimeMillis() - lastClick) / 1000;
            double maxTime = 0.243478 * world.getCorrectCount() + 6.45652;
            points = (int) (10 * (maxTime - elapsedTime));
            points = Math.max(points, 10);
        }
        return points;
    }

    /**
     * Create all foods and assign them identifiers.
     */
    private void createFoods() {
        List<Food> foods = new ArrayList<Food>();
        for (int id = 0; id < FOODS; id++) {
            Food food = new Food();
            food.setId(id);
            foods.add(food);
        }
        world.setUnusedFoods(foods);
    }

    /**
     * Add a single food to the game world.
     */
    private void addFood() {
        int index = random.nextInt(world.getUnusedFoods().size());
        Food food = world.getUnusedFoods().remove(index);
        food.setPosition(calculateNewFoodPosition());
        List<Food> foods = world.getFoods();
        foods.add(food);
    }

    /*
     * Hide a single random picked food.
     */
    private void hideFood() {
        List<Food> visiblePickedFoods = findVisiblePickedFoods();
        int index = random.nextInt(visiblePickedFoods.size());
        Food food = visiblePickedFoods.get(index);
        food.setVisible(false);
    }

    private List<Food> findVisibleFoods() {
        List<Food> visibleFoods = new ArrayList<Food>();
        for (Food food : world.getFoods()) {
            if (food.isVisible()) {
                visibleFoods.add(food);
            }
        }
        return visibleFoods;
    }

    private List<Food> findVisiblePickedFoods() {
        List<Food> foods = new ArrayList<Food>();
        for (Food food : world.getFoods()) {
            if (food.isVisible() && food.isPicked()) {
                foods.add(food);
            }
        }
        return foods;
    }

    private Vector2 calculateNewFoodPosition() {
        int visibleFoods = findVisibleFoods().size();
        return calculatePosition(visibleFoods);
    }

    private Vector2 calculatePosition(int position) {
        int x = (position % MAX_COLUMNS) * (Food.SIZE + UNIT) + 6 * UNIT;
        int y = (position / MAX_COLUMNS) * (Food.SIZE + UNIT) + 16 * UNIT;
        y = GAME_HEIGHT - y;
        return new Vector2(x, y);
    }

    private void shuffleFoods() {
        List<Integer> freePositions = new ArrayList<Integer>();
        for (int i = 0; i < MAX_ROWS * MAX_COLUMNS; i++) {
            freePositions.add(i);
        }

        for (Food food : findVisibleFoods()) {
            int randomIndex = random.nextInt(freePositions.size());
            int randomPosition = freePositions.remove(randomIndex);
            Vector2 position = calculatePosition(randomPosition);
            food.setPosition(position);
        }
    }

}
