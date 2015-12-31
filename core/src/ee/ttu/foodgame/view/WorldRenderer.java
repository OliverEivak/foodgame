package ee.ttu.foodgame.view;

import static ee.ttu.foodgame.App.GAME_HEIGHT;
import static ee.ttu.foodgame.App.GAME_WIDTH;
import static ee.ttu.foodgame.App.UNIT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import ee.ttu.foodgame.App;
import ee.ttu.foodgame.model.Food;
import ee.ttu.foodgame.model.World;
import ee.ttu.foodgame.utils.FileUtils;

public class WorldRenderer implements View {

    private World world;
    private App game;
    private OrthographicCamera camera;

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;

    private Array<Sprite> sprites;
    private Texture goodJobTex;
    private Sprite goodJob;
    private Label correctCountLabel;
    private Label scoreLabel;
    private LabelStyle textStyle;
    private BitmapFont font;
    private Stage stage;
    private Image goodJobImage;

    private boolean init = false;

    public WorldRenderer(Stage stage, World world, App game) {
        this.stage = stage;
        this.world = world;
        this.game = game;
    }

    protected void init() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        sprites = getFoodSprites();

        font = new BitmapFont();
        textStyle = new LabelStyle(font, Color.BLACK);

        correctCountLabel = new Label("correct = 0", textStyle);
        scoreLabel = new Label("score = 0", textStyle);

        correctCountLabel.setPosition(GAME_WIDTH * 0.6f, GAME_HEIGHT * 0.95f);
        scoreLabel.setPosition(GAME_WIDTH * 0.8f, GAME_HEIGHT * 0.95f);

        goodJobTex = new Texture(FileUtils.getFile("goodJob2.jpg"));
        goodJob = new Sprite(goodJobTex);
        goodJob.setSize(GAME_WIDTH, GAME_HEIGHT);

        goodJobImage = new Image(goodJob);
        goodJobImage.setPosition(0, 0);
        goodJobImage.setWidth(GAME_WIDTH);
        goodJobImage.setHeight(GAME_HEIGHT);
        goodJobImage.setVisible(false);

        stage.addActor(scoreLabel);
        stage.addActor(correctCountLabel);
        stage.addActor(goodJobImage);

        init = true;
    }

    public void render(float delta) {
        if (init == false) {
            init();
        }

        camera.update();

        clearScreen();
        updateLabelText();

        if (world.isGameOver()) {
            drawGameLostHighlights();
        }

        if (App.CHEAT_MODE) {
            drawCheatModeHighlights();
        }

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        drawFoods(spriteBatch);
        spriteBatch.end();

        stage.draw();
    }

    private void clearScreen() {
        if (goodJobImage.isVisible()) {
            Gdx.gl.glClearColor(123f / 255f, 239 / 255f, 90f / 255f, 1f);
        } else {
            Gdx.gl.glClearColor(118f / 255f, 167 / 255f, 184f / 255f, 1f);
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void updateLabelText() {
        correctCountLabel.setText("correct = " + world.getCorrectCount());
        scoreLabel.setText("score = " + world.getScore());
    }

    private void drawFoods(SpriteBatch batch) {
        for (Food food : world.getFoods()) {
            if (food.isVisible()) {
                Vector2 position = food.getPosition();
                Rectangle bounds = food.getBounds();

                batch.draw(sprites.get(food.getId()), position.x, position.y, bounds.width, bounds.height);
            }
        }
    }

    private void drawCheatModeHighlights() {
        highlightNotPickedFoods(ShapeType.Line, new Color(236 / 255f, 240 / 255f, 241 / 255f, 1));
    }

    private void drawGameLostHighlights() {
        highlightNotPickedFoods(ShapeType.Filled, new Color(46 / 255f, 204 / 255f, 113 / 255f, 1));
        highlightNotPickedFoods(ShapeType.Line, new Color(189 / 255f, 195 / 255f, 199 / 255f, 1));
    }

    protected void highlightNotPickedFoods(ShapeType type, Color color) {
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(type);

        for (Food food : world.getFoods()) {
            if (food.isVisible()) {
                Vector2 position = food.getPosition();
                Rectangle bounds = food.getBounds();

                if (!food.isPicked()) {
                    shapeRenderer.setColor(color);
                    shapeRenderer.circle(position.x + bounds.width / 2, position.y + bounds.height / 2,
                            bounds.width / 2 + UNIT);
                }
            }
        }

        shapeRenderer.end();
    }

    private Array<Sprite> getFoodSprites() {
        TextureAtlas atlas = new TextureAtlas(FileUtils.getFile("foods.txt"));
        return atlas.createSprites();
    }

    public void dispose() {
        spriteBatch.dispose();
        font.dispose();
        goodJobTex.dispose();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void showGoodJobMessage() {
        goodJobImage.setVisible(true);

        Timer.schedule(new Task() {
            @Override
            public void run() {
                goodJobImage.setVisible(false);
            }
        }, 2);
    }

    /**
     * For testing purposes.
     */
    protected void setInit(boolean init) {
        this.init = init;
    }

}
