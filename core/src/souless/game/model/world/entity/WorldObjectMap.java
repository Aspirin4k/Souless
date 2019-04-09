package souless.game.model.world.entity;

import souless.game.common.Vector2;
import souless.game.model.world.exception.OutOfMapException;
import souless.game.model.world.object.AbstractWorldObject;
import souless.game.view.world.entity.IViewWorldMap;
import souless.game.view.world.object.IViewWorldObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class WorldObjectMap<T extends AbstractWorldObject> implements IViewWorldMap {
    private HashMap<Integer, ArrayList<T>> objectMap;
    private int width;
    private int height;

    public WorldObjectMap(int width, int height) {
        this.objectMap = new HashMap<Integer, ArrayList<T>>();
        this.width = width;
        this.height = height;
    }

    /**
     * Возвращает список объектов, находящихся на заданных координатах
     *
     * @param coordinates координаты на игровой карте
     *
     * @return список объектов
     */
    public List<T> getObjectsByCoordinates(Vector2 coordinates) {
        int objectIndex = this.width * (coordinates.getX() - 1) + (coordinates.getY() - 1);
        return this.objectMap.get(objectIndex);
    }

    public List<IViewWorldObject> getViewsByCoordinates(Vector2 coordinates) {
        ArrayList<IViewWorldObject> result = new ArrayList<IViewWorldObject>();
        for (Iterator<T> mapObjectIterator = this.getObjectsByCoordinates(coordinates).iterator(); mapObjectIterator.hasNext();) {
            result.add(mapObjectIterator.next().getView());
        }
        return result;
    }

    public void putObject(Vector2 coordinates, T object) throws OutOfMapException {
        if (!this.isInInterval(coordinates.getX(), this.width) || !this.isInInterval(coordinates.getY(), this.height)) {
            throw new OutOfMapException(
                    String.format("Trying to set coordinates to %d, %d; but size is %d, %d", coordinates.getX(), coordinates.getY(), this.width, this.height)
            );
        }

        int hashIndex = this.width * (coordinates.getY() - 1) + (coordinates.getY() - 1);
        if (!this.objectMap.containsKey(hashIndex)) {
            this.objectMap.put(hashIndex, new ArrayList<T>());
        }

        this.objectMap.get(hashIndex).add(object);
    }

    private boolean isInInterval(int value, int maxValue) {
        return value >= 1 && value <= maxValue;
    }
}
