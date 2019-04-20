package souless.game.common;

/**
 * Вариант Vector2 с целочисленными координатами
 */
public class Vector2 {
    private int x;
    private int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public float getFloatedX() {
        return (float)this.getX();
    }

    public float getFloatedY() {
        return (float)this.getY();
    }

    public int getY() {
        return this.y;
    }

    public Vector2 clone() {
        return new Vector2(this.getX(), this.getY());
    }
}
