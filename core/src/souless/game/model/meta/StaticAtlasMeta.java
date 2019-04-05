package souless.game.model.meta;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class StaticAtlasMeta extends AtlasMeta {
    public StaticAtlasMeta(String atlasName, TextureAtlas atlas)
    {
        super(atlasName, atlas);
    }

    @Override
    public void loadTexture(Texture tex, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}