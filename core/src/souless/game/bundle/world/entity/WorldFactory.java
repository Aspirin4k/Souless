package souless.game.bundle.world.entity;

import souless.game.bundle.world.exception.OutOfMapException;
import souless.game.bundle.world.map.WorldObjectMap;
import souless.game.bundle.world.map.WorldObjectMapFactory;

public class WorldFactory {
    private final int TEST_WORLD_HEIGHT = 100;
    private final int TEST_WORLD_WIDTH = 100;

    private final WorldObjectMapFactory worldObjectMapFactory;

    public WorldFactory(WorldObjectMapFactory worldObjectMapFactory) {
        this.worldObjectMapFactory = worldObjectMapFactory;
    }

    public World getWorld(WorldObjectMap tileMap) {
        return new World(tileMap);
    }

    public World getTestWorld() {
        WorldObjectMap tileMap;
        try {
            tileMap = this.worldObjectMapFactory.getTestTileMap(this.TEST_WORLD_WIDTH, this.TEST_WORLD_HEIGHT);
        } catch (OutOfMapException e) {
            tileMap = this.worldObjectMapFactory.getEmptyTileMap(this.TEST_WORLD_WIDTH, this.TEST_WORLD_HEIGHT);
        }
        return new World(tileMap);
    }
}
