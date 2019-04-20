package souless.game.bundle.world.map;

import souless.game.bundle.world.exception.OutOfMapException;
import souless.game.bundle.world.object.WorldObjectFactory;
import souless.game.common.Vector2;

public class WorldObjectMapFactory {
    private final WorldObjectFactory worldObjectFactory;
    private final WorldObjectMapEntryFactory worldObjectMapEntryFactory;

    public WorldObjectMapFactory(
            WorldObjectFactory worldObjectFactory,
            WorldObjectMapEntryFactory worldObjectMapEntryFactory
    ) {
        this.worldObjectFactory = worldObjectFactory;
        this.worldObjectMapEntryFactory = worldObjectMapEntryFactory;
    }

    public WorldObjectMap getEmptyTileMap(int width, int height) {
        return new WorldObjectMap(
                this.worldObjectMapEntryFactory,
                width,
                height
        );
    }

    public WorldObjectMap getTestTileMap(int width, int height) throws OutOfMapException {
        WorldObjectMap tileMap = new WorldObjectMap(
                this.worldObjectMapEntryFactory,
                width,
                height
        );
        for (int x = 1; x <= width; x++) {
            for (int y = 1; y <= height; y++) {
                tileMap.putObject(
                        new Vector2(x, y),
                        this.worldObjectFactory.getTestTile()
                );
            }
        }
        return tileMap;
    }
}
