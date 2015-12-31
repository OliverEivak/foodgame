package ee.ttu.foodgame.controller;

import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMockSupport.injectMocks;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import ee.ttu.foodgame.App;
import ee.ttu.foodgame.common.test.GdxTestRunner;
import ee.ttu.foodgame.view.View;

@RunWith(GdxTestRunner.class)
public class HighScoresControllerTest {

    @Mock
    private App game;

    @Mock
    private Stage stage = new Stage();

    @Mock
    private View view;

    @TestSubject
    private HighScoresController highScoresController = new HighScoresController(stage, view, game);

    @Before
    public void setup() {
        injectMocks(this);
    }

    @Test
    public void createStartButton() {
        replay(game, stage, view);

        TextButton button = highScoresController.createMenuButton();

        verify(game, stage, view);

        assertNotNull(button);
        assertEquals("Go back to menu", button.getText().toString());
    }

    @Test
    public void setInputProcessor() {
        assertNotNull(Gdx.input.getInputProcessor());
    }

}
