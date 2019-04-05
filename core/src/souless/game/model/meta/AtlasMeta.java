package souless.game.model.meta;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public abstract class AtlasMeta {
    protected final String atlasName;
    protected final TextureAtlas atlas;
    
    public AtlasMeta(String atlasName, TextureAtlas atlas)
    {
        this.atlas = atlas;
        this.atlasName = atlasName;
    }
    
    public String getAtlasName()                                 { return this.atlasName; };
    public TextureAtlas getAtlas()                               { return this.atlas; };
    public void dispose()                                        { this.atlas.dispose(); }
    public AtlasRegion getTexture(String textureName, int index) { return this.atlas.findRegion(textureName, index); };
    public AtlasRegion getTexture(int index)                     { return null; }
    
    public abstract void loadTexture(Texture tex, int index);
}
