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
import ee.ttu.foodgame.controller.HighScoresController;
import ee.ttu.foodgame.view.HighScoresRenderer;

@RunWith(GdxTestRunner.class)
public class HighScoresScreenTest {

    @Mock
    private App game;

    @Mock
    private Stage stage;

    @Mock
    private HighScoresRenderer renderer;

    @Mock
    private HighScoresController controller;

    @TestSubject
    private HighScoresScreen highScoresScreen = new HighScoresScreen(game);

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

        highScoresScreen.render(delta);

        verify(game, stage, renderer, controller);
    }

    @Test
    public void resize() {
        int width = 11;
        int height = 51;

        renderer.resize(width, height);

        replay(game, stage, renderer, controller);

        highScoresScreen.resize(width, height);

        verify(game, stage, renderer, controller);
    }

    @Test
    public void dispose() {
        renderer.dispose();
        stage.dispose();

        replay(game, stage, renderer, controller);

        highScoresScreen.dispose();

        verify(game, stage, renderer, controller);
    }

}