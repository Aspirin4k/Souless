package souless.game.objects;

import com.badlogic.gdx.math.Vector2;

public class Creature extends Entity {
    
    public Creature(Vector2 pos) {
        super(pos);
    }
    
    public Creature(float x, float y)
    {
        super(x,y);
    }
    
    public Creature(Vector2 pos, String tName)
    {
        super(pos, tName);
    }
    
    public Creature(float x, float y, String tName)
    {
        super(x,y, tName);
    }
}
