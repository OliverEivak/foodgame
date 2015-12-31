package ee.ttu.foodgame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import ee.ttu.foodgame.App;
import ee.ttu.foodgame.screen.GameScreen;
import ee.ttu.foodgame.screen.HighScoresScreen;
import ee.ttu.foodgame.screen.InstructionScreen;
import ee.ttu.foodgame.utils.FileUtils;
import ee.ttu.foodgame.utils.SFX;
import ee.ttu.foodgame.view.View;

public class GameMainMenuController extends Controller {

    private final App game;

    private Stage stage;

    private View view;

    private TextButton startButton;
    private TextButton highScoresButton;
    private TextButton guideButton;

    public GameMainMenuController(Stage stage, View view, App game) {
        this.game = game;
        this.stage = stage;
        this.view = view;

        startButton = createStartButton();
        highScoresButton = createScoresButton();
        guideButton = createGuideButton();

        Table table = createTableWithButtons(startButton, highScoresButton, guideButton);
        stage.addActor(table);

        setInputProcessor();

        SFX.initialize();
        Gdx.input.setCatchBackKey(false);
    }

    public void update(float delta) {

    }

    protected TextButton createStartButton() {
        FileHandle atlasFile = FileUtils.getFile("ui/ui-green.atlas");
        FileHandle fontFile = FileUtils.getFile("ui/BlackBigStyle.fnt");
        return createButton(atlasFile, fontFile, "button_02", "button_01", "Start new Game");
    }

    protected TextButton createScoresButton() {
        FileHandle atlasFile = FileUtils.getFile("ui/ui-green.atlas");
        FileHandle fontFile = FileUtils.getFile("ui/BigStyle.fnt");
        return createButton(atlasFile, fontFile, "button_01", "button_02", "View High Scores");
    }

    protected TextButton createGuideButton() {
        FileHandle atlasFile = FileUtils.getFile("ui/ui-green.atlas");
        FileHandle fontFile = FileUtils.getFile("ui/BigStyle.fnt");
        return createButton(atlasFile, fontFile, "button_01", "button_02", "How to play");
    }

    private void setInputProcessor() {
        Gdx.input.setInputProcessor(stage);

        startButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SFX.playButton1();

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
            }

        });

        highScoresButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SFX.playButton1();

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new HighScoresScreen(game));
            }

        });
        
        
        guideButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SFX.playButton1();

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new InstructionScreen(game));
            }

        });
    }

}
