package souless.game.objects.onentering;

import souless.game.objects.GameObject;

public class WallOnEntering implements TileOnEntering {

    @Override
    public boolean tryEnter(GameObject obj, GameObject tile) {
        return false;
    }
    
}
