package souless.game.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import souless.game.objects.onentering.TileOnEntering;

public abstract class GameObject {
    // Позиция на экране
    private final Rectangle POSITION;
    // Размеры объекта
    private final float SIZE = 1f;
    // Название текстуры
    private final String textureId;
    
    protected GameObjectFactory.ObjectType type;
    
    protected TileOnEntering onEnteringI;
    
    public GameObject(Vector2 pos)
    {
        POSITION = new Rectangle(pos.x, pos.y, SIZE, SIZE);
        this.textureId = "";
    }
    
    public GameObject(float x, float y)
    {
        POSITION = new Rectangle(x,y, SIZE, SIZE);
        this.textureId = "";
    }
    
    public GameObject(Vector2 pos, String tId)
    {
        POSITION = new Rectangle(pos.x, pos.y, SIZE, SIZE);
        this.textureId = tId;
    }
    
    public GameObject(float x, float y, String tId)
    {
        POSITION = new Rectangle(x,y, SIZE, SIZE);
        this.textureId = tId;
    }
    
    /**
     * Получет координаты объекта
     * @return вектор координат
     */
    public Vector2 getPosition() { return new Vector2(POSITION.x, POSITION.y); }
    
    /**
     * Получает прямоугольник, описывающий объект
     * @return прямоугольник
     */
    public Rectangle getRect() { return POSITION; }
    
    /**
     * Получает имя текстуры, представляющего данный объект
     * @return имя текстуры
     */
    public String getTextureId() { return this.textureId; }
    
    /**
     * Получает тип объекта
     * @return 
     */
    public GameObjectFactory.ObjectType getType() { return this.type; }
    
    /**
     * Изменить координаты объекта
     * @param dx смещение по x
     * @param dy смещение по y
     */
    public void changePosition(float dx, float dy)
    {
        this.POSITION.x += dx;
        this.POSITION.y += dy;
    }
    
    /**
    * Объект пытается вступить на ячейку
    * @param obj
    * @return 
    */
    public boolean tryEnter(GameObject obj) { return onEnteringI.tryEnter(obj, this); }
}
