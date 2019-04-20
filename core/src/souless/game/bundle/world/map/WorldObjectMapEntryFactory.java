package souless.game.bundle.world.map;

import souless.game.common.Vector2;

public class WorldObjectMapEntryFactory {
    public WorldObjectMapEntry getWorldObjectMapEntry(Vector2 coordinates) {
        return new WorldObjectMapEntry(coordinates);
    }
}
