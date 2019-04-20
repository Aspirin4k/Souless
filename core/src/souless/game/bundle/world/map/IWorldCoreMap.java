package souless.game.bundle.world.map;

import souless.game.common.Vector2;
import souless.game.bundle.world.object.core.VoidWorldObjectCore;

import java.util.List;

public interface IWorldCoreMap {
    /**
     * Возвращает максимальную ширину карты в объектах
     *
     * @return ширина карты
     */
    public int getWidth();

    /**
     * Возвращает максимальную высоту карты в объектах
     *
     * @return высота карты
     */
    public int getHeight();

    /**
     * Возвращает список сущностей поведения объектов на заданных координатах
     *
     * @param coordinates - координаты точки на игровой карте
     *
     * @return - список отображений объектов
     */
    public List<VoidWorldObjectCore> getCoreByCoordinates(Vector2 coordinates);
}
