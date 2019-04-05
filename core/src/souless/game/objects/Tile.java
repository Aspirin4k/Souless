package souless.game.objects;

import com.badlogic.gdx.math.Vector2;

public class Tile extends GameObject {
    
    public Tile(Vector2 pos)
    {
        super(pos);
    }
    
    public Tile(float x, float y)
    {
        super(x,y);
    }
    
    public Tile(Vector2 pos, String tName)
    {
        super(pos, tName);
    }
    
    public Tile(float x, float y, String tName)
    {
        super(x,y, tName);
    }
     
}
