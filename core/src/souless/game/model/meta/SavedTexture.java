package souless.game.model.meta;

import souless.game.view.objects.ViewObjectFactory;

/*
Вспомогательный класс для фиксирования текстур, используемых на карте
*/
public class SavedTexture {
    private final String textureName;
    private final ViewObjectFactory.viewType textureType;
    private final String atlasName;

    public SavedTexture(String textureName, int textureTypeNumber, String atlasName)
    {
        this.textureName = textureName;
        this.textureType = ViewObjectFactory.viewType.getTypeByBalue(textureTypeNumber);
        this.atlasName = atlasName;
    };
    
    public SavedTexture(String textureName, ViewObjectFactory.viewType textureType, String atlasName)
    {
        this.textureName = textureName;
        this.textureType = textureType;
        this.atlasName = atlasName;
    };

    public String getTextureName()     { return this.textureName; };
    public ViewObjectFactory.viewType getTextureType()   { return this.textureType; };
    public String getAtlasName()       { return this.atlasName; };
};