package souless.game.model.world;

import souless.game.model.ResourcesManager;
import souless.game.model.world.entity.WorldResource;
import souless.game.model.world.exception.WorldNotLoadedException;

public class WorldManager {
    private WorldResource currentWorld;

    private ResourcesManager resourcesManager;

    public WorldManager(ResourcesManager resourcesManager) {
        this.currentWorld = null;

        this.resourcesManager = resourcesManager;
    }

    public WorldResource getCurrentWorld() throws WorldNotLoadedException {
        if (null == this.currentWorld) {
            throw new WorldNotLoadedException("WorldManager was not initialized");
        }

        return this.currentWorld;
    }

    public void loadWorld(String saveFilename) {
        this.currentWorld = this.resourcesManager.loadWorld(saveFilename);
    }
}
