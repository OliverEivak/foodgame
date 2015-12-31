package ee.ttu.foodgame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import ee.ttu.foodgame.App;
import ee.ttu.foodgame.screen.GameMainMenuScreen;
import ee.ttu.foodgame.screen.GameScreen;
import ee.ttu.foodgame.utils.FileUtils;
import ee.ttu.foodgame.utils.SFX;
import ee.ttu.foodgame.view.View;

public class GameLostController extends Controller {

    private final App game;

    private Stage stage;

    private View view;

    private TextButton startButton;
    private TextButton menuButton;

    public GameLostController(Stage stage, View view, App game) {
        this.game = game;
        this.stage = stage;
        this.view = view;

        startButton = createStartButton();
        menuButton = createMenuButton();

        Table table = createTableWithButtons(startButton, menuButton);
        stage.addActor(table);

        setInputProcessor();
        Gdx.input.setCatchBackKey(true);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Keys.BACK)) {
            System.out.println("Pressed back");
            game.setScreen(new GameMainMenuScreen(game));
        }
    }

    protected TextButton createStartButton() {
        FileHandle atlasFile = FileUtils.getFile("ui/ui-green.atlas");
        FileHandle fontFile = FileUtils.getFile("ui/BlackBigStyle.fnt");
        return createButton(atlasFile, fontFile, "button_02", "textbox_01", "Start over");
    }

    protected TextButton createMenuButton() {
        FileHandle atlasFile = FileUtils.getFile("ui/ui-green.atlas");
        FileHandle fontFile = FileUtils.getFile("ui/BigStyle.fnt");
        return createButton(atlasFile, fontFile, "button_01", "button_02", "Main Menu");
    }

    private void setInputProcessor() {
        Gdx.input.setInputProcessor(stage);

        startButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SFX.playButton2();
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
            }

        });

        menuButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SFX.playButton2();
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                game.setScreen(new GameMainMenuScreen(game));

            }

        });
    }

}
