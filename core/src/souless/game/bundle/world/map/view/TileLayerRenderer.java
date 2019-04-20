package souless.game.bundle.world.map.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import souless.game.bundle.world.WorldManager;
import souless.game.bundle.world.map.IWorldViewMap;
import souless.game.common.Vector2;

public class TileLayerRenderer implements ILayerRenderer {
    private final Batch batch;

    public TileLayerRenderer(Batch batch) {
        this.batch = batch;
    }

    @Override
    public void draw(IWorldViewMap worldViewMap) {
        for (int x = 1; x <= worldViewMap.getWidth(); x++) {
            for (int y = 1; y <= worldViewMap.getHeight(); y++) {
                Vector2 coordinates = new Vector2(x, y);
                TextureRegion textureRegion = worldViewMap.getTextureByCoordinates(coordinates).get(0);
                this.batch.draw(
                        textureRegion,
                        this.getUICoordinate(coordinates.getFloatedX()),
                        this.getUICoordinate(coordinates.getFloatedY())
                );
            }
        }
    }

    private float getUICoordinate(float coordinate) {
        return coordinate * WorldManager.MAP_OBJECT_SIZE;
    }
}
