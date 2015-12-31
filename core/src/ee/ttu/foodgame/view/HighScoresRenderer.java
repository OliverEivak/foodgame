package ee.ttu.foodgame.view;

import static ee.ttu.foodgame.App.GAME_HEIGHT;
import static ee.ttu.foodgame.App.GAME_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import ee.ttu.foodgame.App;
import ee.ttu.foodgame.controller.Save;
import ee.ttu.foodgame.utils.FileUtils;

public class HighScoresRenderer implements View {

    private Stage stage;
    private Label titleLabel;
    private Label scoresLabel;
    private Label averageScoreLabel;
    private StringBuilder scoresBuilder;
    private LabelStyle textStyle;
    private BitmapFont font;
    private OrthographicCamera camera;
    private App game;
    private int MAX_SCORES = 8;

    public HighScoresRenderer(Stage stage, App game) {
        this.stage = stage;
        this.game = game;
        Save.load();

        font = new BitmapFont(FileUtils.getFile("endgameFont.fnt"));

        textStyle = new LabelStyle(font, Color.BLACK);
        titleLabel = new Label("Highscores", textStyle);
        scoresLabel = new Label("H", textStyle);
        averageScoreLabel = new Label("Average score - " + Save.s.getAverageScore(), textStyle);
        scoresLabel.setText(getScoresText());

        titleLabel.setPosition(GAME_WIDTH * 0.3f, GAME_HEIGHT * 0.8f);
        scoresLabel.setPosition(GAME_WIDTH * 0.15f, GAME_HEIGHT * 0.5f);
        averageScoreLabel.setPosition(GAME_WIDTH * 0.20f, GAME_HEIGHT * 0.25f);

        stage.addActor(titleLabel);
        stage.addActor(scoresLabel);
        stage.addActor(averageScoreLabel);
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1)));
    }

    private String getScoresText() {
        scoresBuilder = new StringBuilder();
        int i = 0;
        while (i < MAX_SCORES) {
            scoresBuilder.append(i + 1);
            scoresBuilder.append(". ");
            if (i < Save.s.getScores().size()) {
                scoresBuilder.append(Save.s.getScores().get(i));
            } else {
                scoresBuilder.append(0);
            }
            scoresBuilder.append("\n");
            i++;
        }
        return scoresBuilder.toString();
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
