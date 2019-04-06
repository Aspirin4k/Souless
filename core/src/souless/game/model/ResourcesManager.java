package souless.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import souless.game.view.objects.ViewObjectFactory;

public class ResourcesManager
{
    private final AssetManager assets;
    
    // Текстурные атласы и шрифты
    private final TexturesManager textureManager;

    private BitmapFont font;
    
    // Содержит информацию, выводимую в лог
    private ConsoleLogData logData;
    
    public enum ManagerMode {
      GAMEMODE  
    };
    
    /**
     * Создает менеджер ресурсов.
     */
    public ResourcesManager()
    {
        assets = new AssetManager();
        
        textureManager = new TexturesManager(assets);
        loadFont();
        
        assets.finishLoading();
    }
    
    public Animation<AtlasRegion> getAnimation() { return this.textureManager.getAnimation(); }
    
    /**
     * Генерирует шрифт из .ttf файла
     */
    private void loadFont() 
    { 
//        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("resources/fonts/lsr.ttf"));
//
//        FreeTypeFontParameter param = new FreeTypeFontParameter();
//        param.size = 20;
//        param.characters = FreeTypeFontGenerator.DEFAULT_CHARS;
//        param.minFilter = TextureFilter.Linear;
//        param.magFilter = TextureFilter.Linear;
//        param.characters = FreeTypeFontGenerator.DEFAULT_CHARS +
//                "ЁЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮёйцукенгшщзхъфывапролджэячсмитьбю";
//
//        font = generator.generateFont(param);
    }
    
    /**
     * Загружает текстуру в атлас для динамически сгенерированных текстур
     * @param tex текстура
     * @param nameid номер текстуры в массиве текстур
     * @param index индекс
     */
    public void loadDynamicTexture(Texture tex, String nameid, int index) { 
        this.textureManager.loadDynamicTexture(tex, nameid, index);
    }
    
    /**
     * Возвращает ссылку на шрифт
     * @return 
     */
    public BitmapFont getFont() { return font; }
    
    /**
     * Возвращает регион атласа из текстурного атласа тайлов с данным именем
     * @param textureName имя текстурного региона
     * @return текстурный регион
     */
    public AtlasRegion getTexture(String textureName) 
    { 
        return getTexture(textureName, -1); 
    }
    
    /**
     * Возвращает регион атласа из текстурного атласа тайлов с данным именем и индексом
     * @param textureNameId индекс используемой текстуры
     * @param textureIndex индекс текстуры
     * @return текстурный регион
     */
    public AtlasRegion getTexture(String textureNameId, int textureIndex)
    {
        return this.textureManager.getTexture(textureNameId, textureIndex);
    }
    
    /**
     * Устанавливает ссылку на данные консоли
     * @param logData 
     */
    public void setLogData(ConsoleLogData logData) { this.logData = logData; }
    
    /**
     * Возвращает ссылку на данные консоли
     * @return 
     */
    public ConsoleLogData getLogData()             { return this.logData; }
    
    /**
     * Сохраняет мир в локальный файл
     * @param world мир
     */
    public void saveWorld(World world)
    {   
       FileHandle fh = Gdx.files.local("saves/" + world.getWorldName() + ".svd");
       SaveGameParser sv = new SaveGameParser();
       String svString = sv.parse(world, this.textureManager.getUsingTextures());
       fh.writeString(svString, false);
    }
    
    /**
     * Позволяет загрузить мир с данным именем. При отсутствии сохранения создает новый мир
     * @param name название мира
     * @return 
     */
    public World loadWorld(String name)
    {
        // TODO: пока сохранение представлено в виде текстового файла - после запиливания редактора необходимо переделать в бинарный
        FileHandle fh = Gdx.files.local("saves/" + name + ".svd");
        if (!fh.exists())
        {
            logData.add("Создан новый мир");
            return new World();
        }
        
        try
        {
            SaveGameParser sv = new SaveGameParser();
            World loadedWorld = sv.parse(fh.readString());
            this.textureManager.setTextureList(sv.getTextureList());
            loadedWorld.setWorldName(name);
            return loadedWorld;
        }
        catch (Exception e)
        {
            logData.add(e.toString());
            logData.add("Произошла ошибка при загрузке уровня " + name);
        }
        return null;
    }
    
    public ViewObjectFactory.viewType geTextureType(String i) { return this.textureManager.getTextureObject(i).getTextureType(); }
}
