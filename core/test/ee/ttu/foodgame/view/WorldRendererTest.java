package ee.ttu.foodgame.view;

import static ee.ttu.foodgame.App.GAME_HEIGHT;
import static ee.ttu.foodgame.App.GAME_WIDTH;
import static ee.ttu.foodgame.App.UNIT;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMockSupport.injectMocks;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

import ee.ttu.foodgame.App;
import ee.ttu.foodgame.common.test.GdxTestRunner;
import ee.ttu.foodgame.model.Food;
import ee.ttu.foodgame.model.World;

@RunWith(GdxTestRunner.class)
public class WorldRendererTest {

    @Mock
    private App game;

    @Mock
    private World world;

    @Mock
    private Stage stage;

    @TestSubject
    private WorldRenderer worldRenderer = new WorldRenderer(stage, world, game);

    @Mock
    private OrthographicCamera camera;

    @Mock
    private SpriteBatch spriteBatch;

    @Mock
    private ShapeRenderer shapeRenderer;

    @Mock
    private BitmapFont font;

    @Mock
    private Texture goodJobTex;

    @Mock
    private Array<Sprite> sprites;

    @Mock(fieldName = "correctCountLabel")
    private Label correctCountLabel;

    @Mock(fieldName = "scoreLabel")
    private Label scoreLabel;

    @Mock
    private Image goodJobImage;

    @Before
    public void setup() {
        injectMocks(this);
        // Skip initialization and use the mocks instead
        worldRenderer.setInit(true);
    }

    /**
     * Add a food to the world and test rendering.
     */
    @Test
    public void render() {
        camera.update();

        expect(goodJobImage.isVisible()).andReturn(false);
        spriteBatch.setProjectionMatrix(anyObject(Matrix4.class));

        // Label drawing
        expect(world.getCorrectCount()).andReturn(0);
        correctCountLabel.setText("correct = 0");
        expect(world.getScore()).andReturn(0);
        scoreLabel.setText("score = 0");
        stage.draw();

        spriteBatch.begin();

        // Food drawing
        Food food = new Food();
        food.setId(11);
        food.setPosition(new Vector2(111, 222));

        expect(world.isGameOver()).andReturn(false);
        expect(world.getFoods()).andReturn(Arrays.asList(food));

        Sprite spriteMock = createMock(Sprite.class);
        expect(sprites.get(food.getId())).andReturn(spriteMock);

        spriteBatch.draw(spriteMock, food.getPosition().x, food.getPosition().y, food.getBounds().width,
                food.getBounds().height);

        spriteBatch.end();

        replayAll();

        worldRenderer.render(1);

        verifyAll();
    }

    @Test
    public void init() {
        stage.addActor(EasyMock.anyObject(Label.class));
        stage.addActor(EasyMock.anyObject(Label.class));

        replayAll();

        worldRenderer.init();

        verifyAll();

        OrthographicCamera camera = worldRenderer.getCamera();
        assertEquals(GAME_WIDTH, camera.viewportWidth, 0.1);
        assertEquals(GAME_HEIGHT, camera.viewportHeight, 0.1);
    }

    @Test
    public void dispose() {
        spriteBatch.dispose();
        font.dispose();
        goodJobTex.dispose();

        replayAll();

        worldRenderer.dispose();

        verifyAll();
    }

    @Test
    public void highlightNotPickedFoods() {
        ShapeType shapeType = ShapeType.Line;
        Color color = EasyMock.createMock(Color.class);

        int x = 111;
        int y = 222;

        Food food = new Food();
        food.setId(11);
        food.setPosition(new Vector2(x, y));
        food.setVisible(true);
        food.setPicked(false);

        shapeRenderer.setProjectionMatrix(EasyMock.anyObject(Matrix4.class));
        shapeRenderer.begin(shapeType);
        expect(world.getFoods()).andReturn(Arrays.asList(food));
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x + food.getBounds().width / 2, y + food.getBounds().height / 2,
                food.getBounds().width / 2 + UNIT);
        shapeRenderer.end();

        replayAll();

        worldRenderer.highlightNotPickedFoods(shapeType, color);

        verifyAll();
    }

    private void replayAll(Object... mocks) {
        replay(world, camera, spriteBatch, shapeRenderer, font, goodJobTex, sprites, goodJobImage);

        if (mocks != null) {
            for (Object object : mocks) {
                replay(object);
            }
        }
    }

    private void verifyAll(Object... mocks) {
        verify(world, camera, spriteBatch, shapeRenderer, font, goodJobTex, sprites, goodJobImage);

        if (mocks != null) {
            for (Object object : mocks) {
                verify(object);
            }
        }
    }

}
