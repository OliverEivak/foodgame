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
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;

import ee.ttu.foodgame.common.test.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class GameMainMenuRendererTest {

    @Mock
    private Stage stage;

    private GameMainMenuRenderer gameMainMenuRenderer;

    @Before
    public void setup() {
        injectMocks(this);

        // Expects for constructor
        stage.addActor(EasyMock.anyObject(Label.class));
        stage.addAction(EasyMock.anyObject(SequenceAction.class));

        replay(stage);

        // TestSubject
        gameMainMenuRenderer = new GameMainMenuRenderer(stage);

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

        gameMainMenuRenderer.resize(width, height);

        verify(stage);
    }

    @Test
    public void render() {
        int delta = 17;

        stage.act(delta);
        stage.draw();

        replay(stage);

        gameMainMenuRenderer.render(delta);

        verify(stage);
    }

}
