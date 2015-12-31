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
import ee.ttu.foodgame.utils.FileUtils;
import ee.ttu.foodgame.utils.SFX;
import ee.ttu.foodgame.view.View;

public class InstructionController extends Controller {

    private App game;

    private Stage stage;

    private View view;

    private TextButton menuButton;

    public InstructionController(Stage stage, View view, App game) {
        this.game = game;
        this.stage = stage;
        this.view = view;

        Save.load();

        menuButton = createMenuButton();

        Table table = createTableWithButtons(menuButton);
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

    protected TextButton createMenuButton() {
        FileHandle atlasFile = FileUtils.getFile("ui/ui-green.atlas");
        FileHandle fontFile = FileUtils.getFile("ui/BigStyle.fnt");
        return createButton(atlasFile, fontFile, "button_01", "button_02", "Go back to menu");
    }

    private void setInputProcessor() {
        Gdx.input.setInputProcessor(stage);

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
