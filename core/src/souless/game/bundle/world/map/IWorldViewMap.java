package souless.game.bundle.world.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import souless.game.common.Vector2;

import java.util.List;

public interface IWorldViewMap {
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
     * Возвращает список представлений всех объектов по заданным координатам
     *
     * @param coordinates - координаты точки на игровой карте
     *
     * @return - список отображений объектов
     */
    public List<TextureRegion> getTextureByCoordinates(Vector2 coordinates);
}
