package souless.game.view.world.entity;

import souless.game.common.Vector2;
import souless.game.view.world.object.IViewWorldObject;

import java.util.List;

public interface IViewWorldMap {
    /**
     * Возвращает список представлений всех объектов по заданным координатам
     *
     * @param coordinates - координаты точки на игровой карте
     *
     * @return - список отображений объектов
     */
    public List<IViewWorldObject> getViewsByCoordinates(Vector2 coordinates);
}
