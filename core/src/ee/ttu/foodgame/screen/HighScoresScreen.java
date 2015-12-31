package ee.ttu.foodgame.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import ee.ttu.foodgame.App;
import ee.ttu.foodgame.controller.HighScoresController;
import ee.ttu.foodgame.view.HighScoresRenderer;

public class HighScoresScreen implements Screen {
    private final App game;

    private Stage stage;
    private HighScoresRenderer renderer;
    private HighScoresController controller;

    public HighScoresScreen(final App game) {
        this.game = game;

        stage = new Stage(new FitViewport(App.GAME_WIDTH, App.GAME_HEIGHT));
        renderer = new HighScoresRenderer(stage, game);
        controller = new HighScoresController(stage, renderer, game);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
        stage.dispose();
    }

}
