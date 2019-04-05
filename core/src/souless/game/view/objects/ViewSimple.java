package souless.game.view.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import souless.game.model.ResourcesManager;
import souless.game.objects.GameObject;
import souless.game.objects.Tile;

import java.util.ArrayList;

public class ViewSimple extends ViewObject {

    public ViewSimple(GameObject tile) {
        super(tile);
    }

    @Override
    public void generateTexture(ArrayList<Tile> adjancedTiles, ResourcesManager resManager, SpriteBatch batch) {
        this.texture = resManager.getTexture(this.linkedTile.getTextureId(), 1);
        if (texture == null) texture = resManager.getTexture(linkedTile.getTextureId());
    }
    
}
