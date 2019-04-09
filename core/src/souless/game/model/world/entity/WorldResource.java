package souless.game.model.world.entity;

import souless.game.model.GameMap;
import souless.game.objects.Entity;
import souless.game.objects.Tile;

import java.util.ArrayList;

public class WorldResource {
    private final int DEFAULT_WIDTH = 40;
    private final int DEFAULT_HEIGHT = 40;

    private WorldObjectMap<souless.game.model.world.object.Tile> tileMap;

    private GameMap Map;
    private String worldName;
    
    public WorldResource()
    {
        Map = new GameMap();
        worldName = "test_world";

        this.tileMap = new WorldObjectMap<souless.game.model.world.object.Tile>(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
    }
    
    public WorldResource(ArrayList<Tile> gameMap, ArrayList<Entity> entities, int width, int height)
    {
        Map = new GameMap(gameMap, entities, width, height);
    }
    
    public WorldResource(String name, ArrayList<Tile> gameMap, ArrayList<Entity> entities, int width, int height)
    {
        Map = new GameMap(gameMap, entities, width, height);
        worldName = name;
    }
    
    public GameMap getMap()      { return Map; }
    
    public Entity getPlayer()    { return Map.getPlayer(); }
    
    public String getWorldName() { return worldName; }
    
    public void setWorldName(String name)   { this.worldName = name; }
}
