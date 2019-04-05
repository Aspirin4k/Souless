package souless.game.objects;

import com.badlogic.gdx.math.Vector2;
import souless.game.model.GameMap;
import souless.game.objects.moving.EntityMoving;

public class Entity extends GameObject {
    
    protected EntityMoving MOVING_PATTERN;
    
    public Entity(Vector2 pos) 
    {
        super(pos);
    }
    
    public Entity(float x, float y)
    {
        super(x,y);
    }
    
    public Entity(Vector2 pos, String tName)
    {
        super(pos, tName);
    }
    
    public Entity(float x, float y, String tName)
    {
        super(x,y, tName);
    }
    
    /**
     * Переместить текущий объект
     * @param d
     * @param map 
     */
    public void move(EntityMoving.Direction d, GameMap map)
    {
        MOVING_PATTERN.move(this, d, map);
    }
}
