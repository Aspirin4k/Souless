package souless.game.bundle.world.object.view;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import souless.game.model.TextureManager;

/**
 * Класс представляет простую статическую картинку
 */
public class SimpleWorldObjectView {
    private final TextureManager textureManager;
    private final String textureName;

    private TextureRegion sprite;

    public SimpleWorldObjectView(TextureManager textureManager, String textureName) {
        this.textureName = textureName;
        this.textureManager = textureManager;
    }

    public TextureRegion getTexture()
    {
        if (null == this.sprite) {
            this.sprite = this.textureManager.getTexture(this.textureName);
        }

        return this.sprite;
    }
}
