package ee.ttu.foodgame.view;

import com.badlogic.gdx.graphics.OrthographicCamera;

public interface View {

    public void render(float delta);

    public void dispose();

    public OrthographicCamera getCamera();

}
