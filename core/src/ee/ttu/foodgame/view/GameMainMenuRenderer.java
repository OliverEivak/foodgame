package ee.ttu.foodgame.view;

import static ee.ttu.foodgame.App.GAME_HEIGHT;
import static ee.ttu.foodgame.App.GAME_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import ee.ttu.foodgame.utils.FileUtils;

public class GameMainMenuRenderer implements View {

    private Stage stage;
    private Label textLabel;
    private LabelStyle textStyle;
    private BitmapFont font;
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    private Sprite background;
    private OrthographicCamera camera;

    public GameMainMenuRenderer(Stage stage) {
        this.stage = stage;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);

        spriteBatch = new SpriteBatch();

        font = new BitmapFont(FileUtils.getFile("endgameFont.fnt"));

        textStyle = new LabelStyle(font, Color.BLACK);
        textLabel = new Label("Foodgame", textStyle);

        textLabel.setPosition(GAME_WIDTH * 0.65f, GAME_HEIGHT * 0.9f);

        stage.addActor(textLabel);
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1)));

        backgroundTexture = new Texture(FileUtils.getFile("chef.png"));
        background = new Sprite(backgroundTexture);
        background.setSize(GAME_WIDTH, GAME_HEIGHT);

        System.out.println("GameMainMenuRenderer: " + GAME_WIDTH + " " + GAME_WIDTH);
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void render(float delta) {
        clearScreen();

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, GAME_WIDTH, GAME_HEIGHT);
        spriteBatch.end();

        stage.act(delta);
        stage.draw();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(118f / 255f, 167 / 255f, 184f / 255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void dispose() {
        font.dispose();
        spriteBatch.dispose();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

}