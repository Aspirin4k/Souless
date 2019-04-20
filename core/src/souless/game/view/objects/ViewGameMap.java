package souless.game.view.objects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import souless.game.model.GameMap;
import souless.game.model.ResourceManager;
import souless.game.objects.Entity;
import souless.game.objects.Tile;

import java.util.ArrayList;

public class ViewGameMap {
    private final GameMap linkedMap;
    private final ArrayList<ViewObject> viewGrid;
    private final ArrayList<ViewObject> viewEntities;
    
    public ViewGameMap(GameMap map, ResourceManager resManager, SpriteBatch batch)
    {
        linkedMap = map;
        viewGrid = new ArrayList<ViewObject>();
        for (Tile tile: map.getGrid())
        {
            ViewObject viewTile = ViewObjectFactory.getViewTile(tile, resManager);
            viewTile.generateTexture(linkedMap.getAdjancedTiles(tile.getPosition()),
                    resManager, batch);
            viewGrid.add(viewTile);
        }
        viewEntities = new ArrayList<ViewObject>();
        for (Entity entity: map.getEntities())
        {
            ViewObject viewEntity = ViewObjectFactory.getViewTile(entity, resManager);
            viewEntity.generateTexture(linkedMap.getAdjancedTiles(entity.getPosition()), 
                    resManager, batch);
            viewEntities.add(viewEntity);
        }
    }
    
    public void draw(SpriteBatch batch, Sprite sprite, float offsetY, OrthographicCamera camera, float stateTime)
    {
        for(ViewObject tile: viewGrid)
        {
            if ( // Проверяем, попадает ли тайл в область экрана
                    (tile.linkedTile.getPosition().x + tile.linkedTile.getRect().width >= 
                    camera.position.x - camera.viewportWidth / 2)  &&
                    (tile.linkedTile.getPosition().x <  camera.position.x + camera.viewportWidth / 2)  &&
                    (tile.linkedTile.getPosition().y + tile.linkedTile.getRect().height >= 
                    camera.position.y - camera.viewportHeight / 2 -offsetY) &&
                    (tile.linkedTile.getPosition().y <  camera.position.y + camera.viewportHeight / 2 - offsetY))
                        tile.draw(batch, sprite, offsetY, stateTime);
        }
        
        for (ViewObject entity: viewEntities)
        {
            if ( // Проверяем, попадает ли объект в область экрана
                    (entity.linkedTile.getPosition().x + entity.linkedTile.getRect().width >= 
                    camera.position.x - camera.viewportWidth / 2)  &&
                    (entity.linkedTile.getPosition().x <  camera.position.x + camera.viewportWidth / 2)  &&
                    (entity.linkedTile.getPosition().y + entity.linkedTile.getRect().height >= 
                    camera.position.y - camera.viewportHeight / 2 -offsetY) &&
                    (entity.linkedTile.getPosition().y <  camera.position.y + camera.viewportHeight / 2 - offsetY))
                        entity.draw(batch, sprite, offsetY, stateTime);
        }
    }
}
