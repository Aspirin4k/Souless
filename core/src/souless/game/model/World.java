package souless.game.model;

import souless.game.objects.Entity;
import souless.game.objects.Tile;

import java.util.ArrayList;

public class World {
    private GameMap Map;
    private String worldName;
    
    public World()
    {
        Map = new GameMap();
        worldName = "test_world";
    }
    
    public World(ArrayList<Tile> gameMap, ArrayList<Entity> entities, int width, int height)
    {
        Map = new GameMap(gameMap, entities, width, height);
    }
    
    public World(String name, ArrayList<Tile> gameMap, ArrayList<Entity> entities, int width, int height)
    {
        Map = new GameMap(gameMap, entities, width, height);
        worldName = name;
    }
    
    public GameMap getMap()      { return Map; }
    
    public Entity getPlayer()    { return Map.getPlayer(); }
    
    public String getWorldName() { return worldName; }
    
    public void setWorldName(String name)   { this.worldName = name; }
}
