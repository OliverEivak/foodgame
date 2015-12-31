package ee.ttu.foodgame.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import ee.ttu.foodgame.App;
import ee.ttu.foodgame.controller.WorldController;
import ee.ttu.foodgame.model.World;
import ee.ttu.foodgame.view.WorldRenderer;

public class GameScreen implements Screen {

    private final App game;

    private World world;

    private Stage stage;
    private WorldRenderer renderer;
    private WorldController controller;

    public GameScreen(final App game) {
        this.game = game;

        world = new World();

        stage = new Stage(new FitViewport(App.GAME_WIDTH, App.GAME_HEIGHT));
        renderer = new WorldRenderer(stage, world, game);
        controller = new WorldController(world, renderer, game);
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
    }

}
