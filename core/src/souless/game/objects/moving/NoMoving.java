package souless.game.objects.moving;

import souless.game.model.GameMap;
import souless.game.objects.GameObject;

public class NoMoving implements EntityMoving {

    @Override
    public boolean move(GameObject obj, Direction d, GameMap map) {
        return false;
    }
    
}
