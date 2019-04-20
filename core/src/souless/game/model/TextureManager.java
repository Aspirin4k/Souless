package souless.game.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import souless.game.model.meta.AtlasMeta;
import souless.game.model.meta.DynamicAtlasMeta;
import souless.game.model.meta.SavedTexture;
import souless.game.model.meta.StaticAtlasMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TextureManager {
    private final AssetManager assetManager;

    private final HashMap<String, Sprite> textureCache;
    
    // Число текстурных регионов, которые будут кешироваться
    private final int NUMBER_OF_CACHED_TEXTURES = 10;
    private ArrayList<AtlasRegion> cachedTextures;
    
    // Динамический и статические текстурные атласы
//    private final ArrayList<DynamicTextureAtlas> dynamicAtlases;
    private final ArrayList<AtlasMeta> atlases;

    private Animation<AtlasRegion> animation;

    private HashMap<String, SavedTexture> usedTextures;
    
    public TextureManager(AssetManager assetManager)
    {
        this.assetManager = assetManager;

        this.textureCache = new HashMap<String, Sprite>();
        cachedTextures = new ArrayList<AtlasRegion>();
        // Список динамически сгенерированных текстурных атласов
        //   dynamicAtlases = new ArrayList<DynamicTextureAtlas>();
        this.atlases = new ArrayList<AtlasMeta>();

        assetManager.load("resources/tex/tilesTextures.pack", TextureAtlas.class);
        assetManager.finishLoading();
        atlases.add(new StaticAtlasMeta("tilesTextures", (TextureAtlas) assetManager.get("resources/tex/tilesTextures.pack")));
        
//        this.tileAtlas = (TextureAtlas)assetManager.get("resources/tex/tilesTextures.pack");
//        this.entityAtlas = (TextureAtlas)assetManager.get("test.pack");
//        this.uiAtlas = (TextureAtlas)assetManager.get("resources/tex/ui.pack");
    }
    
    public Animation<AtlasRegion> getAnimation() { return animation; }
    
    /**
     * Загрузка динамически сгенерированной текстуры
     * @param tex
     * @param nameid
     * @param index 
     */
    public void loadDynamicTexture(Texture tex, String nameid, int index)
    {
        SavedTexture texObj = this.usedTextures.get(nameid);
        for (AtlasMeta atlas: atlases)
            if (atlas.getAtlasName().equals(texObj.getTextureName())) 
            {
                atlas.loadTexture(tex, index);
                return;
            }
        
        DynamicAtlasMeta newAtlas = new DynamicAtlasMeta(texObj.getTextureName());
        newAtlas.loadTexture(tex, index);
        
        atlases.add(newAtlas);
    }

    public TextureRegion getTexture(String textureName)
    {
        if (!this.textureCache.containsKey(textureName)) {
            this.textureCache.put(textureName, this.atlases.get(0).getAtlas().createSprite(textureName));
        }

        return this.textureCache.get(textureName);
    }
    
    public AtlasRegion getTexture(String textureId, int textureIndex) {
        SavedTexture tex = this.usedTextures.get(textureId);
        AtlasMeta atlas = null;
        for (AtlasMeta tempAtlas : atlases)
            if (tempAtlas.getAtlasName().equals(tex.getAtlasName()))
            {
                atlas = tempAtlas;
                break;
            }
        
        // TODO: Обработать случай, когда данного атласа нет      
        switch(tex.getTextureType())
        {
            case SIMPLE:
                // Если текстурка хеширована
                for (AtlasRegion cachedTexture : cachedTextures)
                {
                    if ((cachedTexture.index == textureIndex) && 
                            (cachedTexture.name.equals(tex.getTextureName())))
                        return cachedTexture;
                }
                // Получаем ее из физического файла
                AtlasRegion newRegion = atlas.getTexture(tex.getTextureName(), textureIndex);
                if (newRegion != null)
                {
                    cachedTextures.add(0,newRegion);

                    if (cachedTextures.size() > NUMBER_OF_CACHED_TEXTURES)
                        cachedTextures.remove(cachedTextures.size()-1);
                }
                return newRegion;  
            case WALL_STYLE:
                // TODO: Подумать, как можно сделать изящнее
                for (AtlasMeta tempAtlas : atlases)
                    if ((tempAtlas.getAtlasName().equals(tex.getTextureName()) && (tempAtlas.getTexture(textureIndex) != null)))
                        return tempAtlas.getTexture(textureIndex);
                return atlas.getTexture(tex.getTextureName(), textureIndex);
            case ANIMATED:
                break;
        }
        
        return null;
    }
    
    /**
     * Подгружается список новых текстур. При этом загружаются необходимые атласы в память.
     * @param textures hashmap объектов SavedTexture
     */
    public void setTextureList(HashMap<String, SavedTexture> textures) {
        // TODO: Выгрузить загруженные ранее атласы, перед тем как загружать новые недостающие
        this.usedTextures = textures;
        for (Map.Entry<String, SavedTexture> entry : textures.entrySet())
        {
            SavedTexture val = entry.getValue();
            boolean atlasExists = false;
            for (AtlasMeta atlas : atlases)
                if (atlas.getAtlasName().equals(val.getAtlasName()))
                {
                    atlasExists = true;
                    break;
                }
            
            if (!atlasExists)
            { 
                assetManager.load("resources/tex/" + val.getAtlasName()  + ".pack", TextureAtlas.class);
                assetManager.finishLoading();
                atlases.add(new StaticAtlasMeta(val.getAtlasName(), (TextureAtlas) assetManager.get("resources/tex/" + val.getAtlasName()  + ".pack")));
            }
        }
    }

    /**
     * Получить список текстур
     * @param i
     * @return 
     */
    public SavedTexture getTextureObject(String i) { return this.usedTextures.get(i); }
    
    /*
    Возвращает хэшмап с описанием текстур, используемых текстурным менеджером в данной сцене
    */
    public HashMap<String, SavedTexture> getUsingTextures() { return this.usedTextures; }
}
