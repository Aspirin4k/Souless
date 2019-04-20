package souless.game.bundle.world.map;

import souless.game.bundle.world.object.WorldObject;
import souless.game.common.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Сущность объекта мира, привязанного к определенным координатам
 */
public class WorldObjectMapEntry {
    private final ArrayList<WorldObject> worldObjectList;
    private final Vector2 coordinates;

    public WorldObjectMapEntry(Vector2 coordinates) {
        this.worldObjectList = new ArrayList<WorldObject>();
        this.coordinates = coordinates;
    }

    public List<WorldObject> getWorldObjectList() {
        return this.worldObjectList;
    }

    public void putObject(WorldObject worldObject) {
        this.worldObjectList.add(worldObject);
    }

    public int getX() {
        return this.coordinates.getX();
    }

    public int getY() {
        return this.coordinates.getY();
    }
}
