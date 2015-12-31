package ee.ttu.foodgame.model;

import static ee.ttu.foodgame.App.UNIT;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Food {

    public static final int SIZE = 10 * UNIT;

    private int id;

    private Vector2 position;

    private Rectangle bounds;

    private boolean picked = false;

    private boolean visible = true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        this.bounds = new Rectangle(position.x, position.y, SIZE, SIZE);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
