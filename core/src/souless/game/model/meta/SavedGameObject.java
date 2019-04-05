package souless.game.model.meta;

import souless.game.objects.GameObjectFactory;

public class SavedGameObject {
    private final String textureId;
    private final GameObjectFactory.ObjectType objectType;

    public SavedGameObject(String textureId, int objectType)
    {
        this.textureId = textureId;
        this.objectType = GameObjectFactory.ObjectType.getTypeByBalue(objectType);
    };
    
    public SavedGameObject(String textureId, GameObjectFactory.ObjectType objectType)
    {
        this.textureId = textureId;
        this.objectType = objectType;
    }

    public String getTextureId()      { return this.textureId; };
    public GameObjectFactory.ObjectType getObjectType() { return this.objectType; };
}
