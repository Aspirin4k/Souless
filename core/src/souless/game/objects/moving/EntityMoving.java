package souless.game.objects.moving;

import souless.game.model.GameMap;
import souless.game.objects.GameObject;

public interface EntityMoving {
    
    public enum Direction {
        LEFT_UP,
        UP,
        RIGHT_UP,
        LEFT,
        RIGHT,
        LEFT_DOWN,
        DOWN,
        RIGHT_DOWN
    };
    
    /**
     * Перемещение объекта в пространстве
     * @param d направление перемещения
     * @param map карта
     * @param obj перемещаемый объект
     * @return успешность передвижения
     */
    public boolean move(GameObject obj, Direction d, GameMap map);
}
