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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import ee.ttu.foodgame.App;
import ee.ttu.foodgame.controller.Save;
import ee.ttu.foodgame.utils.FileUtils;

public class InstructionRenderer implements View {

    private Stage stage;
   
    private LabelStyle textStyle;
    private BitmapFont font;
    private OrthographicCamera camera;
    private App game;
    private Image instructions;
    private Texture instructionsText;
    private Sprite instructSprite;
    public InstructionRenderer(Stage stage, App game) {
        this.stage = stage;
        this.game = game;
        

        font = new BitmapFont(FileUtils.getFile("endgameFont.fnt"));

        textStyle = new LabelStyle(font, Color.BLACK);
        
        instructionsText = new Texture(FileUtils.getFile("Instructions2.jpg"));
        instructSprite = new Sprite(instructionsText);
        instructSprite.setSize(GAME_WIDTH, GAME_HEIGHT);

        instructions = new Image(instructSprite);
        instructions.setPosition(0, 0);
        instructions.setWidth(GAME_WIDTH);
        instructions.setHeight(GAME_HEIGHT);
        instructions.setVisible(true);
        stage.addActor(instructions);

        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1)));
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
