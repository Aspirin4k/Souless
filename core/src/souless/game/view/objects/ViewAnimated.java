package souless.game.view.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import souless.game.model.ResourcesManager;
import souless.game.objects.GameObject;
import souless.game.objects.Tile;

import java.util.ArrayList;

public class ViewAnimated extends ViewObject {

    Animation<AtlasRegion> animation;
    
    public ViewAnimated(GameObject tile) {
        super(tile);
    }

    @Override
    public void generateTexture(ArrayList<Tile> adjancedTiles, ResourcesManager resManager,
                                SpriteBatch batch) {
        this.animation = resManager.getAnimation();
    }

    @Override
    public void draw(SpriteBatch batch, Sprite sprite, float offsetY, float stateTime) {
        this.texture = animation.getKeyFrame(stateTime, true);
        super.draw(batch, sprite, offsetY, stateTime);
    }
    
}
