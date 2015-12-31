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
import ee.ttu.foodgame.controller.GameMainMenuController;
import ee.ttu.foodgame.view.GameMainMenuRenderer;

@RunWith(GdxTestRunner.class)
public class GameMainMenuScreenTest {

    @Mock
    private App game;

    @Mock
    private Stage stage;

    @Mock
    private GameMainMenuRenderer renderer;

    @Mock
    private GameMainMenuController controller;

    @TestSubject
    private GameMainMenuScreen gameMainMenuScreen = new GameMainMenuScreen(game);

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

        gameMainMenuScreen.render(delta);

        verify(game, stage, renderer, controller);
    }

    @Test
    public void resize() {
        int width = 11;
        int height = 51;

        renderer.resize(width, height);

        replay(game, stage, renderer, controller);

        gameMainMenuScreen.resize(width, height);

        verify(game, stage, renderer, controller);
    }

    @Test
    public void dispose() {
        renderer.dispose();
        stage.dispose();

        replay(game, stage, renderer, controller);

        gameMainMenuScreen.dispose();

        verify(game, stage, renderer, controller);
    }

}