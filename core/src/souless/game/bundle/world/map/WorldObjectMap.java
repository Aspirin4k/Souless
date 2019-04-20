package souless.game.bundle.world.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import souless.game.bundle.world.exception.OutOfMapException;
import souless.game.bundle.world.object.WorldObject;
import souless.game.bundle.world.object.core.VoidWorldObjectCore;
import souless.game.common.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorldObjectMap implements IWorldViewMap, IWorldCoreMap {
    private final WorldObjectMapEntryFactory worldObjectMapEntryFactory;

    private final HashMap<Integer, WorldObjectMapEntry> objectMap;
    private final int width;
    private final int height;

    public WorldObjectMap(
            WorldObjectMapEntryFactory worldObjectMapEntryFactory,
            int width,
            int height
    ) {
        this.worldObjectMapEntryFactory = worldObjectMapEntryFactory;

        this.objectMap = new HashMap<Integer, WorldObjectMapEntry>();
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    /**
     * Возвращает список объектов, находящихся на заданных координатах
     *
     * @param coordinates координаты на игровой карте
     *
     * @return список объектов
     */
    public List<WorldObject> getObjectsByCoordinates(Vector2 coordinates) {
        int objectIndex = this.width * (coordinates.getX() - 1) + (coordinates.getY() - 1);
        return this.objectMap.get(objectIndex).getWorldObjectList();
    }

    @Override
    public List<TextureRegion> getTextureByCoordinates(Vector2 coordinates) {
        ArrayList<TextureRegion> result = new ArrayList<TextureRegion>();
        for (WorldObject worldObject : this.getObjectsByCoordinates(coordinates)) {
            result.add(worldObject.getTexture());
        }
        return result;
    }

    @Override
    public List<VoidWorldObjectCore> getCoreByCoordinates(Vector2 coordinates) {
        ArrayList<VoidWorldObjectCore> result = new ArrayList<VoidWorldObjectCore>();
        for (WorldObject worldObject : this.getObjectsByCoordinates(coordinates)) {
            result.add(worldObject.getCore());
        }
        return result;
    }

    public void putObject(Vector2 coordinates, WorldObject object) throws OutOfMapException {
        if (!this.isInInterval(coordinates.getX(), this.width) || !this.isInInterval(coordinates.getY(), this.height)) {
            throw new OutOfMapException(
                    String.format("Trying to set coordinates to %d, %d; but size is %d, %d", coordinates.getX(), coordinates.getY(), this.width, this.height)
            );
        }

        int hashIndex = this.width * (coordinates.getX() - 1) + (coordinates.getY() - 1);
        if (!this.objectMap.containsKey(hashIndex)) {
            this.objectMap.put(hashIndex,this.worldObjectMapEntryFactory.getWorldObjectMapEntry(coordinates.clone()));
        }

        this.objectMap.get(hashIndex).putObject(object);
    }

    private boolean isInInterval(int value, int maxValue) {
        return value >= 1 && value <= maxValue;
    }
}
