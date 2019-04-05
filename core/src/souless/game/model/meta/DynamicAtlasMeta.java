package souless.game.model.meta;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class DynamicAtlasMeta extends AtlasMeta {   
    public DynamicAtlasMeta(String name)
    {
        super(name, new TextureAtlas());
    }

    @Override
    public TextureAtlas.AtlasRegion getTexture(int index)   { return atlas.findRegion(this.atlasName + "_" + index); }
    @Override
    public void loadTexture(Texture tex, int index) {
        if (getTexture(index) != null) return;

        atlas.addRegion(this.atlasName + "_" + index, tex,0,0,tex.getWidth(),tex.getHeight());
    }
}
