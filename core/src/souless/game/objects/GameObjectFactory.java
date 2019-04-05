package souless.game.objects;

import com.badlogic.gdx.math.Vector2;
import souless.game.objects.moving.NoMoving;
import souless.game.objects.moving.SimpleMoving;
import souless.game.objects.onentering.FloorOnEntering;
import souless.game.objects.onentering.WallOnEntering;

public class GameObjectFactory {
    
    public enum ObjectType {
        FLOOR(1),
        WALL(0),
        PLAYER(30),
        TORCH(64);
        
        private final int id;
        ObjectType(int id)      { this.id = id; }
        public int getValue() { return id; }
        public static ObjectType getTypeByBalue(int id)
        {
            switch (id)
            {
                case 0:  return WALL;
                case 1:  return FLOOR;
                case 30: return PLAYER;
                case 64: return TORCH;
                default: return null;
            }
        }
    }
    
    /**
     * Фабрика возвращает объект нужного типа
     * @param type тип объекта
     * @param position позиция на карте
     * @param texId индекс используемой текстуры
     * @return 
     */
    public static GameObject getObject(ObjectType type, Vector2 position, String texId)
    {
        switch (type)
        {
            case WALL: {
                Tile object = new Tile(position, texId);
                object.onEnteringI = new WallOnEntering();
                object.type = type;
                return object;
            }
            case FLOOR: {
                Tile object = new Tile(position, texId);
                object.onEnteringI = new FloorOnEntering();
                object.type = type;
                return object;
            }
            case PLAYER: {
                Creature object = new Creature(position, texId);
                object.MOVING_PATTERN = new SimpleMoving();
                object.type = type;
                return object;
            }
            case TORCH: {
                Entity object = new Entity(position, texId);
                object.MOVING_PATTERN = new NoMoving();
                object.type = type;
                return object;
            }
            default: return null;
        }
    }
}
