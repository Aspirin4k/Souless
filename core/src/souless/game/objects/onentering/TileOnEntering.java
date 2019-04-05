package souless.game.objects.onentering;

import souless.game.objects.GameObject;

public interface TileOnEntering {
    /**
     * Попытка сущности вступить на ячейку карты
     * @param obj сущность
     * @param tile ячейка
     * @return успешность попытки
     */
    public boolean tryEnter(GameObject obj, GameObject tile);
}
