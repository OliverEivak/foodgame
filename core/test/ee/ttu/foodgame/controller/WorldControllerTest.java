package ee.ttu.foodgame.controller;

import static ee.ttu.foodgame.App.FOODS;
import static org.easymock.EasyMockSupport.injectMocks;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ee.ttu.foodgame.App;
import ee.ttu.foodgame.common.test.GdxTestRunner;
import ee.ttu.foodgame.model.Food;
import ee.ttu.foodgame.model.World;
import ee.ttu.foodgame.utils.SFX;
import ee.ttu.foodgame.view.WorldRenderer;

@RunWith(GdxTestRunner.class)
public class WorldControllerTest {

    private World world = new World();

    @Mock
    private App game;

    @Mock
    private WorldRenderer view;

    @TestSubject
    private WorldController worldController = new WorldController(world, view, game);

    @Before
    public void setup() {
        injectMocks(this);
        SFX.initialize();
    }

    /**
     * Test that there is one food visible and the rest of the foods are unused.
     */
    @Test
    public void verifyWorld() {
        assertEquals(1, world.getFoods().size());
        assertEquals(FOODS - 1, world.getUnusedFoods().size());
    }

    /**
     * Test that the score is 0 at the beginning
     */
    @Test
    public void testFirstScore() {
        assertEquals(0, world.getScore());
    }

    @Test
    public void testInitialCorrectCount() {
        assertEquals(0, world.getCorrectCount());
    }

    /*
     * Test clicking on food
     */
    @Test
    public void processTouchDown() {
        Food food = world.getFoods().get(0);

        view.showGoodJobMessage();

        EasyMock.replay(view, game);

        worldController.processTouchDown((int) food.getPosition().x, (int) food.getPosition().y);

        EasyMock.verify(view, game);

        assertTrue(food.isPicked());
        assertEquals(1, world.getCorrectCount());
        assertTrue(world.getScore() >= 10);
        assertTrue(world.getFoods().size() >= 2);
    }

    @Test
    public void gameLost() throws InterruptedException {
        EasyMock.replay(view, game);

        worldController.gameLost();

        EasyMock.verify(view, game);

        assertTrue(world.isGameOver());
    }

}
