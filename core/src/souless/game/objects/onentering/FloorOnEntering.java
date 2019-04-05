package souless.game.objects.onentering;

import souless.game.objects.GameObject;

public class FloorOnEntering implements TileOnEntering {

    @Override
    public boolean tryEnter(GameObject obj, GameObject tile) {
        return true;
    }
    
}
