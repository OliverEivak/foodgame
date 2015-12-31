package ee.ttu.foodgame.view;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMockSupport.injectMocks;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;

import ee.ttu.foodgame.common.test.GdxTestRunner;
import ee.ttu.foodgame.controller.Save;

@RunWith(GdxTestRunner.class)
public class GameWonRendererTest {

    @Mock
    private Stage stage;

    private GameWonRenderer gameWonRenderer;

    @Before
    public void setup() {
        injectMocks(this);

        // Save the score
        Save.init();
        Save.s.setScore(13);
        Save.save();

        // Expects for constructor
        stage.addActor(EasyMock.anyObject(Label.class));
        stage.addActor(EasyMock.anyObject(Label.class));
        stage.addActor(EasyMock.anyObject(Label.class));
        stage.addActor(EasyMock.anyObject(Label.class));

        replay(stage);

        // TestSubject
        gameWonRenderer = new GameWonRenderer(stage, 13);

        verify(stage);
        reset(stage);
    }

    @Test
    public void resize() {
        int width = 100;
        int height = 200;

        Viewport viewportMock = EasyMock.createMock(Viewport.class);
        expect(stage.getViewport()).andReturn(viewportMock);
        viewportMock.update(width, height, true);

        replay(stage);

        gameWonRenderer.resize(width, height);

        verify(stage);
    }

    @Test
    public void render() {
        int delta = 17;

        stage.act(delta);
        stage.draw();

        replay(stage);

        gameWonRenderer.render(delta);

        verify(stage);
    }

}
