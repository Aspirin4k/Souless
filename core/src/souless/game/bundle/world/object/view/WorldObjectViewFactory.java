package souless.game.bundle.world.object.view;

import souless.game.model.TextureManager;

public class WorldObjectViewFactory {
    private final TextureManager textureManager;

    public WorldObjectViewFactory(TextureManager textureManager) {
        this.textureManager = textureManager;
    }

    public SimpleWorldObjectView getSimpleView(String textureName) {
        return new SimpleWorldObjectView(this.textureManager, textureName);
    }
}
