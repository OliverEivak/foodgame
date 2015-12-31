package ee.ttu.foodgame.screen;

import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMockSupport.injectMocks;

import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.scenes.scene2d.Stage;

import ee.ttu.foodgame.App;
import ee.ttu.foodgame.common.test.GdxTestRunner;
import ee.ttu.foodgame.controller.WorldController;
import ee.ttu.foodgame.view.WorldRenderer;

@RunWith(GdxTestRunner.class)
public class GameScreenTest {

    @Mock
    private App game;

    @Mock
    private Stage stage;

    @Mock
    private WorldRenderer renderer;

    @Mock
    private WorldController controller;

    @TestSubject
    private GameScreen gameScreen = new GameScreen(game);

    @Before
    public void setup() {
        injectMocks(this);
    }

    @Test
    public void render() {
        float delta = 17;

        controller.update(delta);
        renderer.render(delta);

        replay(game, stage, renderer, controller);

        gameScreen.render(delta);

        verify(game, stage, renderer, controller);
    }

}