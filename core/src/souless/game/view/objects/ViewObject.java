package souless.game.view.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import souless.game.model.ResourceManager;
import souless.game.objects.GameObject;
import souless.game.objects.Tile;

import java.util.ArrayList;

public abstract class ViewObject {
    protected final GameObject linkedTile;
    protected AtlasRegion texture;
    
    public ViewObject(GameObject tile)
    {
        linkedTile = tile;
    }
    
    /**
     * Генерация текстуры
     * @param adjancedTiles
     * @param resManager 
     * @param batch
     */
    public abstract void generateTexture(ArrayList<Tile> adjancedTiles,
                                         ResourceManager resManager, SpriteBatch batch);
    
    /**
     * Метод отрисовки тайла
     * @param batch кисть
     * @param sprite
     * @param offsetY смещение по вертикали
     * @param stateTime
     */
    public void draw(SpriteBatch batch, Sprite sprite, float offsetY, float stateTime)
    {
        sprite.setRegion(texture);
        sprite.setPosition(linkedTile.getPosition().x, linkedTile.getPosition().y + offsetY);
        sprite.setSize(linkedTile.getRect().width, linkedTile.getRect().height);
        sprite.draw(batch);
    }
}
