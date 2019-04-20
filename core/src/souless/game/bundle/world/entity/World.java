package souless.game.bundle.world.entity;

import souless.game.bundle.world.map.WorldObjectMap;
import souless.game.model.GameMap;
import souless.game.objects.Entity;
import souless.game.objects.Tile;

import java.util.ArrayList;

public class World {
    private WorldObjectMap tileMap;

    private GameMap Map;
    private String worldName;
    
    public World()
    {
        Map = new GameMap();
        worldName = "test_world";
    }

    public World(
            WorldObjectMap tileMap
    ) {
        this.tileMap = tileMap;
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

    public WorldObjectMap getTileMap() {
        return this.tileMap;
    }
    
    public GameMap getMap()      { return Map; }
    
    public Entity getPlayer()    { return Map.getPlayer(); }
    
    public String getWorldName() { return worldName; }
    
    public void setWorldName(String name)   { this.worldName = name; }
}
