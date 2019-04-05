package souless.game.model;

import com.badlogic.gdx.math.Vector2;
import souless.game.objects.*;

import java.util.ArrayList;

public class GameMap {
    private final ArrayList<Tile> WORLD_GRID;
    private final ArrayList<Entity> ENTITIES;
    private final Creature PLAYER;
    
    private final int WORLD_WIDTH;
    private final int WORLD_HEIGHT;
    
    public GameMap()
    {
        WORLD_WIDTH = 80;
        WORLD_HEIGHT = 40;
        WORLD_GRID = new ArrayList();
        for (int i=0; i<WORLD_HEIGHT; i++)
            for (int j=0; j<WORLD_WIDTH; j++)
                    WORLD_GRID.add((i==0) || (j==1) || (i==1) || (j==0) || (i==WORLD_HEIGHT-1) || (j==WORLD_WIDTH-1) 
                            ? (Tile) GameObjectFactory.getObject(GameObjectFactory.ObjectType.WALL, new Vector2(j,i), "")
                            : (Tile)GameObjectFactory.getObject(GameObjectFactory.ObjectType.FLOOR, new Vector2(j,i), ""));
        ENTITIES = new ArrayList<Entity>();
        PLAYER = (Creature)GameObjectFactory.getObject(GameObjectFactory.ObjectType.PLAYER, new Vector2(1,1), "");
    }
    
    public GameMap(ArrayList<Tile> grid, ArrayList<Entity> entities, int width, int height)
    {
        WORLD_GRID = grid;
        WORLD_WIDTH = width;
        WORLD_HEIGHT = height;
        ENTITIES = entities;
    //    ENTITIES.add((Entity)GameObjectFactory.getObject(GameObjectFactory.ObjectType.TORCH, new Vector2(2,6), ""));
        
    // TODO: обработать ошибку отсутствия игрока
        Creature player = null;
        for (GameObject entity : entities)
            if (entity.getType() == GameObjectFactory.ObjectType.PLAYER)
            {
                player = (Creature)entity;
                break;
            }
        PLAYER = player;
    }
    
    public Entity getPlayer() { return PLAYER; }
    
    public int getWidth()
    {
        return WORLD_WIDTH;
    }
    
    public int getHeight()
    {
        return WORLD_HEIGHT;
    }
    
    public ArrayList<Tile> getGrid()
    {
        return WORLD_GRID;
    }
    
    public ArrayList<Entity> getEntities()
    {
        return ENTITIES;
    }
    
    /**
     * Получить ячейку с соответствующими координатами
     * @param x координата x
     * @param y координата y
     * @return тайл, расположенный по этим координатам
     */
    public Tile getTileByCoordinates(float x, float y)
    {
//        int index = Math.round(x) + Math.round(y) * WORLD_WIDTH;
//        if ((index >= 0) && (index < WORLD_HEIGHT * WORLD_WIDTH))
//            return WORLD_GRID.get(index);
        for (Tile tile : WORLD_GRID)
            if ((tile.getPosition().x == x) && (tile.getPosition().y == y))
                return tile;
        return null;
    }
    
    /**
     * Получить ячейку с соответствующими координатами
     * @param pos вектор, описывающий координаты тайла
     * @return тайл, расположенный по этим координатам
     */
    public Tile getTileByCoordinates(Vector2 pos) { return getTileByCoordinates(pos.x, pos.y); }
    
    /**
     * Получает смежные с данной позицией клетки
     * @param x координата х
     * @param y координата y
     * @return список клеток слева напаво сверху вниз
     */
    public ArrayList<Tile> getAdjancedTiles(float x, float y)
    {
        ArrayList<Tile> result = new ArrayList<Tile>();
        
        result.add(getTileByCoordinates(x-1,y+1));
        result.add(getTileByCoordinates(x,y+1));
        result.add(getTileByCoordinates(x+1,y+1));
        result.add(getTileByCoordinates(x-1,y));
        result.add(getTileByCoordinates(x+1,y));
        result.add(getTileByCoordinates(x-1,y-1));
        result.add(getTileByCoordinates(x,y-1));
        result.add(getTileByCoordinates(x+1,y-1));
        
        return result;
    }
    
    /**
     * Получает смежные с данной позицией клетки
     * @param pos вектор координат
     * @return список клеток слева напаво сверху вниз
     */
    public ArrayList<Tile> getAdjancedTiles(Vector2 pos) { return getAdjancedTiles(pos.x, pos.y); }
}
