package ee.ttu.foodgame;

import com.badlogic.gdx.Game;

import ee.ttu.foodgame.screen.GameMainMenuScreen;

public class App extends Game {

    /**
     * Abstract units (not pixels)
     */
    public static final int GAME_WIDTH = 9 * 50;
    public static final int GAME_HEIGHT = 16 * 50;

    public static final int UNIT = 8;

    public static final int FOODS = 63;

    // Use this for development
    public static final boolean CHEAT_MODE = false;

    public void create() {
        this.setScreen(new GameMainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
    }

}