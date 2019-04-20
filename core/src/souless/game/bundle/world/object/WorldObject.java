package souless.game.bundle.world.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import souless.game.bundle.world.object.core.VoidWorldObjectCore;
import souless.game.bundle.world.object.view.SimpleWorldObjectView;

/**
 * Объединяет в себе логику элемента мира и его представление
 */
public class WorldObject implements IViewWorldObject, IWorldObjectCore {
    private final VoidWorldObjectCore worldObjectCore;
    private final SimpleWorldObjectView simpleWorldObjectView;

    public WorldObject(VoidWorldObjectCore worldObjectCore, SimpleWorldObjectView simpleWorldObjectView) {
        this.worldObjectCore = worldObjectCore;
        this.simpleWorldObjectView = simpleWorldObjectView;
    }

    @Override
    public TextureRegion getTexture() {
        return this.simpleWorldObjectView.getTexture();
    }

    @Override
    public VoidWorldObjectCore getCore() {
        return this.worldObjectCore;
    }
}
