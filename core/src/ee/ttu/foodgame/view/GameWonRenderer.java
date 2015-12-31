package ee.ttu.foodgame.view;

import static ee.ttu.foodgame.App.GAME_HEIGHT;
import static ee.ttu.foodgame.App.GAME_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import ee.ttu.foodgame.controller.Save;
import ee.ttu.foodgame.utils.FileUtils;
import ee.ttu.foodgame.utils.SFX;

public class GameWonRenderer implements View {

    private Stage stage;
    private Label scoreLabel;
    private Label wonLabel;
    private Label highestScoreLabel;
    private Label averageScoreLabel;
    private LabelStyle textStyle;
    private BitmapFont font;
    private OrthographicCamera camera;

    public GameWonRenderer(Stage stage, int score) {
        this.stage = stage;

        font = new BitmapFont(FileUtils.getFile("endgameFont.fnt"));

        textStyle = new LabelStyle(font, Color.BLACK);
        scoreLabel = new Label("score = " + score, textStyle);
        wonLabel = new Label("You won", textStyle);
        highestScoreLabel = new Label("Highest score = " + Save.s.getHighestScore(), textStyle);
        averageScoreLabel = new Label("Average score = " + Save.s.getAverageScore(), textStyle);

        if (score == Save.s.getHighestScore()) {
            SFX.playApplause();
        }

        wonLabel.setPosition(GAME_WIDTH * 0.35f, GAME_HEIGHT * 0.7f);
        scoreLabel.setPosition(GAME_WIDTH * 0.35f, GAME_HEIGHT * 0.6f);
        highestScoreLabel.setPosition(GAME_WIDTH * 0.15f, GAME_HEIGHT * 0.5f);
        averageScoreLabel.setPosition(GAME_WIDTH * 0.15f, GAME_HEIGHT * 0.4f);

        stage.addActor(wonLabel);
        stage.addActor(scoreLabel);
        stage.addActor(highestScoreLabel);
        stage.addActor(averageScoreLabel);
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void render(float delta) {
        clearScreen();

        stage.act(delta);
        stage.draw();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(118f / 255f, 167 / 255f, 184f / 255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void dispose() {
        font.dispose();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

}