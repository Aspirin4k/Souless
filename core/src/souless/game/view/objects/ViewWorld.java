package souless.game.view.objects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import souless.game.model.ResourcesManager;
import souless.game.model.world.entity.WorldResource;

public class ViewWorld {
    
    private final WorldResource linkedWorld;
    
    private final ViewGameMap viewMap;
    private final ViewObject viewPlayer;
    
    private final ResourcesManager resManager;
    
    private OrthographicCamera bufferCamera;
    
    public ViewWorld(WorldResource world, ResourcesManager rM, SpriteBatch batch)
    {
        linkedWorld = world;
        resManager = rM;
        viewMap = new ViewGameMap(world.getMap(),rM, batch);
        viewPlayer = ViewObjectFactory.getViewTile(world.getPlayer(), rM);
        viewPlayer.generateTexture(null, resManager, batch);
    }
    
    public void draw(SpriteBatch batch, Sprite sprite, float offsetY, OrthographicCamera camera, float stateTime)
    {
        viewMap.draw(batch, sprite, offsetY, camera, stateTime);
        viewPlayer.draw(batch, sprite, offsetY, stateTime);
    }
    
}
